package com.yusuf.erdogan.mapsapi.dto.request;

import com.yusuf.erdogan.mapsapi.validator.ValidLatitude;
import com.yusuf.erdogan.mapsapi.validator.ValidLongitude;
import com.yusuf.erdogan.mapsapi.validator.ValidRadius;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacesRequest implements Serializable {
    @ValidLatitude
    @NotNull
    private Double latitude;

    @ValidLongitude
    @NotNull
    private Double longitude;

    @ValidRadius
    @NotNull
    private int radius;
}
