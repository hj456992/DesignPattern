package Test;

import java.util.*;

/**
 * Created by houjue on 2018/12/13.
 */
public class ByteDanceTest2 {
    static class Solution1 {
        public static int findLengthOfLCIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int max = 1;
            int length = 1;
            for (int i = 0; i < nums.length - 1; i ++) {
                if (nums[i] < nums[i + 1]) {
                    length ++;
                } else {
                    length = 1;
                }
                max = Math.max(max, length);
            }
            return max;
        }

        public static void main(String[] args) {
            System.out.println(findLengthOfLCIS(new int[]{1,3,5,7}));
        }
    }

    /**
     * 最大正方形
     *
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

     * 示例:

     * 输入:

     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0

     * 输出: 4
     */
    static class Solution2 {
        // baoli
        // 双重循环遍历每一个字符，计算所有出现的正方形的面积，选出最大的，很难写
        public int maximalSquare(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int maxLen = 0;
            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < cols; j ++) {
                    if (matrix[i][j] == '1'){
                        int sqlen = 1;
                        boolean flag = true;
                        while (sqlen + i < rows && sqlen + j < cols && flag) {
                            // 同一行所有列都要为1
                            for (int k = j; k <= sqlen + j; k++) {
                                if (matrix[i + sqlen][k] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            // 同一列所有行都要为1
                            for (int k = i; k <= sqlen + i; k++) {
                                if (matrix[k][j + sqlen] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                sqlen++;
                        }
                        if (maxLen < sqlen) {
                            maxLen = sqlen;
                        }
                    }
                }
            }
            return maxLen * maxLen;
        }

        // dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
        // dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1.
        public int maximalSquareDP(char[][] matrix) {
            int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
            int[][] dp = new int[rows + 1][cols + 1];

            int squareBorder = 0;
            for (int i = 1;i <= rows; i ++) {
                for (int j = 1; j <= cols; j ++) {
                    if (matrix[i-1][j-1] == '1') {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                        squareBorder = Math.max(dp[i][j], squareBorder);
                    }
                }
            }
            return squareBorder * squareBorder;
        }
    }

    /**
     * 最大子序和
     *
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

     * 示例:

     *  输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:

     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    static class Solution3 {

    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

     * 你可以假设 nums1 和 nums2 不会同时为空。

     *  示例 1:

     * nums1 = [1, 3]
     * nums2 = [2]

     * 则中位数是 2.0
     * 示例 2:

     * nums1 = [1, 2]
     * nums2 = [3, 4]

     则中位数是 (2 + 3)/2 = 2.5
     */
    static class Solution4 {
        // baoli
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length == 0) {
                return findMedian(nums2);
            }
            if (nums2.length == 0) {
                return findMedian(nums1);
            }
            int[] nums3 = new int[nums1.length + nums2.length];
            for (int i = 0;i < nums1.length; i ++) {
                nums3[i] = nums1[i];
            }
            for (int i = 0;i < nums2.length; i ++) {
                nums3[nums1.length + i] = nums2[i];
            }
            Arrays.sort(nums3);
            return findMedian(nums3);
        }

        private static double findMedian(int[] nums) {
            double result;
            // 奇数
            if ((nums.length & 1) == 1) {
                result = nums[nums.length/2] ;
            } else {
                result = (nums[(nums.length/2) - 1] + nums[nums.length/2]) / 2.0;
            }
            return result;
        }

        // 二分法
        public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) { // to ensure m<=n
                int[] temp = nums1; nums1 = nums2; nums2 = temp;
                int tmp = m; m = n; n = tmp;
            }
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;
                if (i < iMax && nums2[j-1] > nums1[i]){
                    iMin = i + 1; // i is too small
                }
                else if (i > iMin && nums1[i-1] > nums2[j]) {
                    iMax = i - 1; // i is too big
                }
                else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) { maxLeft = nums2[j-1]; }
                    else if (j == 0) { maxLeft = nums1[i-1]; }
                    else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); }
                    if ( (m + n) % 2 == 1 ) { return maxLeft; }

                    int minRight = 0;
                    if (i == m) { minRight = nums2[j]; }
                    else if (j == n) { minRight = nums1[i]; }
                    else { minRight = Math.min(nums2[j], nums1[i]); }

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }

        public static void main(String[] args) {
            System.out.println(findMedianSortedArrays1(new int[]{1, 3}, new int[]{2}));
        }
    }
}
