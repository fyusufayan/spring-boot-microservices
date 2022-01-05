package com.dairo.moviecatalogservice.services;

import com.dairo.moviecatalogservice.models.Rating;
import com.dairo.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(@PathVariable String userId){
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
    }

    public UserRating getFallbackUserRating(@PathVariable String userId){
        UserRating userRating=new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(new Rating("0",0)));
        return userRating;
    }
}
