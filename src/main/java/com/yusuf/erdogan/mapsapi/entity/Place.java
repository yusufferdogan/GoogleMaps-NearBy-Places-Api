package com.yusuf.erdogan.mapsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "place")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double latitude;
    private Double longitude;

    private String vicinity;

    @Column(name = "place_id", unique = true)
    private String placeId;

    private Double rating;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "place_request_response",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "place_request_latitude", referencedColumnName = "latitude"),
                    @JoinColumn(name = "place_request_longitude", referencedColumnName = "longitude"),
                    @JoinColumn(name = "place_request_radius", referencedColumnName = "radius")
            }
    )
    private Set<PlaceRequest> placeRequests = new HashSet<>();
}