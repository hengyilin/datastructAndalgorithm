package cn.edu.szu.mytestproject.stack;

import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-9-30
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 可以获取最小值的栈结构 两种处理方式
 */
public class GetMinNumberStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinNumberStack(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("The stack is empty");
        }
        int value = this.stackData.pop();
        if (value == this.stackMin.peek()) {
            stackMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("There is no min number");
        }
        return this.stackMin.peek();
    }

    public void push2(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        }
        if (newNum >= this.stackMin.peek()) {
            this.stackMin.push(this.stackMin.peek());
        } else {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop2() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("The stack is empty");
        } else {
            this.stackMin.pop();
        }
        return this.stackData.pop();
    }

}
