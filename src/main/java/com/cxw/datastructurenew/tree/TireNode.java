package com.cxw.datastructurenew.tree;

/**
 * @author chengxuwei
 * @create 2022/3/15 14:46
 * @desc
 */
public class TireNode {
    public int pass;// 路过的次数
    public int end;//字符串结尾的次数
    public TireNode[] nexts;//路数  a~z
    public TireNode(){
        pass = 0;
        end = 0;
        //nexts[0] 走向 'a'的路
        //nexts[1] 走向 'b'的路
        // ...
        nexts = new TireNode[26];
    }
}
