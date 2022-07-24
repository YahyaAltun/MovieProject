package com.examplemovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.examplemovie")
public class ExampleMovieApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(ExampleMovieApplication1.class, args);
    }

}
