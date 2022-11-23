package com.LifeGame.model;

import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class ExampleModel extends Observable implements Model{
    @Override
    public void nextState(int[][] map) {

    }

    @Override
    public void setMapSize(int n) {


    }

    @Override
    public void toggle(int x, int y) {

    }
}
