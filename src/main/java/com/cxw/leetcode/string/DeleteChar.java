package com.cxw.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2021-03-09 17:10
 * @description
 */
public class DeleteChar {
    public static void main(String[] args) {
//        String s = "aaaaaaaa";
        String s = "aaayaayaa";
        DeleteChar dc = new DeleteChar();
        String s1 = dc.removeDuplicates(s);
        System.out.println(s1);
    }
    public String removeDuplicates(String S) {
        if(S.length()<=1){
            return S;
        }
        List<Character> list = new ArrayList<>();
        for(int i=0;i<S.length();i++){
            list.add(S.charAt(i));
        }
        return removeDuplicates(list);
    }
    public String removeDuplicates(List<Character> list) {
        int index = list.size()-1;
        while(index<=list.size()-1&&index>0){
            if(list.get(index-1).equals(list.get(index))){
                list.remove(index);
                list.remove(index-1);
                index=Math.min(list.size()-1,index);
            }else {
                index--;
            }
        }
        String newStr = "";
        for(int i=0;i<list.size();i++){
            newStr+=list.get(i);
        }
        return newStr;
    }

    public String removeDuplicates2(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if(stack.size()>0){
                Character pc = stack.peek();
                if(pc.equals(c)){
                    stack.pop();
                    continue;
                }
            }
            stack.add(c);
        }
        String str = "";
        while(!stack.isEmpty()){
            str = stack.pop()+str;
        }
        return str;
    }
}