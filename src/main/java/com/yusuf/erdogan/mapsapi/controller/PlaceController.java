package com.yusuf.erdogan.mapsapi.controller;

import com.yusuf.erdogan.mapsapi.dto.request.PlacesRequest;
import com.yusuf.erdogan.mapsapi.dto.response.PlacesResponse;
import com.yusuf.erdogan.mapsapi.dto.utilities.result.DataResult;
import com.yusuf.erdogan.mapsapi.entity.Place;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequest;
import com.yusuf.erdogan.mapsapi.service.Impl.PlaceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceServiceImpl placeService;


    @GetMapping("/search")
    public DataResult<PlacesResponse> searchNearby(PlacesRequest request) {
        return (placeService.getPlaces(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Set<PlaceRequest>>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces().stream().map(Place::getPlaceRequests).toList());
    }
}
