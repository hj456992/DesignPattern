package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by houjue on 2018/11/15.
 */
public class ListTest {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        // 当前list节点
        ListNode cur = head;
        // 反转后list节点
        ListNode reversed = null;
        while (cur != null) {
            ListNode tempNode = reversed;
            reversed = cur;
            cur = cur.next;
            reversed.next = tempNode;
        }
        return reversed;
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

     * 示例：

     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode curNode1 = l1;
        ListNode curNode2 = l2;

        ListNode newNode = new ListNode(Integer.MIN_VALUE);
        ListNode curNew = newNode;
        while (true) {
            ListNode tempNode1 = curNode1.next;
            ListNode tempNode2 = curNode2.next;
            if (curNode2.val < curNode1.val) {
                curNew.next = curNode2;
                curNew = curNew.next;

                curNode2 = tempNode2;
            } else {
                curNew.next = curNode1;
                curNew = curNew.next;

                curNode1 = tempNode1;
            }
            if (curNode2 == null) {
                curNew.next = curNode1;
                break;
            }
            if (curNode1 == null) {
                curNew.next = curNode2;
                break;
            }
        }
        return newNode.next;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

     * 示例：

     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        ListNode newList = new ListNode(Integer.MIN_VALUE);
        ListNode curNew = newList;
        int temp = 0;
        while (curL1 != null) {
            int sum;
            if (curL2 == null) {
                sum = curL1.val + temp;
                curL1 = curL1.next;
            } else {
                sum = curL1.val + curL2.val + temp;
                curL1 = curL1.next;
                curL2 = curL2.next;
            }
            curNew.next = new ListNode(sum % 10);
            curNew = curNew.next;
            temp = sum / 10;
        }
        while (curL2 != null) {
            int sum = curL2.val + temp;
            curNew.next = new ListNode(sum % 10);
            curNew = curNew.next;
            temp = sum / 10;
            curL2 = curL2.next;
        }
        if (temp > 0) {
            curNew.next = new ListNode(temp);
        }
        return newList.next;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode cur = head;
        while (cur.val != 5) {
            int val = cur.val + 1;
            cur.next = new ListNode(val);
            cur = cur.next;
        }

        ListNode prev = reverseList(head);
        while (prev != null) {
            System.out.print(prev.val);
            prev = prev.next;
        }
//        ListNode l1 = new ListNode(1);
//        ListNode cur = l1;
//        while (cur.val != 3) {
//            int val = cur.val + 1;
//            cur.next = new ListNode(val);
//            cur = cur.next;
//        }
//
//        ListNode l2 = new ListNode(4);
//        ListNode cur1 = l2;
//        while (cur1.val != 9) {
//            int val = cur1.val + 1;
//            cur1.next = new ListNode(val);
//            cur1 = cur1.next;
//        }
//
//        ListNode prev = addTwoNumbers(l1, l2);
//        while (prev != null) {
//            System.out.print(prev.val);
//            prev = prev.next;
//        }

    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

     * 示例：

     * 给定一个链表: 1->2->3->4->5, 和 n = 2.

     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。

     * 进阶：

     * 你能尝试使用一趟扫描实现吗？
     */
    static class Solution1 {
        /**
         * 解题思路：
         * 将L-n位置的节点的next节点从指向L-n+1改为L-n+2
         * @param head
         * @param n
         * @return
         */
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            if (n == 0) {
                return head;
            }
            int length = 0;
            ListNode curNode = head;
            while (curNode != null) {
                curNode = curNode.next;
                length ++;
            }
            int t = length - n;
            if (t == 0) {
                return head.next;
            }
            if (t < 0) {
                return head;
            }
            curNode = head;
            ListNode newList = new ListNode(0);
            ListNode curNew = newList;
            int i = 0;
            while (i != t) {
                curNew.next = curNode;
                curNew = curNew.next;
                curNode = curNode.next;

                i ++;
            }
            curNew.next = curNode.next;
            return newList.next;
        }

        // 双指针解法，指针[i,j]的窗口大小为n+1，j走到最后一个节点时，把i节点的next节点指向next.next节点
        public static ListNode removeNthFromEnd1(ListNode head, int n) {
            if (n == 0) {
                return head;
            }
            int j = 1;
            int i = j - n;
            ListNode curjNode = head;
            ListNode curiNode = new ListNode(Integer.MIN_VALUE);
            ListNode newList = new ListNode(0);
            ListNode cur = newList;
            while (curjNode.next != null) {
                curjNode = curjNode.next;
                if (i == 0) {
                    curiNode = head;
                    cur.next = curiNode;
                    cur = cur.next;
                } else if (i > 0) {
                    curiNode = curiNode.next;
                    cur.next = curiNode;
                    cur = cur.next;
                }
                i ++;
                j ++;
            }
            if (i < 0) {
                return head;
            }
            if (i == 0) {
                return head.next;
            }
            cur.next = curiNode.next.next;
            return newList.next;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode cur = head;
            while (cur.val != 5) {
                int val = cur.val + 1;
                cur.next = new ListNode(val);
                cur = cur.next;
            }

            ListNode newList = removeNthFromEnd1(head, 2);
            while (newList != null) {
                System.out.print(newList.val);
                newList = newList.next;
            }
        }
    }

    /**
     * 23. 合并K个排序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

     * 示例:

     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */
    static class Solution2 {
        // 解法1，暴力解法，取出所有的值，进行快排，然后重新组装链表，时间复杂度O(nlogn),空间复杂度O(n)
        public static ListNode mergeKLists1(ListNode[] lists) {
            List<Integer> vals = new ArrayList<>();
            for (ListNode list : lists) {
                while (list != null) {
                    vals.add(list.val);
                    list = list.next;
                }
            }
            Collections.sort(vals);

            ListNode sortedList = new ListNode(Integer.MIN_VALUE);
            ListNode point = sortedList;
            for (Integer val : vals) {
                point.next = new ListNode(val);
                point = point.next;
            }
            return sortedList.next;
        }

        // 解法2，一个个节点进行比较，所有节点遍历一遍，时间复杂度O(kn)，空间复杂度O(n)，因为用了优先队列/堆
        public static ListNode mergeKLists2(ListNode[] lists) {
            PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a,b) -> a.val > b.val ? 1 : -1);
            for (ListNode list : lists) {
                while (list != null) {
                    queue.offer(list);
                    list = list.next;
                }
            }

            ListNode sortedList = new ListNode(Integer.MIN_VALUE);
            ListNode point = sortedList;
            while (!queue.isEmpty()) {
                point.next = queue.poll();
                point = point.next;
            }
            point.next = null;
            return sortedList.next;
        }

        // 解法3，分治法，将原链表数组拆成一半一半，然后合并，时间复杂度O(nlogk)，空间复杂度O(1)
        public static ListNode mergeKLists3(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }
            if (lists.length <= 2) {
                return mergeLists(lists[0], lists[1]);
            }

            ListNode[] list1 = Arrays.copyOfRange(lists, 0, (lists.length+ 1)/2);
            ListNode[] list2 = Arrays.copyOfRange(lists, (lists.length+ 1)/2, lists.length);

            ListNode l1 = mergeKLists3(list1);
            ListNode l2 = mergeKLists3(list2);

            return mergeLists(l1, l2);
        }

        private static ListNode mergeLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode mergedList = new ListNode(0);
            ListNode point = mergedList;

            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    point.next = l1;
                    point = point.next;
                    l1 = l1.next;
                } else {
                    point.next = l2;
                    point = point.next;
                    l2 = l2.next;
                }
            }
            if (l1 != null) {
                point.next = l1;
            }
            if (l2 != null) {
                point.next = l2;
            }
            return mergedList.next;
        }


        static int[] arr1 = new int[]{-1,-1,-1};
        static int[] arr2 = new int[]{-2,-2,-1};
        static int[] arr3 = new int[]{2,6};

        public static void main(String[] args) {
            ListNode list1 = new ListNode(0);
            ListNode p1 = list1;
            for (int i = 0 ;i < arr1.length; i ++) {
                p1.next = new ListNode(arr1[i]);
                p1 = p1.next;
            }

            ListNode list2 = new ListNode(0);
            ListNode p2 = list2;
            for (int i = 0 ;i < arr2.length; i ++) {
                p2.next = new ListNode(arr2[i]);
                p2 = p2.next;
            }

            ListNode list3 = new ListNode(0);
            ListNode p3 = list3;
            for (int i = 0 ;i < arr3.length; i ++) {
                p3.next = new ListNode(arr3[i]);
                p3 = p3.next;
            }

            ListNode mergedList = mergeKLists3(new ListNode[]{list1.next, list2.next, list3.next});
            while (mergedList != null) {
                System.out.print(mergedList.val);
                mergedList = mergedList.next;
            }
        }
    }

    /**
     * 25. k个一组翻转链表
     * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

     * 示例 :

     * 给定这个链表：1->2->3->4->5

     * 当 k = 2 时，应当返回: 2->1->4->3->5

     * 当 k = 3 时，应当返回: 3->2->1->4->5

     * 说明 :

     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    static class Solution3 {
        // 定义一个[i,j]的区间，j向前移动，j-i+1=k时，反转[i,j]内的节点，然后i，j指向j+1，当j走到末尾时，如果j-i+1<k，则不翻转
        public static ListNode reverseKGroup(ListNode head, int k) {
            ListNode curr = head;
            int count = 0;

            while (curr != null && count < k) {
                curr = curr.next;
                count ++;
            }

            if (count == k) {
                curr = reverseKGroup(curr, k);

                while (count > 0) {
                    count --;
                    ListNode tempNode = curr;
                    curr = head;
                    head = head.next;
                    curr.next = tempNode;
                }
                head = curr;
            }
            return head;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode cur = head;
            while (cur.val != 5) {
                int val = cur.val + 1;
                cur.next = new ListNode(val);
                cur = cur.next;
            }

            ListNode prev = reverseKGroup(head, 3);
            while (prev != null) {
                System.out.print(prev.val);
                prev = prev.next;
            }
        }
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    static class Solution4 {
        public static ListNode deleteDuplicates1(ListNode head) {
            ListNode cur = head;
            ListNode newList = new ListNode(Integer.MIN_VALUE);
            ListNode prev = newList;
            while (cur != null) {
                if (cur.val != prev.val) {
                    prev.next = cur;
                    prev = prev.next;
                }
                cur = cur.next;
            }
            prev.next = null;

            return newList.next;
        }

        // 简化
        public static ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                if (cur.next.val == cur.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }

        public static int[] stringToIntegerArray(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return new int[0];
            }

            String[] parts = input.split(",");
            int[] output = new int[parts.length];
            for(int index = 0; index < parts.length; index++) {
                String part = parts[index].trim();
                output[index] = Integer.parseInt(part);
            }
            return output;
        }

        public static ListNode stringToListNode(String input) {
            // Generate array from the input
            int[] nodeValues = stringToIntegerArray(input);

            // Now convert that list into linked list
            ListNode dummyRoot = new ListNode(0);
            ListNode ptr = dummyRoot;
            for(int item : nodeValues) {
                ptr.next = new ListNode(item);
                ptr = ptr.next;
            }
            return dummyRoot.next;
        }

        public static String listNodeToString(ListNode node) {
            if (node == null) {
                return "[]";
            }

            String result = "";
            while (node != null) {
                result += Integer.toString(node.val) + ", ";
                node = node.next;
            }
            return "[" + result.substring(0, result.length() - 2) + "]";
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                ListNode head = stringToListNode(line);

                ListNode ret = deleteDuplicates(head);

                String out = listNodeToString(ret);

                System.out.print(out);
            }
        }
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     */
    static class Solution5 {
        // 本质上是将n-k位置的节点指向null，最后一个节点指向头结点
        public static ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            // 第一遍遍历，算出head的节点数，以及实际的k值
            int n = 0;
            ListNode node = head;
            while (node != null) {
                n ++;
                node = node.next;
            }
            // 对n取模，并算出从左到右第几个位置进行截断
            k = n - k % n;
            if (k == n) {
                return head;
            }
            ListNode l1 = head;
            ListNode p = l1;
            for (int i = 1; i < k; i ++) {
                p = p.next;
            }
            ListNode l2 = p.next;
            ListNode q = l2;
            p.next = null;
            while (q.next != null) {
                q = q.next;
            }
            q.next = l1;
            return l2;
        }

        public static int[] stringToIntegerArray(String input) {
                input = input.trim();
                input = input.substring(1, input.length() - 1);
                if (input.length() == 0) {
                    return new int[0];
                }

                String[] parts = input.split(",");
                int[] output = new int[parts.length];
                for(int index = 0; index < parts.length; index++) {
                    String part = parts[index].trim();
                    output[index] = Integer.parseInt(part);
                }
                return output;
            }

            public static ListNode stringToListNode(String input) {
                // Generate array from the input
                int[] nodeValues = stringToIntegerArray(input);

                // Now convert that list into linked list
                ListNode dummyRoot = new ListNode(0);
                ListNode ptr = dummyRoot;
                for(int item : nodeValues) {
                    ptr.next = new ListNode(item);
                    ptr = ptr.next;
                }
                return dummyRoot.next;
            }

            public static String listNodeToString(ListNode node) {
                if (node == null) {
                    return "[]";
                }

                String result = "";
                while (node != null) {
                    result += Integer.toString(node.val) + ", ";
                    node = node.next;
                }
                return "[" + result.substring(0, result.length() - 2) + "]";
            }

            public static void main(String[] args) throws IOException {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = in.readLine()) != null) {
                    ListNode head = stringToListNode(line);
                    line = in.readLine();
                    int k = Integer.parseInt(line);

                    ListNode ret = rotateRight(head, k);

                    String out = listNodeToString(ret);

                    System.out.print(out);
                }
            }
    }

    /**
     * 237. 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * 现有一个链表 -- head = [4,5,1,9]
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     *
     * 说明:
     *
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     */
    static class Solution6 {
        // 解题思路，要删除的node节点本身就是head节点的一部分，不用纠结传参没有head，直接node.next = node.next.next
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
