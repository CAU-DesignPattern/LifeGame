package com.LifeGame.model;

import org.springframework.stereotype.Component;

@Component
public class Model {

    public void clearMap() {  //map 초기화
    }

    public void nextState() {

    }

    public void setMapSize(int n) {

    }
    public int getMapSize() {
        return 0;
    }

    public void toggle(int x, int y) {  //dot가 업데이트된 위치
    }
    public void toggle(int[][] liveCells) {

    }

    public int[][] getLiveCells() {
        return null;
    }
}
