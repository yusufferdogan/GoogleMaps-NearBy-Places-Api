package com.yusuf.erdogan.mapsapi.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlaceRequestId implements Serializable {
    private Double latitude;
    private Double longitude;
    private int radius;

    public PlaceRequestId(Double latitude, Double longitude, int radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public PlaceRequestId() {

    }
}
