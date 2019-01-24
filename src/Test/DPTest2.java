package Test;

import java.util.*;

/**
 * 动态规划练习题2
 * 股票买卖系列
 * Created by houjue on 2018/12/10.
 */
public class DPTest2 {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

     * 注意你不能在买入股票前卖出股票。

     * 示例 1:

     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:

     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    static class Solution1 {

        // DP[i] = max(DP[i-1],val[i] - min[i - 1])
        public int maxProfit(int[] prices) {
            if (prices.length == 0) return 0;

            int minIndex = 0;
            int max = 0;
            for (int i = 1; i < prices.length; i ++) {
                int profit = prices[i] - prices[minIndex];
                if (profit > 0) {
                    max = Math.max(max, profit);
                } else {
                    minIndex = i;
                }
            }
            return max;
        }
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     * 示例 1:

     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:

     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:

     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    static class Solution2 {
        // 标准贪心，昨天价格大于今天即可卖出
        public int maxProfit(int[] prices) {
            if (prices.length == 0 || prices.length == 1) {
                return 0;
            }

            int profit = 0;
            for (int i = 1;i < prices.length; i ++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     * 示例 1:

     * 输入: [3,3,5,0,0,3,1,4]
     * 输出: 6
     * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2:

     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:

     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
     * (背)
     */
    static class Solution3 {
        // 因为只能交易两笔，且买入必须在卖出之前，定义DP状态为DP[i][2][2]表示第i天第1笔或第2笔没有股票的利润或者持有股票的利润
        // ，第一个维度代表天
        // ，第二个维度代表k笔交易(k>=0,k<=2)
        // ，第三个维度代表当前交易状态（0，没有股票；1，持有股票）
        // DP[i][0][0] = DP[i-1][0][0] ：第i天第一笔没有持有股票，那么可能性只有一种，即i-1天第一笔也不持有股票
        // DP[i][0][1] = MAX(DP[i-1][0][1],DP[i-1][0][0] - val(i)) ：如果第i天第一笔持有股票，那么这一笔的最大值为，即i-1天持有股票，或者i-1天不持有股票，第i天买入股票，取这二者的最大值
        // DP[i][1][0] = MAX(DP[i-1][1][0],DP[i-1][0][1] + val(i)) ：如果第i天第二笔没有股票，那么这一笔的最大值为，即i-1天没有股票，或者i-1天持有股票，第i天卖出股票，取这二者的最大值
        // DP[i][1][1] = MAX(DP[i-1][1][1],DP[i-1][1][0] - val(i)) ：如果第i天第二笔持有股票，那么这一笔的最大值为，即i-1天持有股票，或者i-1天不持有股票，第i天买入股票，取这二者的最大值
        // 最终结果 DP[prices.length-1][2][0]
        public static int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }

            int [][][] dp = new int[prices.length][3][3];
            dp[0][0][0] = 0;
            dp[0][0][1] = -prices[0];
            dp[0][0][2] = 0;
            int max = 0;
            for (int i = 1; i < prices.length; i ++) {
                for (int k = 1; k <= 2; k ++) {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k][0] - prices[i]);
                    dp[i][k][2] = dp[i - 1][k - 1][1] + prices[i];
                    int t1 = dp[i][k][0];
                    int t2 = dp[i][k][1];
                    int t3 = dp[i][k][2];
                    max = Math.max(max, Math.max(t1, Math.max(t2, t3)));
                }

            }
            return max;
        }

        // answer from leetcode
        // DP状态：DP[k,i]表示第i天k笔交易的最大利润
        // DP方程：DP[k,i]=Max(DP[k,i-1],price[i]-price[j]+DP[k-1][j-1]),j=[0,i-1]
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution
        public static int maxProfit1(int[] prices) {
            if (prices.length == 0) return 0;
            int[][] dp = new int[3][prices.length];
            for (int k = 1; k <= 2; k++)  {
                int min = prices[0];
                for (int i = 1; i < prices.length; i++) {
                    min = Math.min(min, prices[i] - dp[k-1][i-1]);
                    dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
                }
            }

            return dp[2][prices.length - 1];
        }

        public static void main(String[] args) {
            int[] prices = new int[]{1,2,4,2,5,7,2,4,9,0};
            System.out.println(maxProfit1(prices));
        }
    }

    /**
     * 最长上升子序列
     *
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。

     * 示例:

     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:

     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n^2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     */
    static class Solution4 {

        // 暴力求解
        public static int lengthOfLIS(int[] nums) {
            return lengthofLIS(nums, Integer.MIN_VALUE, 0);
        }

        public static int lengthofLIS(int[] nums, int prev, int curpos) {
            if (curpos == nums.length) {
                return 0;
            }
            int taken = 0;
            if (nums[curpos] > prev) {
                taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
            }
            int nottaken = lengthofLIS(nums, prev, curpos + 1);
            return Math.max(taken, nottaken);
        }

        // 暴力带缓存求解
        public int lengthOfLIS2(int[] nums) {
            int memo[][] = new int[nums.length + 1][nums.length];
            for (int[] l : memo) {
                Arrays.fill(l, -1);
            }
            return lengthofLIS2(nums, -1, 0, memo);
        }
        public int lengthofLIS2(int[] nums, int previndex, int curpos, int[][] memo) {
            if (curpos == nums.length) {
                return 0;
            }
            if (memo[previndex + 1][curpos] >= 0) {
                return memo[previndex + 1][curpos];
            }
            int taken = 0;
            if (previndex < 0 || nums[curpos] > nums[previndex]) {
                taken = 1 + lengthofLIS2(nums, curpos, curpos + 1, memo);
            }

            int nottaken = lengthofLIS2(nums, previndex, curpos + 1, memo);
            memo[previndex + 1][curpos] = Math.max(taken, nottaken);
            return memo[previndex + 1][curpos];
        }

        // dp
        // dp[i]=max(dp[j])+1 j>=0 && j < i
        // LISlength=max(dp[i])
        public int lengthOfLISDP(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxans = 1;
            for (int i = 1; i < dp.length; i++) {
                int maxval = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        maxval = Math.max(maxval, dp[j]);
                    }
                }
                dp[i] = maxval + 1;
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }

        public static void main(String[] args) {
            System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        }
    }

    /**
     * 10. 正则表达式匹配
     * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

     * '.' 匹配任意单个字符。
     * '*' 匹配零个或多个前面的元素。
     * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

     * 说明:

     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:

     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:

     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
     * 示例 3:

     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
     * 示例 4:

     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:

     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     */
    static class Solution5 {

    }
}
