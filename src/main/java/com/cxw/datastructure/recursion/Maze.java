package com.cxw.datastructure.recursion;


/**
 * 迷宫问题
 * 0是还未走过
 * 1是墙
 * 2可以走通
 * 3走不通
 *  策略 下-》右-》上-》左
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][8];
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[5][5] = 1;
        map[5][6] = 1;
        print(map);
        System.out.println("----------------");
        goWay(map,1,1);
        print(map);
    }

    private static boolean goWay(int[][] map, int i, int j) {
        if(map[6][6]==2){
            return true;
        }else{
            if(map[i][j]==0) {
                map[i][j] = 2;
                //下-》右-》上-》左
                if (goWay(map, i + 1, j)) {
                    return true;
                } else if (goWay(map, i, j + 1)) {
                    return true;
                } else if (goWay(map, i - 1, j)) {
                    return true;
                } else if (goWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                }
            }
            return false;
        }
    }

    public static void print(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
