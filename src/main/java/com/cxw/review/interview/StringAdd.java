package com.cxw.review.interview;

import java.math.BigInteger;

/**
 * @author chengxuwei
 * @create 2022/3/17 12:16
 * @desc  两个字符串大数相加  禁止使用 Integer.parse
 */
public class StringAdd {
    public static void main(String[] args) {
        String str1= "711256323215416751437";
        String str2= "711256323215416751437";
        String res = add(str1,str2);
        BigInteger b1 = new BigInteger(str1);
        BigInteger b2 = new BigInteger(str2);
        BigInteger b3 = b1.add(b2);
        System.out.println(res);
        System.out.println(b3);
    }

    private static String add(String str1, String str2) {
        if(str1==null||str1.length()==0){
            return str2;
        }else if(str2==null||str2.length()==0){
            return str1;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        char[] resAry = new char[Math.max(c1.length,c2.length)];
        if(c1.length>=c2.length){
            int add0 = c1.length - c2.length;
            for (int i = 0; i < add0; i++) {
                str2 = "0"+str2;
            }
        }else{
            int add0 = c2.length - c1.length;
            for (int i = 0; i < add0; i++) {
                str1 = "0"+str1;
            }
        }
        c1 = str1.toCharArray();
        c2 = str2.toCharArray();
        int addOne = 0;
        for (int i = c1.length-1; i >= 0; i--) {
            int i1 = c1[i] - '0';
            int i2 = c2[i] - '0';
            int one = i1 + i2 + addOne;
            if (one > 9) {
                one = one - 10;
                addOne = 1;
            } else {
                addOne = 0;
            }
            resAry[i] = (char) ('0' + one);
            if (i == 0 && addOne == 1) {
                return "1" + new String(resAry);
            }
        }
        return new String(resAry);
    }
}
