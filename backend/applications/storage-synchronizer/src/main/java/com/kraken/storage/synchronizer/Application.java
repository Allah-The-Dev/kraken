package com.kraken.storage.synchronizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kraken"})
@Component
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}