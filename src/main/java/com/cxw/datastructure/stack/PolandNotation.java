package com.cxw.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
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
//        String infixExp = "7 - 3 * ( 3 - 3 ) + 4";
        String infixExp = "43 * ( 2 + 4 ) + 2 * 32 / ( 65 - 1 )";
        System.out.println(infixExp);
        List<String> suffixExp = convertSuffixExp(infixExp);
        System.out.println(suffixExp);
        //  "7 - 3 * 3 - 3 + 4";
//        String expStr = "7 3 3 * - 3 - 4 +";
        Stack<Integer> stack = new Stack<>();
        for(String exp : suffixExp){
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
        System.out.println(suffixExp + "="+stack.pop());
    }

    private static List<String> convertSuffixExp(String infixExp) {
        //符号栈
        Stack<String> s1 = new Stack<>();
        //中间结果栈 用list表示
        List<String> s2 = new ArrayList<>();
        String[] expAry = infixExp.split(" ");
        for(String exp : expAry){
            //如果是数字，直接加入s2
            if(exp.matches("\\d+")){
                s2.add(exp);
            }else if(exp.equals("(")){
                //左括号压入s1
                s1.push(exp);
            }else if(exp.equals(")")){
                //查看s1的元素是否是(
                //  如果不是，循环将s1内的元素弹出加入s2
                // 如果是，将s1的(弹出
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                //比较当前运算符与s1栈顶运算符,当前exp优先级小于或等于s1栈顶优先级，循环将s1内的元素弹出加入s2
                while(s1.size()>0&&comparePriority(exp,s1.peek())){
                    s2.add(s1.pop());
                }
                //将exp加入s1
                s1.push(exp);
            }
        }
        //将剩下的s1内的元素弹出加入s2
        while(s1.size()>0){
            s2.add(s1.pop());
        }
        return s2;
    }

    private static boolean comparePriority(String oper1, String oper2) {
        int priority1 = priority(oper1);
        int priority2 = priority(oper2);
        return priority1-priority2<=0;
    }
    private static int priority(String oper1) {
        int priority1 = -1;
        if("/".equals(oper1)||"*".equals(oper1)){
            priority1 = 1;
        } else if("+".equals(oper1)||"-".equals(oper1)){
            priority1 = 0;
        }
        return priority1;
    }

    private static  boolean isOper(String str) {
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