package com.yusuf.erdogan.mapsapi.webserviceclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GooglePlacesResponse {
    private List<GooglePlacesResult> results;

    @Data
    public static class GooglePlacesResult {
        private String name;
        private Geometry geometry;
        private String vicinity;
        @JsonProperty("place_id")
        private String placeId;
        private Double rating;
        private List<String> types;
        @JsonProperty("business_status")
        private String businessStatus;
        private List<Photo> photos;

        @Data
        public static class Geometry {
            private Location location;

            @Data
            public static class Location {
                private Double lat;
                private Double lng;
            }
        }

        @Data
        public static class Photo {
            @JsonProperty("photo_reference")
            private String photoReference;
        }
    }
}
