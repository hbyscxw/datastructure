package com.cxw.datastructure.stack;

/**
 * @author chengxuwei
 * @date 2020-06-08 11:33
 * @description 中缀表达式  有问题
 */
public class SimpleCalculator {
    public static void main(String[] args) {
        String exp = "7 - 3 * 3 - 3 + 4";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        String[] expAry = exp.split(" ");
        for(int i=0;i<expAry.length;i++){
            String expOne = expAry[i];
            if(isOper(expOne)){
                char oper = expOne.charAt(0);
                if(operStack.isEmpty()){
                    operStack.push(oper);
                }else{
                    char ch = (char) operStack.peek();
                    if(comparePriority(oper,ch)){
                        //当前优先级低
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        ch = (char)operStack.pop();
                        int res = calculate(num1,num2,ch);
                        numStack.push(res);
                        operStack.push(oper);
                    }else{
                        operStack.push(oper);
                    }
                }
            }else{
                int value = Integer.parseInt(expOne);
                numStack.push(value);
            }
        }

        while(numStack.size()>1){
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char oper = (char) operStack.pop();
            int res = calculate(num1,num2,oper);
            numStack.push(res);
        }

        System.out.println(exp+"="+numStack.pop());
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

    private static boolean comparePriority(char oper1, char oper2) {
        int priority1 = priority(oper1);
        int priority2 = priority(oper2);
        return priority1-priority2<0;
    }
    private static int priority(char oper1) {
        int priority1 = -1;
        if(oper1=='/'||oper1=='*'){
            priority1 = 1;
        } else if(oper1=='+'||oper1=='-'){
            priority1 = 0;
        }
//        if(oper1=='/'){
//            priority1 = 4;
//        } else if(oper1=='*'){
//            priority1 = 3;
//        } else if(oper1=='-'){
//            priority1 = 2;
//        } else if(oper1=='+'){
//            priority1 = 1;
//        }
        return priority1;
    }

    private static boolean isOper(String str) {
        return "+".equals(str)||"-".equals(str)||"*".equals(str)||"/".equals(str);
    }
}
class ArrayStack2 {
    private int maxSize;
    private int top;
    private int ary[];

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        top = -1;
        ary = new int[maxSize];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，不能再添加元素！");
            return;
        }
        top++;
        ary[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，不能再弹出元素！");
        }
        int value = ary[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，不能再弹出元素！");
        }
        return ary[top];
    }

    public void show() {
        for (int i = top; i >= 0; i--) {
            System.out.println(ary[i]);
        }
    }

    public int size(){
        return top+1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}