package com.yusuf.erdogan.mapsapi.webserviceclient;

import com.yusuf.erdogan.mapsapi.webserviceclient.response.GooglePlacesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googlePlacesClient", url = "${google.places.api.url}")
public interface GooglePlacesClient {

    @GetMapping
    GooglePlacesResponse searchNearby(@RequestParam("location") String location,
                                      @RequestParam("radius") int radius,
                                      @RequestParam("key") String apiKey);
}
