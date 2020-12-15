package com.cxw.leetcode;


/**
 * @author chengxuwei
 * @date 2020-12-08 14:35
 * @description z字型变换
 *将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 */
public class ZConvert {
    public static void main(String[] args) {
        String s = "AB";
        String s1 = convert(s, 1);
        System.out.println(s1);
    }
    public static String convert(String s, int numRows) {
        String[][] map = convertMap(s,numRows);
        StringBuilder sb = new StringBuilder();
        for (String[] ary : map){
            for (String str : ary) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private static String[][] convertMap(String s, int numRows) {
        String[][] map = new String[numRows][s.length()];
        //填充
        for (String[] ary : map){
            for (int i = 0; i < ary.length; i++) {
                ary[i] = "";
            }
        }
        if(numRows==1) {
            for (int i = 0; i < s.length(); i++) {
                map[0][i] = String.valueOf(s.charAt(i));
            }
            return map;
        }
        int l = 0;
        int r = 0;
        boolean goDown = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(l==0||l==numRows-1){
                goDown = !goDown;
            }
            map[l][r] = String.valueOf(c);
            if(goDown){
                l++;
            }else{
                l--;
                r++;
            }
        }
        return map;
    }
}