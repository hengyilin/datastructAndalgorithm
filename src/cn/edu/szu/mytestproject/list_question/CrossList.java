package cn.edu.szu.mytestproject.list_question;

/**
 * Author : hengyilin
 * Date   : 16-10-15
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 两个链表相交的系列问题
 * 问题一：怎么判断两个无环链表是否相交并找出第一个相交的节点
 * 两个链表如果有相交的地方则从相交的地方开始以后都是一样的了，
 * 因此判断的话可以直接判断最后一个节点是否相同
 * 若相同则一定是相交的，但是这样找不到第一个相交的节点，否则不相交
 * 要想找出第一个相交的节点可以按照以下思路来处理：
 * 1、先遍历两个链表找出两个链表的长度，并假设l1>l2
 * 2、长的链表先走l1-l2步，然后两个链表在一起走遇到节点相同
 * 就返回该节点且该节点就是要找的第一个相交的节点
 * 问题二：怎么判断链表是否成环并找出入环的第一个节点
 * 判断是否成环可以按照以下思路判断：
 * 快慢指针p1p2，p1 一次走一步p2一次走两步，如果成环则p1p2一定会在环内相遇
 * 退出的条件是其中一个到达null这样肯定不会是成环的
 * 找出进入环的第一个节点可以按照以下思路来处理：
 * 判断成环时快慢指针会相遇，相遇时慢指针继续按照步长为1走，
 * 快指针重置为head从头开始按照步长为1向下走，
 * 快慢指针肯定会在第一个进入环的节点处相遇，返回该节点
 * 问题三：怎么判断两个有环链表是否相交并找出第一个相交的节点
 * 判断原链表都为有环链表之后要找到每个链表进入环的第一个节点，找到之后根据两个链表进入环的节点展开以下讨论
 * 1、如果两个进入环的节点相同则问题退化成找到两个无环链表的第一个相交点问题了，按照问题一定点思路处理
 * 2、如果两个链表进入环的节点不同则按照以下思路处理：
 * 从其中一个链表进入环的节点中开始遍历链表，
 * 如果在遍历过程中遇到另一个链表进入环的节点则返回两个两个节点的任意一个
 * 如果遍历回到该节点还没有遇到另一个节点则说明两个链表分别成环没有相交
 * <p>
 * 另：
 * 判断是否相交还有其他办法例如可以遍历链表同时在遍历过程中用哈希表存储节点信息然后在遍历另一个链表，
 * 分别在哈希表中查是否有相同的节点，有就说明有相交
 * 判断成环同样可以用哈希表记录节点的信息，每遍历到一个节点就在哈希表中判断之前是否曾经遍历过该节点
 */
public class CrossList {
    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    private ListNode head = null;

    public void buildList(int value) {
        if (head == null) {
            head = new ListNode(value);
            return;
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(value);
    }

    public void printList(ListNode head) {
        if (head == null) {
            System.out.println("链表为空：");
            return;
        }
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
    }

    /**
     * 获取成环链表的进入环的第一个节点
     * @param head
     * @return
     */
    public ListNode getFirstLoopNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head.next.next;
        while (fast != slow) {
            if (fast == null || fast.next.next == null) { // 不是有环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 获取无环链表的相交的第一个节点
     * @param head1
     * @param head2
     * @return
     */
    public ListNode noLoopGetFirstCommonNode(ListNode head1,ListNode head2) {
        if (head == null || head2 == null) {
            return null;
        }
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int len = 0;
        while (cur1.next != null) {
            len++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            len--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = len > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        len = Math.abs(len);
        while (len != 0) {
            cur1 = cur1.next;
            len--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public ListNode getLoopListFirstCommonNode(ListNode head1, ListNode loopNode1, ListNode head2, ListNode loopNode2) {
        ListNode currentNode1 = null;
        ListNode currentNode2 = null;
        if (loopNode1 == loopNode2) { // 两个链表从同一个节点进入环。说明两个链表是在环外相交的
            currentNode1 = head1;
            currentNode2 = head2;
            int len = 0;
            while (currentNode1 != loopNode1) {
                len++;
                currentNode1 = currentNode1.next;
            }
            while (currentNode2 != loopNode2) {
                len--;
                currentNode2 = currentNode2.next;
            }
            currentNode1 = len > 0 ? head1 : head2;
            currentNode2 = currentNode1 == head1 ? head2 : head1;
            len = Math.abs(len);
            while (len != 0) {
                len--;
                currentNode1 = currentNode1.next;
            }
            while (currentNode1 != currentNode2) {
                currentNode1 = currentNode1.next;
                currentNode2 = currentNode2.next;
            }
            return currentNode1;
        }
        else { // 说明两个链表是在环上相交的或者不是相交的
            currentNode1 = loopNode1.next;
            while (currentNode1 != loopNode1) {
                if (currentNode1 == currentNode2) {
                    return currentNode1;
                }
                currentNode1 = currentNode1.next;
            }
            return null;
        }
    }

    public ListNode getFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode loopNode1 = getFirstLoopNode(head1);
        ListNode loopNode2 = getFirstLoopNode(head2);
        if (loopNode1 == null && loopNode2 == null) {
            return noLoopGetFirstCommonNode(head1, head2);
        }
        if (loopNode1 != null && loopNode2 != null) {
            return getLoopListFirstCommonNode(head1, loopNode1, head2, loopNode2);
        }
        return null; // 一个链表有环一个链表无环则不可能会产生相交！！！
    }
}
