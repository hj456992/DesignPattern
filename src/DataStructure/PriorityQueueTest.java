package DataStructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 描述:
 * 优先队列（堆）题目练习
 *
 * @author 侯珏
 * @create 2018-11-26 13:55
 */
public class PriorityQueueTest {
    /**
     * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
     *
     * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
     *
     * 示例:
     *
     * int k = 3;
     * int[] arr = [4,5,8,2];
     * KthLargest kthLargest = new KthLargest(3, arr);
     * kthLargest.add(3);   // returns 4
     * kthLargest.add(5);   // returns 5
     * kthLargest.add(10);  // returns 5
     * kthLargest.add(9);   // returns 8
     * kthLargest.add(4);   // returns 8
     * 说明:
     * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
     */
    static class Solution1 {
        /**
         * 用优先队列实现小顶堆（mini heap）,将前k大的元素放入小顶堆中，新来的元素，如果大于当前堆的头，则加入堆中，并剔除
         * 堆头，如果小于则弃掉该元素
         * 时间复杂度为O(N*LogK)
         */
        static class KthLargest {
            int k;
            PriorityQueue<Integer> priorityQueue;

            public KthLargest(int k, int[] nums) {
                priorityQueue = new PriorityQueue<>();
                this.k = k;
                if (nums != null && nums.length > 0) {
                    for (int i = 0; i < nums.length; i++) {
                        if (priorityQueue.size() < k) {
                            priorityQueue.add(nums[i]);
                        } else {
                            int head = priorityQueue.peek();
                            if (head < nums[i]) {
                                priorityQueue.poll();
                                priorityQueue.add(nums[i]);
                            }
                        }
                    }
                }
            }

            public int add(int val) {
                if (priorityQueue.isEmpty() || priorityQueue.size() < k) {
                    priorityQueue.offer(val);
                    return priorityQueue.peek();
                }
                int head = priorityQueue.peek();
                if (head < val) {
                    priorityQueue.poll();
                    priorityQueue.add(val);
                }
                return priorityQueue.peek();
            }
        }

        public static void main(String[] args) {
            int[] arr = new int[4];
            arr[0] = 4;
            arr[1] = 5;
            arr[2] = 8;
            arr[3] = 2;
            KthLargest kthLargest = new KthLargest(3, arr);
            System.out.println(kthLargest.priorityQueue);
            System.out.println(kthLargest.add(3));
            System.out.println(kthLargest.add(5));
            System.out.println(kthLargest.add(10));
            System.out.println(kthLargest.add(9));
            System.out.println(kthLargest.add(4));
        }
    }

    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口最大值。
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 注意：
     *
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
     */
    static class Solution2 {

        /**
         * 解题思路，用双端队列作为滑动窗口，进来的新数与当前滑动窗口的第一个值做比较，如果大于，则将其挤掉，循环判断，直到第一个值为当前滑动窗口的最大值，
         * 这样实现的时候，就可以不用排序，达到线性的时间复杂度
         * 使用双端队列实现的时候双端队列里存储的 数组索引，而不是元素，这样才能实现 O(n) 的时间复杂度
         *
         * 所以双端队列主要是用到了两点：一是在队头取当前窗口最大值，二是新进元素从队尾开始剔除小于自己的元素。
         * 对于大小为N的列表，窗口大小为K，在后面N-K次循环中，每次都会新进一个元素，剔除一个或多个元素，所以总体来看会是O(n)的时间复杂度
         */
        static class SliddingWindow {

            public static int[] maxSlidingWindow(int[] nums, int k) {
                if (nums.length <= 0) {
                    return new int[0];
                }
                Deque<Integer> windows = new ArrayDeque<>();
                int[] result = new int[nums.length - k + 1];
                for (int i = 0; i < nums.length; i ++) {
                    // 清除过期元素
                    if (i >= k && windows.peekFirst() <= i - k) {
                        windows.pollFirst();
                    }
                    // 清除比新插入数小的元素
                    while (!windows.isEmpty() && nums[i] >= nums[windows.getLast()]) {
                        windows.pollLast();
                    }
                    // 将最新一位加入
                    windows.add(i);
                    if (i >= k-1) {
                        result[i - k + 1] = nums[windows.peekFirst()];
                    }
                }
                return result;
            }

            public static void main(String[] args) {
                int[] nums = {1,3,1,2,0,5};
                int[] result = maxSlidingWindow(nums, 3);

                for (int i = 0; i < result.length; i ++) {
                    System.out.println(result[i]);
                }

            }
        }
    }

}
