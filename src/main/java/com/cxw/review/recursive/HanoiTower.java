package com.cxw.review.recursive;

/**
 * @author chengxuwei
 * @date 2020-12-02 16:00
 * @description 汉罗塔
 */
public class HanoiTower {
    public static void main(String[] args) {
        int storey = 3;
        process(storey,'a','b','c');
    }

    private static void process(int storey,char a, char b,char c) {
        if(storey==1){
            System.out.println(a+"->"+b);
        }else{
            //a->c b做备用
            process(storey-1,a,c,b);

            process(1,a,b,c);
            //c->b a备用
            process(storey-1,c,b,a);
        }
    }
}
