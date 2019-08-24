package com.kqyang.springcloud.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author kqyang
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class SpringcloudMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMovieApplication.class, args);
    }

}
