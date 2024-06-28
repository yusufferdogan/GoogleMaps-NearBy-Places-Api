package com.yusuf.erdogan.mapsapi.service;

import com.yusuf.erdogan.mapsapi.dto.request.PlacesRequest;
import com.yusuf.erdogan.mapsapi.dto.response.PlacesResponse;
import com.yusuf.erdogan.mapsapi.dto.utilities.result.DataResult;
import org.springframework.stereotype.Service;

@Service
public interface PlaceService {
    DataResult<PlacesResponse> getPlaces(PlacesRequest request);
}
