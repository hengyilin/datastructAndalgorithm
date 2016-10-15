package cn.edu.szu.mytestproject.list_question;

/**
 * Author : hengyilin
 * Date   : 16-10-14
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 打印有序链表的公共部分
 * 注意这里强调有序，只有有序才会正确执行
 */
public class PrintListCommonPart {
    static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void buildListNode(ListNode head, int data) {
        while (head.next != null) {
            head = head.next;
        }
        head.next = new ListNode(data);
    }

    public void printListNode(ListNode head) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (head.next != null) {
            System.out.print(head.next.data + " ");
            head = head.next;
        }
        System.out.println();

    }

    /**
     * 打印链表的公共部分
     * 相同则打印否则移动指针
     *
     * @param head1
     * @param head2
     */
    public void printCommonPart(ListNode head1, ListNode head2) {
        System.out.println("The Common part is :");
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                head1 = head1.next;
            } else if (head1.data < head2.data) {
                head2 = head2.next;
            } else {
                System.out.println("Common part is :" + head1.data);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array2 = {2,3,5,6,8,9,12,56};
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        PrintListCommonPart part = new PrintListCommonPart();
        for (int i : array) {
            part.buildListNode(head1, i);
        }
        for (int i : array2) {
            part.buildListNode(head2, i);
        }
        part.printListNode(head1);
        part.printListNode(head2);
        part.printCommonPart(head1.next, head2.next);
    }
}
