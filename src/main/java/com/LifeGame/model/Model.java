package com.LifeGame.model;

public interface Model {
    void nextState();

    void setMapSize(int n);

    void toggle(int x, int y);   // dot가 업데이트된 위치
}
