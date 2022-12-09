package com.LifeGame;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LifeGameApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(LifeGameApplication.class).headless(false).web(WebApplicationType.NONE).run(args);
    }
}
