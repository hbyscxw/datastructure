package com.cxw.datastructurenew.tree;

/**
 * @author chengxuwei
 * @create 2022/3/15 14:45
 * @desc 前缀树
 */
public class TireTree {
    private TireNode root;
    public TireTree(){
        root = new TireNode();
    }
    public void addWord(String word){
        if(word == null){
            return;
        }
        char[] chars = word.toCharArray();
        TireNode cur = root;
        cur.pass = 1;
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TireNode();
            }
            cur = cur.nexts[index];
            cur.pass += 1;
        }
        cur.end+=1;
    }

    //查询word加过几次
    public int search(String word){
        if(word == null){
            return 0;
        }
        char[] chars = word.toCharArray();
        TireNode cur = root;
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (cur.nexts[index] == null) {
                return 0;
            }
            cur = cur.nexts[index];
        }
        return cur.end;
    }

    //查询以pre为前缀的字符串个数
    public int prefixNumber(String pre){
        if(pre == null){
            return 0;
        }
        char[] chars = pre.toCharArray();
        TireNode cur = root;
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (cur.nexts[index] == null) {
                return 0;
            }
            cur = cur.nexts[index];
        }
        return cur.pass;
    }

    //删除
    public void delete(String word){
        if(word == null || search(word)<1){
            return ;
        }
        char[] chars = word.toCharArray();
        TireNode cur = root;
        cur.pass--;
        for (int i=0;i<chars.length;i++) {
            char aChar = chars[i];
            int index = aChar - 'a';
            //下级节点pass--
            cur.nexts[index].pass--;
            if(cur.nexts[index].pass <= 0){ //下级节点 pass <= 0 删除下级节点
                cur.nexts[index] = null;
                return;
            }
            cur = cur.nexts[index];
        }
        cur.end--;
    }
}
