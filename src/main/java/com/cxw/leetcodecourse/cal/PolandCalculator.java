package com.cxw.leetcodecourse.cal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-10-23 14:28
 * @description 逆波兰算法
 */
public class PolandCalculator {
    public static void main(String[] args) {
        String str = "43 * ( 2 + 4 ) + 2 * 32 / ( 65 - 1 )";
        int res = calculate(str);
        System.out.println(res);
        res = Calculator.rightCalculate(str);
        System.out.println(res);
    }

    private static int calculate(String str) {
        String[] ary = str.split(" ");
        List<String> newAry = transferPostAry(ary);
        return calculatePostAry(newAry);
    }

    private static int calculatePostAry(List<String> newAry) {
        Stack<Integer> numStack = new Stack<>();
        for (String str : newAry) {
            if(Calculator.isOper(str)){
                Integer num1 = numStack.pop();
                Integer num2 = numStack.pop();
                int res = Calculator.cal(num2,num1,str);
                numStack.add(res);
            }else{
                numStack.add(Integer.parseInt(str));
            }
        }
        return numStack.pop();
    }

    private static List<String> transferPostAry(String[] ary) {
        List<String> list = new ArrayList<>();
        Stack<String> operStack = new Stack<>();
        for (String str : ary) {
            if(Calculator.isOper(str)){
                while(!operStack.isEmpty()&&comparePriority(operStack.peek(), str)) {
                    list.add(operStack.pop());
                }
                operStack.add(str);
            }else if("(".equals(str)){
                operStack.push(str);
            }else if(")".equals(str)){
                while(!operStack.peek().equals("(")){
                    list.add(operStack.pop());
                }
                operStack.pop();
            }else{
                list.add(str);
            }
        }
        while(!operStack.isEmpty()){
            list.add(operStack.pop());
        }
        return list;
    }

    private static boolean comparePriority(String oper1, String oper2) {
        return Calculator.isPriorityOper(oper1);
    }


}