package com.LifeGame.service;

import org.springframework.stereotype.Component;

@Component
public class Service {

    public MapData load() throws InvalidFileLoadedException {
        throw new InvalidFileLoadedException();
    }

    public void store(MapData mapData) {

    }
}
