package cn.edu.szu.mytestproject.sort;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 链表快速排序
 */
public class LinkListQuickSort {
    private class LinkListNode{
        public int data;
        public LinkListNode next;

        public LinkListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkListNode partition(LinkListNode head, LinkListNode tail) {
        int x = head.data;
        LinkListNode temp1 = head;
        LinkListNode temp2 = temp1.next;
        while (temp2 != tail) {
            if (temp2.data < x) {
                temp1 = temp1.next;
                int tempData = temp1.data;
                temp1.data = temp2.data;
                temp2.data = tempData;
            }
            temp2 = temp2.next;
        }
        int tempData = temp1.data;
        temp1.data = head.data;
        head.data = tempData;
        return head;
    }

    public void quickSort(LinkListNode head, LinkListNode tail) {
        if (head != tail) {
            LinkListNode node = partition(head, tail);
            quickSort(head,node);
            quickSort(node.next, tail);
        }
    }

    public LinkListNode quiclList(LinkListNode head) {
        quickSort(head, null);
        return head;
    }
}
