package com.yusuf.erdogan.mapsapi.service;

import com.yusuf.erdogan.mapsapi.dao.PlaceRepository;
import com.yusuf.erdogan.mapsapi.dao.PlaceRequestRepository;
import com.yusuf.erdogan.mapsapi.entity.Place;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequest;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequestId;
import com.yusuf.erdogan.mapsapi.webserviceclient.GooglePlacesClient;
import com.yusuf.erdogan.mapsapi.webserviceclient.response.GooglePlacesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final GooglePlacesClient googlePlacesClient;
    private final PlaceRequestRepository placeRequestRepository;

    @Value("${google.places.api.key}")
    private String apiKey;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Place> searchNearby(Double latitude, Double longitude, int radius) {
        PlaceRequestId placeRequestId = new PlaceRequestId(latitude, longitude, radius);

        Optional<PlaceRequest> existingRequest;
        existingRequest = placeRequestRepository.findById(placeRequestId);
        if (existingRequest.isPresent()) {
            System.out.println("Returned from DB");
            return existingRequest.get().getPlaces().stream().toList();
        }

        GooglePlacesResponse apiResponse = googlePlacesClient.searchNearby(latitude + "," + longitude, radius, apiKey);
        Set<Place> places = apiResponse.getResults().stream().map(this::mapToPlaceEntity).collect(Collectors.toSet());

        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setId(placeRequestId);
        placeRequest.setPlaces(places);

        placeRepository.saveAll(places);
        placeRequestRepository.save(placeRequest);

        return places.stream().toList();
    }

    private Place mapToPlaceEntity(GooglePlacesResponse.GooglePlacesResult result) {
        Place place = new Place();
        place.setName(result.getName());
        place.setLatitude(result.getGeometry().getLocation().getLat());
        place.setLongitude(result.getGeometry().getLocation().getLng());
        place.setVicinity(result.getVicinity());
        place.setPlaceId(result.getPlaceId());
        place.setRating(result.getRating());
//        place.setTypes(result.getTypes());
//        place.setBusinessStatus(result.getBusinessStatus());
//        if (!result.getPhotos().isEmpty()) {
//            place.setPhotoReference(result.getPhotos().get(0).getPhotoReference());
//        }
        return place;
    }
}
