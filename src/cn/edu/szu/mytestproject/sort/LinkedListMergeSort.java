package cn.edu.szu.mytestproject.sort;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 链表归并排序
 */
public class LinkedListMergeSort {
    private class LinkListNode{
        public int data;
        public LinkListNode next;

        public LinkListNode(int data) {
            this.data = data;
        }
    }

    public LinkListNode findMiddle(LinkListNode node) {
        LinkListNode slow, fast; // 快慢指针
        slow = node;
        fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public LinkListNode mergeTwoLinkList(LinkListNode left, LinkListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        LinkListNode temp = new LinkListNode(0); // 保留一个头指针
        LinkListNode head = temp; // 用于移动的指针
        while (left != null && right != null) {
            if (left.data > right.data) {
                head.next = right; // 归并链接
                right = right.next; // 更新右指针
            } else {
                head.next = left; // 归并链接
                left = left.next; // 更新左指针
            }
            head = head.next; // 更新头指针
        }
        //上述循环退出的条件就是其中一个排完
        if (right == null) {
            head.next = left;
        }
        if (left == null) {
            head.next = right;
        }
        return temp.next;
    }

    public LinkListNode mergeSort(LinkListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        LinkListNode middle = findMiddle(node);
        LinkListNode right = mergeSort(middle.next);
        middle.next = null;
        LinkListNode left = mergeSort(node);
        return mergeTwoLinkList(left, right);
    }
    public static void main(String[] args) {

    }
}
