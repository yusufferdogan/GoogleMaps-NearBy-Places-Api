package com.yusuf.erdogan.mapsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MapsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapsApiApplication.class, args);
    }

}
