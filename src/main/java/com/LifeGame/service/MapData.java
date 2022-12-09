package com.LifeGame.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapData {
    private int mapSize;
    private int[][] map;
}