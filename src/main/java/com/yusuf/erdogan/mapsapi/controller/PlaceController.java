package com.yusuf.erdogan.mapsapi.controller;

import com.yusuf.erdogan.mapsapi.dto.request.PlacesRequest;
import com.yusuf.erdogan.mapsapi.dto.response.PlacesResponse;
import com.yusuf.erdogan.mapsapi.dto.utilities.result.DataResult;
import com.yusuf.erdogan.mapsapi.entity.Place;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequest;
import com.yusuf.erdogan.mapsapi.service.Impl.PlaceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@Validated
public class PlaceController {

    private final PlaceServiceImpl placeService;

    @Operation(summary = "Search nearby places")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found places",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlacesResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/search")
    public DataResult<PlacesResponse> searchNearby(@Valid @ModelAttribute PlacesRequest request) {
        return (placeService.getPlaces(request));
    }
}
