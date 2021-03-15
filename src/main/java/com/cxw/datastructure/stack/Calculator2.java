package com.cxw.datastructure.stack;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2021-03-10 11:40
 * @description 简单计算器
 */
public class Calculator2 {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        Calculator2 c = new Calculator2();
        int res = c.calculate(s);
        System.out.println(res);
    }
    public int calculate(String s) {
        s = s.trim();
        if(s.equals("")){
            return 0;
        }
        if(s.startsWith("-")){
            s = "0"+s;
        }
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        int index = 0;
        while(index<s.length()){
            char c = s.charAt(index);
            if(c==' '){
            }else if(c=='('){
                ops.add(c);
            }else if(c=='+'||c=='-'){
                if(ops.isEmpty()||nums.size()<2){
                    ops.add(c);
                }else {
                    Character o = ops.peek();
                    if (o == '(') {
                        ops.add(c);
                    }else{
                        int num1 = nums.pop();
                        int num2 = nums.pop();
                        int res = 0;
                        if(o.equals('+')){
                            res = num1+num2;
                        }else{
                            res = num2-num1;
                        }
                        nums.add(res);
                        ops.add(c);
                    }
                }
            }else if(c==')'){
                Character o = null;
                while((o = ops.pop())!='('){
                    int num1 = nums.pop();
                    int num2 = nums.pop();
                    int res = 0;
                    if(o=='+'){
                        res = num1+num2;
                    }else{
                        res = num2-num1;
                    }
                    nums.add(res);
                }
            }else if(c>='0'&&c<='9'){
                nums.add(Integer.parseInt(c+""));
            }
            index++;
        }
        Character o = null;
        while(!ops.isEmpty()&&(o = ops.pop())!='('){
            int num1 = nums.pop();
            int num2 = nums.pop();
            int res = 0;
            if(o.equals('+')){
                res = num1+num2;
            }else{
                res = num2-num1;
            }
            nums.add(res);
        }
        return nums.pop();
    }
}