package com.yusuf.erdogan.mapsapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "place_request")
public class PlaceRequest {

    @EmbeddedId
    private PlaceRequestId id;

    @ManyToMany
    @JoinTable(
            name = "place_request_response",
            joinColumns = {
                    @JoinColumn(name = "latitude", referencedColumnName = "latitude"),
                    @JoinColumn(name = "longitude", referencedColumnName = "longitude"),
                    @JoinColumn(name = "radius", referencedColumnName = "radius")
            },
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private Set<Place> places = new HashSet<>();

    // Getters and Setters
}

