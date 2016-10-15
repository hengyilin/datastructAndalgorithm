package cn.edu.szu.mytestproject.list_question;

import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-10-14
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 判断两个链表是不是回文链表
 * 注意回文链表是：对称结构
 * 方法一：栈结构
 * 方法二：调整前后部分指针指向
 */
public class PalinddromeTest {
    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void buildList(ListNode head, int value) {

        while (head.next != null) {
            head = head.next;
        }
        head.next = new ListNode(value);
    }

    public static void printList(ListNode head) {
        while (head.next != null) {
            System.out.print(head.next.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static boolean isPalindrome1(ListNode head) {
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode currentNode = head;
        while (currentNode.next != null) {
            nodeStack.push(currentNode.next);
            currentNode = currentNode.next;
        }
        while (head.next != null) {
            if (head.next.value != nodeStack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return true;
        }
        ListNode rightNode = head.next.next;
        ListNode currentNode = head.next;
        // 快慢指针
        while (currentNode != null && currentNode.next.next != null) {
            rightNode = rightNode.next;
            currentNode = currentNode.next.next;
        }
        Stack<ListNode> nodeStack = new Stack<>();
        while (rightNode.next != null) {
            nodeStack.push(rightNode);
            rightNode = rightNode.next;
        }
        nodeStack.push(rightNode);
        while (!nodeStack.empty()) {
            if (head.next.value != nodeStack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome3(ListNode head) {
        // 链表为空或者链表只有一个元素都是回文表
        if (head.next == null || head.next.next == null) {
            return true;
        }
        // 快慢指针
        ListNode slow = head.next; // 慢指针
        ListNode fast = head.next; // 快指针
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;// 复用快指针
        slow.next = null; // 中点的next指针指向null
        ListNode temp = null;
        // 右半区反转
        while (fast != null) {
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        temp = slow; //保存最后一个节点的引用 此时slow记录这组后一个节点的引用
        fast = slow;
        slow = head.next; // slow从头开始准备判断 此时fast就是最后一个节点
        boolean res = true;
        while (slow != null && fast != null) {
            if (slow.value != fast.value) {
                res = false;
                break; // 退出循环准备还原链表
            }
            slow = slow.next;
            fast = fast.next;
        }
        // 判断完毕准备还原链表
        fast = temp;// temp记录这最后一个元素的引用
        temp = null;
        while (fast != null) {
            slow = fast.next;
            fast.next = temp;
            temp = fast;
            fast = slow;
        }
        System.out.println("调整后的元素为：");
        printList(head);
        return res;
    }

    public static void main(String[] args) {
        int[] value = {1, 2, 3, 3, 2, 1};
        ListNode head = new ListNode(0);
        for (int i : value) {
            buildList(head, i);
        }
        printList(head);
        System.out.println("链表是不是回文表？" + isPalindrome3(head));

    }
}
