package com.yusuf.erdogan.mapsapi.dao;

import com.yusuf.erdogan.mapsapi.entity.PlaceRequest;
import com.yusuf.erdogan.mapsapi.entity.PlaceRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRequestRepository extends JpaRepository<PlaceRequest, PlaceRequestId> {
}
