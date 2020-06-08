package com.cxw.datastructure.stack;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-06-08 16:35
 * @description 逆波兰计算器
 */
public class PolandNotation {
    public static void main(String[] args) {
        //(5+4)*3-4+1
//        String expStr = "5 4 + 3 * 4 - 1 +";
        //  "7 - 3 * 3 - 3 + 4";
        String expStr = "7 3 3 * - 3 - 4 +";
        Stack<Integer> stack = new Stack<>();
        String[] expAry = expStr.split(" ");
        for(String exp : expAry){
            if(isOper(exp)){
                char oper = exp.charAt(0);
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                int res = calculate(num1,num2,oper);
                stack.push(res);
            }else{
                int value = Integer.parseInt(exp);
                stack.push(value);
            }
        }
        System.out.println(expStr + "="+stack.pop());
    }
    private static boolean isOper(String str) {
        return "+".equals(str)||"-".equals(str)||"*".equals(str)||"/".equals(str);
    }
    private static int calculate(int num1, int num2, char oper) {
        switch (oper){
            case '+':
                return num2+num1;
            case '-':
                return num2-num1;
            case '*':
                return num2*num1;
            case '/':
                return num2/num1;
            default:
                throw new RuntimeException("参数错误");
        }
    }
}