package cn.edu.szu.mytestproject.stack;

import java.util.LinkedList;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 两个队列组成一个栈
 * 注意：队列先进先出
 * 栈后进先出
 */
public class TwoQueueBuildStack {
    private LinkedList<Integer> list1;
    private LinkedList<Integer> list2;

    public TwoQueueBuildStack() {
        this.list1 = new LinkedList<>();
        this.list2 = new LinkedList<>();
    }

    public void push(int number) {
        list1.add(number);
    }

    public int pop() {
        while (list1.size() != 1) {
            list2.add(list1.poll());
        }
        Integer value = list1.poll();
        while (!list2.isEmpty()) {
            list1.add(list2.poll());
        }
        return value;
    }

    public int peek() {
        while (list1.size() != 1) {
            list2.add(list1.poll());
        }
        int value = list1.poll();
        list2.add(value);
        while (!list2.isEmpty()) {
            list1.add(list2.poll());
        }
        return value;
    }

    public boolean isEmpty() {
        if (this.list1.isEmpty() && this.list2.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] atgs) {
        TwoQueueBuildStack stack = new TwoQueueBuildStack();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : array) {
            stack.push(i);
        }
        System.out.println(stack.pop());
        stack.push(10);
        System.out.println(stack.peek());
        stack.push(12);
        stack.push(11);
        System.out.println("栈内的数据是：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
