package com.LifeGame.model;

public class Model {

    public int[][] setMapSize(int n) {
        int[][] arr = new int[n][n];
        return arr;
    }

    public int[][] toggle(int x, int y, int[][] arr) {
        // 좌표 설정 방식 - 그냥 배열이랑 똑같이 간다고 가정
        int[][] toggled = arr;
        toggled[x][y] = 1;
        return toggled;
    }

    public int[][] nextState(int[][] map) {
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

        return map;

    }
//
//    public void nextState(int[][] arr);
//    public void setMapSize(int n);
//    public void toggle(int x, int y);   // dot가 업데이트된 위치
}
