package com.cxw.leetcode;

import java.math.BigInteger;

/**
 * @author chengxuwei
 * @date 2020-12-15 16:28
 * @description 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 */
public class IncreaseNum {
    public static void main(String[] args) {
        int num = 10;
        int newNum = getIncreaseNum(num);
        System.out.println(newNum);
    }

    private static int getIncreaseNum(int num) {
        boolean f = isIncreaseNum(num);
        if(f){
            return num;
        }
        int maxNewNum = 0;
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            String newNumStr = changeNum(str,i);
            int newNum = Integer.parseInt(newNumStr);
            if(newNum<=num&&maxNewNum<newNum&&isIncreaseNum(newNum)){
                maxNewNum = newNum;
            }
        }
        return maxNewNum;
    }

    private static String changeNum(String str, int i) {
        char[] chars = str.toCharArray();
        for (int j = i; j < chars.length; j++) {
            if(j==i){
                if(chars[i]!='0'){
                    char ch = str.charAt(i);
                    int numI = ((int) ch - (int) ('0')) - 1;
                    chars[j] = (char) (numI + '0');
                }else{
                    chars[j] = '9';
                }
            }else{
                chars[j] = '9';
            }
        }
        return new String(chars);
    }

    private static boolean isIncreaseNum(int num) {
        String str = String.valueOf(num);
        for (int i = 1; i < str.length(); i++) {
            if(str.charAt(i)<str.charAt(i-1)){
                return false;
            }
        }
        return true;
    }
}