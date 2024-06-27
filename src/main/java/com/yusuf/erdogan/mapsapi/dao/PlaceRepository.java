package com.yusuf.erdogan.mapsapi.dao;

import com.yusuf.erdogan.mapsapi.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByPlaceId(String placeId);
}
