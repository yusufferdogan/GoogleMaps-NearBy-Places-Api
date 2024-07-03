# Nearby Places API

This project is a Java-based backend service for finding nearby places using Google Maps API. The application is built using Spring Boot and follows an N-layered architecture to ensure modularity, scalability, and maintainability.

## Deployed 
Deployed to https://web-production-8958.up.railway.app
<br/><br/>
Sample Query [https://web-production-8958.up.railway.app/api/places/search?latitude=40.9905539&longitude=29.0195239&radius=500](https://web-production-8958.up.railway.app/api/places/search?latitude=40.9905539&longitude=29.0195239&radius=500) <br/><br/>
Swagger Page : https://web-production-8958.up.railway.app/swagger-ui/index.html#/place-controller/searchNearby

## Features

- Fetches nearby places based on latitude, longitude, and radius.
- Caches the results to a database to avoid redundant API calls.
- Uses Google Places API for data retrieval.
- Provides RESTful endpoints for querying places.

## Technologies Used

- Java 8+
- Spring Boot
- Hibernate
- MySQL
- Feign Client
- Swagger for API documentation

## N-layered Architecture

The N-layered architecture divides the application into distinct layers, each responsible for a specific aspect of the application. This approach enhances modularity, making the application easier to manage and scale.

### 1. Presentation Layer

The Presentation Layer handles the HTTP requests and responses. It includes the controllers that expose the RESTful endpoints for the API.

#### Example Controller
```java
@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("/search")
    public DataResult<List<PlaceResponse>> searchNearbyPlaces(
        @RequestParam double latitude,
        @RequestParam double longitude,
        @RequestParam int radius) {
        return placeService.getNearbyPlaces(latitude, longitude, radius);
    }
}
```

### 2. Service Layer

The Service Layer contains the business logic of the application. It processes the requests from the controllers and interacts with the Data Access Layer to fetch or persist data.

#### Example Service
```java
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private GooglePlacesClient googlePlacesClient;

    public DataResult<List<PlaceResponse>> getNearbyPlaces(double latitude, double longitude, int radius) {
        // Business logic to fetch places
    }
}
```

### 3. Data Access Layer

The Data Access Layer interacts with the database. It contains repository classes that perform CRUD operations on the database entities.

#### Example Repository
```java
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // Custom query methods
}
```

### 4. Integration Layer

The Integration Layer handles communication with external services, such as the Google Places API. This layer uses Feign Client for making HTTP calls to external APIs.

#### Example Feign Client
```java
@FeignClient(name = "googlePlacesClient", url = "${google.places.api.url}")
public interface GooglePlacesClient {
    @GetMapping
    GooglePlacesResponse getPlaces(@RequestParam("location") String location,
                                   @RequestParam("radius") int radius,
                                   @RequestParam("key") String apiKey);
}
```

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yusufferdogan/GoogleMaps-NearBy-Places-Api.git
    cd GoogleMaps-NearBy-Places-Api
    ```

2. **Set up MySQL database:**
    - Create a database named `nearby_places`.
    - Update the `application.properties` file with your MySQL database credentials.

3. **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the API documentation:**
    - Open [https://web-production-8958.up.railway.app/swagger-ui/index.html#/place-controller/searchNearby](https://web-production-8958.up.railway.app/swagger-ui/index.html#/place-controller/searchNearby) in your browser to view the Swagger UI documentation.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

For more information, visit the [project repository](https://github.com/yusufferdogan/GoogleMaps-NearBy-Places-Api).

## DATABASE AND RELATIONS

![Screenshot from 2024-07-02 21-53-05](https://github.com/yusufferdogan/GoogleMaps-NearBy-Places-Api/assets/45846424/88124455-b76f-4228-a299-0b9590f5296d)
place_request Table:

Columns: latitude, longitude, radius
Primary Key: Combination of latitude, longitude, and radius
Description: This table stores the details of location-based requests made by users, including the latitude, longitude, and the search radius.
place Table:

Columns: id (Primary Key), latitude, longitude, name, place_id (unique), rating, vicinity, photo
Primary Key: id
Unique Constraint: place_id <br/>
Description: This table stores details about places returned from the Google Places API, such as their geographical coordinates, name, unique place identifier, rating, vicinity, and photo reference.
place_request_response Table:

Columns: place_id, place_request_latitude, place_request_longitude, place_request_radius <br/>
Primary Key: Combination of place_id, place_request_latitude, place_request_longitude, place_request_radius <br/>
Foreign Keys:
place_request_latitude, place_request_longitude, place_request_radius references place_request (latitude, longitude, radius) <br/>
place_id references place (id) <br/>
Description: This table represents the many-to-many relationship between place_request and place. Each entry links a specific place to a specific request. <br/>
<br/> Relations: 
place_request to place_request_response:
A one-to-many relationship where each request can have multiple associated responses.
place to place_request_response:
A one-to-many relationship where each place can be associated with multiple requests.
