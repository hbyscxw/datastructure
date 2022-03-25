package com.cxw.datastructurenew.recursive;

/**
 * @author chengxuwei
 * @create 2022/3/24 10:11
 * @desc 马踏棋盘算法
 *  马从 (0,0)位置 走到 (x,y) 只能走k步，问有多少种走法
 */
public class HorseBoard {
    public static void main(String[] args) {

    }
    public static int process(int x,int y,int step) {
        if(x<0||x>8||y<0||y>9){
            return 0;
        }
        if(step == 0){
            return (x==0&&y==0)? 1 : 0;  //(0,0)初始位置
        }
        return process(x+1,y+2,step-1)+
                process(x+2,y+1,step-1)+
                process(x+1,y-2,step-1)+
                process(x+2,y-1,step-1)+
                process(x-1,y+2,step-1)+
                process(x-2,y+1,step-1)+
                process(x-1,y-2,step-1)+
                process(x-2,y-1,step-1);

    }
}
