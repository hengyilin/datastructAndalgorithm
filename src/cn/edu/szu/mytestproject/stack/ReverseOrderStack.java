package cn.edu.szu.mytestproject.stack;

import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 利用递归函数逆序一个栈结构
 */
public class ReverseOrderStack {
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int value = stack.pop();
        if (stack.empty()) {
            return value;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(value);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        int res = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(res);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 9; i++) {
            stack.push(i);
        }
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
