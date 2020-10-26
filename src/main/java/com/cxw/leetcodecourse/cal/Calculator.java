package com.cxw.leetcodecourse.cal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-10-22 14:19
 * @description 字符串计算器
 */
public class Calculator {
    public static void main(String[] args) {
//        String str = "5 + 3 * 2 + 1 / 1";
//        int res = calculateWithoutBracket(str);
//        System.out.println(res);
//        str = "5 + 3 * ( 2 + 1 ) / 1";
        String str = "43 * ( 2 + 4 ) + 2 * 32 / ( 3 - 1 )";
        int res = calculate(str);
        System.out.println(res);
        res = rightCalculate(str);
        System.out.println(res);
    }

    public static int rightCalculate(String str) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object result = engine.eval(str);
            return (Integer) result;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return Integer.MIN_VALUE;
    }


    //包含括号
    private static int calculate(String str) {
        String[] ary = str.split(" ");
        int[] res = doCal(ary,0);
        return res[0];
    }

    private static int[] doCal(String[] ary, int i) {
        LinkedList<String> que = new LinkedList<>();
        while(i<ary.length){
            String str = ary[i];
            if(isOper(str)) {
                if(!isPriorityOper(str)){
                    que.add(str);
                    i++;
                }else{
                    int num1 = Integer.parseInt(que.removeLast());
                    int num2;
                    if("(".equals(ary[i+1])){
                        int[] res = doCal(ary,i+1);
                        num2 = res[0];
                        i = res[1];
                    }else {
                        num2 = Integer.parseInt(ary[i + 1]);
                        i += 2;
                    }
                    int r = cal(num1, num2, str);
                    que.add(String.valueOf(r));
                }
            }else if("(".equals(str)){
                int[] res = doCal(ary,i+1);
                que.add(String.valueOf(res[0]));
                i = res[1];
                break;
            }else if(")".equals(str)){
                i++;
                break;
            }else {
                que.add(str);
                i++;
            }
        }
        while(que.size()>1){
            int num1 = Integer.parseInt(que.pop());
            String oper = que.pop();
            int num2 = Integer.parseInt(que.pop());
            int res = cal(num2,num1,oper);
            que.add(String.valueOf(res));
        }
        return new int[]{Integer.parseInt(que.pop()),i};
    }

    //无括号
    private static int calculateWithoutBracket(String str) {
        String[] strAry = str.split(" ");
        Stack<Integer> numStack = new Stack<>();
        Stack<String> operStack = new Stack<>();
        for (int i = 0; i < strAry.length; i++) {
            if(isOper(strAry[i])){
                if(isPriorityOper(strAry[i])){
                    Integer num1 = numStack.pop();
                    Integer num2 = Integer.parseInt(strAry[i+1]);
                    int res = cal(num1,num2,strAry[i]);
                    numStack.push(res);
                    i++;
                }else{
                    operStack.push(strAry[i]);
                }
            }else{
                numStack.push(Integer.parseInt(strAry[i]));
            }
        }
        while(!operStack.isEmpty()){
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            String oper = operStack.pop();
            int res = cal(num1,num2,oper);
            numStack.push(res);
        }
        return numStack.pop();
    }

    public static int cal(Integer num1, Integer num2, String oper) {
        if(oper.equals("+")){
            return num1+num2;
        }else if(oper.equals("-")){
            return num1-num2;
        }else if(oper.equals("*")){
            return num2*num1;
        }else if(oper.equals("/")){
            return num1/num2;
        }
        throw new RuntimeException("不支持的操作符！");
    }

    public static boolean isPriorityOper(String s) {
        return (s.equals("*")||s.equals("/"));
    }

    public static boolean isOper(String s) {
        return (s.equals("*")||s.equals("/")||s.equals("+")||s.equals("-"));
    }

}