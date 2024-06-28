package com.yusuf.erdogan.mapsapi.dto.request;

import com.yusuf.erdogan.mapsapi.validator.ValidLatitude;
import com.yusuf.erdogan.mapsapi.validator.ValidLongitude;
import com.yusuf.erdogan.mapsapi.validator.ValidRadius;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacesRequest implements Serializable {

    @Schema(description = "Latitude of the location", example = "37.7749", required = true)
    @ValidLatitude
    @NotNull
    private Double latitude;

    @Schema(description = "Longitude of the location", example = "-122.4194", required = true)
    @ValidLongitude
    @NotNull
    private Double longitude;

    @Schema(description = "Radius for searching nearby places", example = "1000", required = true)
    @ValidRadius
    @NotNull
    private int radius;
}
