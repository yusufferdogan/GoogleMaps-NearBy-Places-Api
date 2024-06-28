package com.yusuf.erdogan.mapsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "place_request")
public class PlaceRequest {

    @EmbeddedId
    private PlaceRequestId id;

    @ManyToMany(mappedBy = "placeRequests", fetch = FetchType.EAGER)
    private Set<Place> places = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceRequest that = (PlaceRequest) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}

