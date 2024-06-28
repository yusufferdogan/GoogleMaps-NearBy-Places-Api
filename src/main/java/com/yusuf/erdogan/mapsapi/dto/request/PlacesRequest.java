package com.yusuf.erdogan.mapsapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacesRequest implements Serializable {
    private Double latitude;
    private Double longitude;
    private int radius;
}
