package DataStructure;

/**
 * 描述:
 * 链表题目练习
 *
 * @author 侯珏
 * @create 2018-11-23 23:59
 */
public class ListTest {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 反转链表
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    private static class Reversed {

        private static ListNode reverseList(ListNode head) {
            ListNode cur = head;
            ListNode reversedList = null;

            while (cur != null) {
                ListNode temp = reversedList;
                reversedList = cur;
                cur = cur.next;
                reversedList.next = temp;
            }
            return reversedList;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode cur = head;
            for (int i = 2; i <= 5; i++) {
                cur.next = new ListNode(i);
                cur = cur.next;
            }

            ListNode reversedList = reverseList(head);

            while (reversedList != null) {
                System.out.println(reversedList.val);
                reversedList = reversedList.next;
            }
        }
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。(要背)
     *
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * 说明:
     * 你的算法只能使用常数的额外空间。
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    private static class SwapPairs {
        private static ListNode swapPairs(ListNode head) {
            ListNode swapList = new ListNode(0);
            swapList.next = head;
            head = swapList;

            while (head.next != null && head.next.next != null) {
                ListNode a = head.next;
                ListNode b = head.next.next;

                head.next = b;
                a.next = b.next;
                b.next = a;
                head = a;
            }
            return swapList.next;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode cur = head;
            for (int i = 2; i<=4; i++) {
                cur.next = new ListNode(i);
                cur = cur.next;
            }

            ListNode swapList = swapPairs(head);
            while (swapList != null) {
                System.out.println(swapList.val);
                swapList = swapList.next;
            }
        }
    }

    /**
     * 判断链表是否有环
     *
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    private static class Cycle{
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return false;
            }
            ListNode fast = head.next;
            ListNode slow = head.next.next;

            while (true) {
                if (fast.val == slow.val) {
                    return true;
                }
                if (fast.next == null || slow.next == null || slow.next.next == null) {
                    return false;
                }
                fast = fast.next;
                slow = slow.next.next;
            }
        }

        public static void main(String[] args) {

        }
    }
}
