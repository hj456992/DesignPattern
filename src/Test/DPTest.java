package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by houjue on 2018/12/6.
 */
public class DPTest {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

     * 注意：给定 n 是一个正整数。

     * 示例 1：

     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：

     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    static class Solution1 {
        static int count = 0;
        // digui
        public static int climbStairs(int n) {
            if (n == 0) {
                return 0;
            }
            climb(0, n);
            return count;
        }

        // t：当前所在楼层，n：总楼层
        private static void climb(int t, int n) {
            if (t == n) {
                count ++;
            } else {
                climb(t + 1, n);
                if (t + 2 <= n) {
                    climb(t + 2, n);
                }
            }
        }

        // dp climb(n) = climb(n-1) + climb(n-2)
        public static int climbStairs1(int n) {
            if (n == 0) {
                return 0;
            }
            int a = 1;
            int b = 2;
            int c = 0;
            for (int i = 0; i < n; i ++) {
                if (i == 0) {
                    c = a;
                } else if (i == 1) {
                    c = b;
                } else {
                    c = a + b;
                    a = b;
                    b = c;
                }
            }
            return c;
        }

        public static void main(String[] args) {
            long current = System.currentTimeMillis();
            System.out.println(climbStairs(44));
            System.out.println(System.currentTimeMillis() - current);
            current = System.currentTimeMillis();
            System.out.println(climbStairs1(44));
            System.out.println(System.currentTimeMillis() - current);
        }
    }

    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

     * 例如，给定三角形：

     * [
     *    [2],
     *   [3,4],
     *  [6,5,7],
     * [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

     * 说明：

     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     */
    static class Solution2 {

        // DP方程：DP[i,j] = min(DP[i + 1,j], DP[i + 1,j + 1]) + value(i,j)
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) {
                return 0;
            }
            if (triangle.size() == 1) {
                return triangle.get(0).get(0);
            }

            int[] res = new int[triangle.size()];

            for (int i = triangle.size() - 1; i >= 0; i --) {
                for (int j = 0; j < triangle.get(i).size(); j ++) {
                    if (i == triangle.size() - 1) {
                        res[j] = triangle.get(i).get(j);
                    } else {
                        res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
                    }
                }
            }

            return res[0];
        }
    }

    /**
     * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

     * 示例 1:

     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:

     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */
    static class Solution3 {
        // DP状态：DP[i] 到i位置连续子序列乘积的最大值，因为第i位置可能为正，也可能为负，所以，如果乘积为正，则是当前位置连续子序列最大值，为负，则是最小值
        // 因此，DP状态定义为，DP[i][0]当前乘积子序列最大值，DP[i][1]当前乘积子序列最小值
        // DP方程为：
        // DP[i][0] = nums[i] > 0 ? Math.max(dp[y][0] * nums[i], nums[i]) : Math.max(dp[y][1] * nums[i], nums[i])
        // DP[i][1] = nums[i] > 0 ? Math.min(dp[y][1] * nums[i], nums[i]) : Math.min(dp[y][0] * nums[i], nums[i])
        // max = max(DP[i][0])
        public static int maxProduct1(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int[][] dp = new int[2][2];
            dp[0][0] = nums[0];
            dp[0][1] = nums[0];
            int max = nums[0];
            for (int i = 1;i < nums.length; i ++) {
                int x = i % 2;
                int y = (i - 1) % 2;
                if (nums[i] > 0) {
                    dp[x][0] = Math.max(dp[y][0] * nums[i], nums[i]);
                    dp[x][1] = Math.min(dp[y][1] * nums[i], nums[i]);
                } else {
                    dp[x][0] = Math.max(dp[y][1] * nums[i], nums[i]);
                    dp[x][1] = Math.min(dp[y][0] * nums[i], nums[i]);
                }
                max = Math.max(max, dp[x][0]);
            }
            return max;
        }

        public static void main(String[] args) {
            int[] nums = new int[]{2,-5,-2,-4,3};
            System.out.println(maxProduct1(nums));
        }
    }

    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

     * 示例：

     * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     * 说明:

     * 你可以假设数组不可变。
     * 会多次调用 sumRange 方法。

     */
    // 背下来
    static class NumArray {
        int[] sums;
        /**
         * Your NumArray object will be instantiated and called as such:
         * NumArray obj = new NumArray(nums);
         * int param_1 = obj.sumRange(i,j);
         */
        public NumArray(int[] nums) {
            sums = new int[nums.length];
            if (sums.length == 0) return;

            sums[0] = nums[0];
            for (int i = 1; i < nums.length; i ++) {
                sums[i] = sums[i - 1] + nums[i];
            }
        }

        // DP方程，DP[k] = DP[k-1] + val(k)
        public int sumRange(int i, int j) {
            return i == 0 ? sums[j] : sums[j] - sums[i - 1];

        }
    }

    /**
     * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。

     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

     * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。



     * 示例 1：

     * 输入：people = [1,2], limit = 3
     * 输出：1
     * 解释：1 艘船载 (1, 2)
     * 示例 2：

     * 输入：people = [3,2,2,1], limit = 3
     * 输出：3
     * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
     * 示例 3：

     * 输入：people = [3,5,3,4], limit = 5
     * 输出：4
     * 解释：4 艘船分别载 (3), (3), (4), (5)
     */
    static class Solution4 {

        /**
         * greedy 最胖的人和最瘦的人公用一条船，如果不可以，胖的人单独一条船，然后找轻一些的人
         */
        public static int numRescueBoats(int[] people, int limit) {
            if (people.length <= 1) {
                return people.length;
            }
            Arrays.sort(people);
            // 船数量
            int count = 0;
            // 瘦子的指针
            int i = 0;
            // 胖子的指针
            int j = people.length - 1;
            while (i <= j) {
                count ++;
                if (people[i] + people[j] <= limit) {
                    i ++;
                }
                j --;
            }

            return count;
        }

        public static void main(String[] args) {
            System.out.println(numRescueBoats(new int[]{6,6,6,6,2,3,1}, 6));
        }
    }


    /**
     * 4扩展，一艘船可以载多人
     */
    static class Solution5 {
        // DP状态定义
        public static int numRescueBoats(int[] people, int limit) {

            return 0;
        }
    }
}
