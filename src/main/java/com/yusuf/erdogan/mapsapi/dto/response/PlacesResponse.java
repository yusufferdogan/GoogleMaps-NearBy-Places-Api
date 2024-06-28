package com.yusuf.erdogan.mapsapi.dto.response;

import com.yusuf.erdogan.mapsapi.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PlacesResponse {
    private List<Place> places;
}
