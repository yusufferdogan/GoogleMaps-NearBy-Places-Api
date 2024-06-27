package com.yusuf.erdogan.mapsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "PLACE")
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

    @Column(unique = true)
    private String placeId;

    private Double rating;
}