package com.cxw.leetcode.num;

import java.math.BigInteger;

/**
 * @author chengxuwei
 * @date 2021-02-22 16:18
 * @description 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class StringToInt {
    public static void main(String[] args) {
        String str = "+1";
        StringToInt sti = new StringToInt();
        int res = sti.myAtoi(str);
        System.out.println(res);
    }
    public int myAtoi(String s) {
        s = s.trim();
        s = sub(s);
        BigInteger num = new BigInteger(s);
        num = minMax(num);
        return num.intValue();
    }
    public String sub(String str){
        for(int i=0;i<str.length();i++){
            if(i==0){
                if(str.charAt(i)=='-') {
                    if (str.length()>i+1&&str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9') {
                    } else {
                        return "0";
                    }
                }else if(str.charAt(i)=='+'){
                    if (str.length()>i+1&&str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9') {
                    } else {
                        return "0";
                    }
                }else if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                }else{
                    return "0";
                }
            }else{
                if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                }else{
                    return str.substring(0,i);
                }

            }
        }
        return str;
    }

    public BigInteger minMax(BigInteger num){
        BigInteger min = new BigInteger(String.valueOf(Integer.MIN_VALUE));
        BigInteger max = new BigInteger(String.valueOf(Integer.MAX_VALUE));
        if(num.compareTo(min)<0){
            return min;
        }else if(num.compareTo(max)>0){
            return max;
        }else{
            return num;
        }
    }
}