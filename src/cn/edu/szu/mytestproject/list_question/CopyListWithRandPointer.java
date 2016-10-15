package cn.edu.szu.mytestproject.list_question;

import java.util.HashMap;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 复制含有随机指针节点的链表
 * 思路一：利用哈希表维护每个节点与复制节点的映射关系两次遍历原链表第一次记录原链表元素
 * 与复制的链表元素的映射关系，第二次遍历完成的随机指针和next指针的指向
 * <p>
 * 思路二：不用哈希表在遍历原链表元素的过程中每遇到一个元素就在其后新建一个复制节点
 * 第二次遍历时根据前一个节点的next和rand指针调整复制出来的那个节点的指针域
 */
public class CopyListWithRandPointer {

    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode rand;

        public ListNode(int value) {
            this.value = value;
            this.next = null;
            this.rand = null;
        }
    }

    private ListNode head = null;

    public void buildList(int value) {
        if (head == null) {
            head = new ListNode(value);
            return;
        }
        ListNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new ListNode(value);
    }

    public void printList(ListNode head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    public ListNode copyListWithRand1(ListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            map.put(currentNode, new ListNode(currentNode.value)); // 用哈希表维护原链表和复制链表的映射关系
            currentNode = currentNode.next;
        }
        currentNode = head;
        // 通过映射表可以找到原链表元素的复制元素
        // 通过遍历原链表可以找到原链表元素的next指针和rand指针
        // 有了以上的对应关系便可以重新连起来
        while (currentNode != null) {
            map.get(currentNode).next = map.get(currentNode.next);
            map.get(currentNode).rand = map.get(currentNode.rand);
            currentNode = currentNode.next;
        }
        return map.get(head);
    }

    public ListNode copyListWithRand2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode currentNode = head;
        ListNode nextNode = null;
        // 遍历原链表并复制和链接拷贝节点
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = new ListNode(currentNode.value);
            currentNode.next.next = nextNode;
            currentNode = nextNode;
        }
        currentNode = head;
        ListNode currentCopyNode = null;
        // 再次遍历原链表，设置复制元素的rand指针
        while (currentNode != null) {
            nextNode = currentNode.next.next;
            currentCopyNode = currentNode.next;
            currentCopyNode.rand = currentCopyNode.rand == null ? null : currentNode.rand;
            currentNode = nextNode;
        }

        ListNode resultNode = head.next;
        currentNode = head;
        //再次遍历原链表实现复制元素和原元素的分离
        while (currentNode != null) {
            nextNode = currentNode.next.next;
            currentCopyNode = currentNode.next;
            currentNode.next = nextNode;
            currentCopyNode.next = currentCopyNode.next == null ? null : currentCopyNode.next;
            currentNode = nextNode;
        }
        return resultNode;
    }

    public static void main(String[] args) {
        CopyListWithRandPointer instance = new CopyListWithRandPointer();
        int[] array = {1, 2, 5, 6, 3, 7, 8, 9, 4};
        for (int i : array) {
            instance.buildList(i);
        }
        instance.printList(instance.head);
        ListNode listNode = instance.copyListWithRand1(instance.head);
        System.out.println("经过哈希表复制后的对结果是：");
        instance.printList(listNode);
        ListNode listNode1 = instance.copyListWithRand2(instance.head);
        System.out.println("经过一般复制后的对结果是：");
        instance.printList(listNode1);

    }

}
