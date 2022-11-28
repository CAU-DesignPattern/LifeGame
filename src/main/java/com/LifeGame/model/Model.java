package com.LifeGame.model;

import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class Model extends Observable {

    private int size;
    private int[][] map;

    public void mapChanged() {
        setChanged();
        notifyObservers();
    }

    public void clearMap() {  //map 초기화
        this.map = new int[this.size][this.size];

        this.mapChanged();
    }
    public int getMapSize() {
        return size;
    }

    public int[][] getMap() {
        return this.map;
    }

    public void setMapSize(int n) {
        this.size = n;
        this.map = new int[n][n];
    }

    public void toggle(int x, int y) {
        // 좌표 설정 방식 - 그냥 배열이랑 똑같이 간다고 가정
        if (this.map[x][y] == 0) {
            this.map[x][y] = 1;
        } else {
            this.map[x][y] = 0;
        }

        this.mapChanged();
    }

    public void setMap(int[][] map) {
        this.map = map;

        this.mapChanged();
    }

    public void nextState(int[][] map) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        int n = map.length;
        int m = map[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int state = 0;

                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x < 0 || x >= n || y < 0 || y >= m) continue;
                    if (map[x][y] == 1 || map[x][y] == 2) state++;
                }

                if (map[i][j] == 0 && state == 3) map[i][j] = 3;
                if (map[i][j] == 1 && (state < 2 || state > 3)) map[i][j] = 2;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] %= 2;
            }
        }
        this.map = map;

        this.mapChanged();
    }
}
