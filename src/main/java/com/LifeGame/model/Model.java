package com.LifeGame.model;

public interface Model {
    public void nextState();
    public void setMapSize(int n);
    public void toggle(int x, int y);   // dot가 업데이트된 위치
}
