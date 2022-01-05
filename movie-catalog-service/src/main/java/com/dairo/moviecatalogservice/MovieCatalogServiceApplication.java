package com.dairo.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory=
//                new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(3000);
//        return new RestTemplate(clientHttpRequestFactory);

        //ABOVE IS IF WE WANT TO MANUALLY TIMEOUT AFTER t SECONDS

        return new RestTemplate();
    }

//    @Bean
//    public WebClient.Builder getWebClient(){
//        return WebClient.builder();
//    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
