package com.LifeGame.model;


// changestate : method
// class : model -> interface의 것 옮겨오기 Or implements
public class ChangeState implements Model{

    @Override
    public void nextState(int[][] arr){}

    @Override
    public void setMapSize(int n) {

    }

    @Override
    public void toggle(int x, int y) {

    }
    public static void update(int[][] map) {
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

//        for (int i = 0; i < map.length; i++) {
//            int[] check = map[i];
//            for (int j = 0; j < check.length; j++) {
//                System.out.print(check[j] + " ");
//            }
//            System.out.println();
//        }

    }

    public static void main(String[] args) {

//        int[][] arr = new int[3][3];
//
//
//        // 1일 때 기준으로
//        // 큰 사이즈에 대해서
//        arr[0][0] = 1;
//        arr[0][1] = 1;
//        arr[0][2] = 1;
//        arr[1][0] = 1;
//        arr[1][1] = 0;
//        arr[1][2] = 0;
//        arr[2][0] = 0;
//        arr[2][1] = 0;
//        arr[2][2] = 1;


        int[][] arr = {{1,0,1,0,1,0,1,1,0,0},{1,1,0,0,0,1,0,0,0,1},{1,1,1,0,0,0,0,0,0,0},{0,0,1,1,0,1,1,0,0,0},{0,0,0,1,0,0,1,0,0,0},
                {0,0,1,1,0,1,1,0,0,0},{0,0,0,1,0,0,1,0,0,0}, {1,0,1,0,1,0,1,1,0,0},{1,1,0,0,0,1,0,0,0,1},{1,1,1,0,0,0,0,0,0,0}};

        System.out.println("before");
        for (int i = 0; i < arr.length; i++) {
            int[] check = arr[i];
            for (int j = 0; j < check.length; j++) {
                System.out.print(check[j] + " ");
            }
            System.out.println();
        }
        System.out.println("after");

        long start = System.nanoTime();
        update(arr);
        long end = System.nanoTime();
        System.out.println("수행시간: " + (end - start) + " ns");

    }
}
