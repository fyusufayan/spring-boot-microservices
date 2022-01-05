package com.dairo.moviecatalogservice.resources;

import com.dairo.moviecatalogservice.models.CatalogItem;
import com.dairo.moviecatalogservice.models.Movie;
import com.dairo.moviecatalogservice.models.Rating;
import com.dairo.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        UserRating ratings= restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating ->{
            // For each movie ID, call movie info service and get details
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
            // Put them all together
            return new CatalogItem(movie.getName(),"description",rating.getRating());
        })
        .collect(Collectors.toList());

    }
}


// REACTIVE PROGRAMMING İÇİN REST
//@Autowired
//private WebClient.Builder webClientBuilder;

// REACTIVE
//Movie movie=webClientBuilder.build()
//        .get()
//        .uri("http://localhost:8082/movies/"+rating.getMovieId())
//        .retrieve()
//        .bodyToMono(Movie.class)
//        .block();