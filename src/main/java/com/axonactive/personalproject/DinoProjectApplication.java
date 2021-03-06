package com.axonactive.personalproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.SpringServletContainerInitializer;

@Slf4j
@SpringBootApplication
public class DinoProjectApplication extends SpringServletContainerInitializer {

  protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
    return app.sources(DinoProjectApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(DinoProjectApplication.class, args);

  }
}
