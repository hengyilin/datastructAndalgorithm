package cn.edu.szu.mytestproject.stack;

import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 两个栈组成一个队列
 * 栈：先进后出
 * 队列：先进先出
 * 实现思路：利用一个栈来试下倒序
 * 注意：1、把一个栈的数据倒到另一个栈中时要注意只有当另一个栈为空时才可以倒
 *      2、把一个栈的数据倒到另一个栈时要一次性全部倒空不能有剩下！！
 */
public class TwoStackBuildQueue {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public int poll() {
        if (stack2.empty()) {
            if (stack1.empty()) {
                throw new RuntimeException("There is not number");
            } else {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
        return stack2.pop();
    }

    public void add(int number) {
        stack1.push(number);
    }

    public int peek() {
        if (stack2.empty()) {
            if (stack1.empty()) {
                throw new RuntimeException("There is not number in stack");
            } else {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
        return stack2.peek();
    }

    public boolean isEmpty() {
        if (stack2.empty() && stack1.empty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TwoStackBuildQueue queue = new TwoStackBuildQueue();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : array) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            System.out.println("队列的值为：" + queue.poll());
        }
    }
}
