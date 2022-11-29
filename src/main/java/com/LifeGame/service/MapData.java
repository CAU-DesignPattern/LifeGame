package com.LifeGame.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MapData {
    private int mapSize;
    private int[][] map;
}