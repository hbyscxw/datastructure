package com.cxw.algorithm.horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-08-07 11:02
 * @description 马踏棋盘算法
 */
public class HorseChessBoardAlgorithm {
    private int len;
    private int[][] chess;
    private boolean[][] visited;
    private boolean finished;
    public static void main(String[] args) {
        HorseChessBoardAlgorithm horseChessBoard = new HorseChessBoardAlgorithm(6);
        horseChessBoard.horseChessboard(1,0,1);
        horseChessBoard.print();
    }

    public HorseChessBoardAlgorithm(int len){
        this.len = len;
        chess =  new int[len][len];
        visited =  new boolean[len][len];
    }

    public List<Point> next(Point cur){
        List<Point> ps = new ArrayList<>();
        Point p = new Point();
        if((p.x = cur.x - 2)>=0&&(p.y = cur.y - 1)>=0){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x - 1)>=0&&(p.y = cur.y - 2)>=0){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x - 2)>=0&&(p.y = cur.y + 1)<len){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x - 1)>=0&&(p.y = cur.y + 2)<len){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x + 2)<len&&(p.y = cur.y - 1)>=0){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x + 2)<len&&(p.y = cur.y + 1)<len){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x + 1)<len&&(p.y = cur.y - 2)>=0){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        if((p.x = cur.x + 1)<len&&(p.y = cur.y + 2)<len){
            if(!visited[p.x][p.y]) {
                ps.add(new Point(p));
            }
        }
        return ps;
    }
    public void horseChessboard(int x,int y,int step){
        Point cur = new Point(x,y);
        chess[x][y] = step;
        visited[x][y] = true;
        List<Point> ps = next(cur);
        while(!ps.isEmpty()){
            Point p = ps.remove(0);
            //如果没有访问，就继续进行
            if(!visited[p.x][p.y]){
                horseChessboard(p.x,p.y,step+1);
            }
        }
        //判断马儿是否完成了任务
        if(step<len*len&&!finished){
            //未完成 回溯
            chess[x][y] = 0;
            visited[x][y] = false;
        }else{
            finished = true;
        }
    }

    public void print() {
        for (int[] ints : chess) {
            System.out.println(Arrays.toString(ints));
        }
    }
}