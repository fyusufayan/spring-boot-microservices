package com.dairo.moviecatalogservice.resources;

import com.dairo.moviecatalogservice.models.CatalogItem;
import com.dairo.moviecatalogservice.models.Movie;
import com.dairo.moviecatalogservice.models.Rating;
import com.dairo.moviecatalogservice.models.UserRating;
import com.dairo.moviecatalogservice.services.MovieInfo;
import com.dairo.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        UserRating ratings= userRatingInfo.getUserRating(userId);
        return ratings.getRatings().stream()
                .map(rating ->movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

}


// REST TEMPLATE FOR REACTIVE PROGRAMMING
//@Autowired
//private WebClient.Builder webClientBuilder;

// REACTIVE
//Movie movie=webClientBuilder.build()
//        .get()
//        .uri("http://localhost:8082/movies/"+rating.getMovieId())
//        .retrieve()
//        .bodyToMono(Movie.class)
//        .block();