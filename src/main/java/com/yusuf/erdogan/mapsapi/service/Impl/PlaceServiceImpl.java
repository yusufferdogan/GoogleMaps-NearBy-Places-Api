package com.yusuf.erdogan.mapsapi.service.Impl;

import com.yusuf.erdogan.mapsapi.dao.PlaceRepository;
import com.yusuf.erdogan.mapsapi.dao.PlaceRequestRepository;
import com.yusuf.erdogan.mapsapi.dto.request.PlacesRequest;
import com.yusuf.erdogan.mapsapi.dto.response.PlacesResponse;
import com.yusuf.erdogan.mapsapi.dto.utilities.result.DataResult;
import com.yusuf.erdogan.mapsapi.dto.utilities.result.SuccessDataResult;
import com.yusuf.erdogan.mapsapi.entity.Place;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequest;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequestId;
import com.yusuf.erdogan.mapsapi.service.PlaceService;
import com.yusuf.erdogan.mapsapi.webserviceclient.GooglePlacesClient;
import com.yusuf.erdogan.mapsapi.webserviceclient.response.GooglePlacesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final GooglePlacesClient googlePlacesClient;
    private final PlaceRequestRepository placeRequestRepository;

    @Value("${google.places.api.key}")
    private String apiKey;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public DataResult<PlacesResponse> getPlaces(PlacesRequest request) {
        PlaceRequestId placeRequestId = new PlaceRequestId(request.getLatitude(), request.getLongitude(), request.getRadius());

        Optional<PlaceRequest> existingRequest = placeRequestRepository.findById(placeRequestId);
        if (existingRequest.isPresent()) {
            List<Place> places = new ArrayList<>(existingRequest.get().getPlaces());
            return new SuccessDataResult<>(new PlacesResponse(places), "Fetched from database");
        }

        GooglePlacesResponse apiResponse = googlePlacesClient.searchNearby(request.getLatitude() + "," + request.getLongitude(), request.getRadius(), apiKey);
        Set<Place> places = apiResponse.getResults().stream().map(this::mapToPlaceEntity).collect(Collectors.toSet());

        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setId(placeRequestId);
        placeRequest.setPlaces(places);

        placeRequestRepository.save(placeRequest);
        for (Place place : places) {
            Optional<Place> existingPlace = placeRepository.findByPlaceId(place.getPlaceId());
            if (existingPlace.isPresent()) {
                place = existingPlace.get();
            } else {
                placeRepository.save(place);
            }
            place.getPlaceRequests().add(placeRequest);
        }

        return new SuccessDataResult<>(new PlacesResponse(new ArrayList<>(places)), "Fetched from Google Places API");
    }


    private Place mapToPlaceEntity(GooglePlacesResponse.GooglePlacesResult result) {
        Place place = new Place();
        place.setName(result.getName());
        place.setLatitude(result.getGeometry().getLocation().getLat());
        place.setLongitude(result.getGeometry().getLocation().getLng());
        place.setVicinity(result.getVicinity());
        place.setPlaceId(result.getPlaceId());
        place.setRating(result.getRating());
        if (result.getPhotos() != null) {
            place.setPhoto(result.getPhotos().get(0).getPhotoReference());
        }
        return place;
    }
}
