package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by houjue on 2019/1/3.
 */
public class ArrayTest {
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * 26. 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

     * 示例 1:

     * 给定数组 nums = [1,1,2],

     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:

     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],

     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

     * 你不需要考虑数组中超出新长度后面的元素。
     */
    static class Solution1 {
        public static int removeDuplicates(int[] nums) {
            if (nums.length < 2) {
                return nums.length;
            }

            int l = 0;
            int numl = nums[0];
            int res = 1;

            for (int i = 1; i < nums.length; i ++) {
                if (nums[i] != numl) {
                    res ++;
                    nums[++l] = nums[i];
                    numl = nums[i];
                }
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
        }
    }

    /**
     * 27. 移除元素
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

     * 示例 1:

     * 给定 nums = [3,2,2,3], val = 3,

     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:

     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,

     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

     * 注意这五个元素可为任意顺序。

     * 你不需要考虑数组中超出新长度后面的元素。
     */
    static class Solution2 {
        public static int removeElement(int[] nums, int val) {
            int l = 0;
            for (int i = 0; i < nums.length; i ++) {
                if (nums[i] != val) {
                    nums[l ++] = nums[i];
                }
            }
            return l;
        }

        public static void main(String[] args) {
            System.out.println(removeElement(new int[]{0}, 0));
        }
    }

    /**
     * 31. 下一个排列
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

     * 必须原地修改，只允许使用额外常数空间。

     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    static class Solution3 {
        /**
         * 题解思路：
         * 假如原数组为
         * 1 5 8 9 3 6 9 7 6 5
         * 从后向前遍历，找到开始降序的数[6]
         * 1 5 8 9 3 [6] 9 7 6 5
         * 比较之前遍历的数，找到大于[6]的最小数[7]，并进行位置转换
         * 1 5 8 9 3 [7] 9 [6] 6 5
         * 将[7]后面的数组进行反转，即为下一个排列
         * 1 5 8 9 3 [7] 5 6 6 9
         * @param nums
         */
        public static void nextPermutation(int[] nums) {
            if (nums.length <= 1) {
                return;
            }

            // 反转的位置
            int index = 0;
            for (int i = nums.length - 2; i >= 0; i --) {
                // 升序则跳过
                if (nums[i] >= nums[i + 1]) {
                    continue;
                }
                // 降序则从i往后遍历，找到差最小的一个值
                for (int j = nums.length - 1; j > i; j --) {
                    if (nums[j] > nums[i]) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        index = i + 1;
                        break;
                    }
                }
                break;
            }
            reverse(nums, index);
        }

        // 反转数组，从index位置开始
        public static void reverse(int[] nums, int index) {
            int i = index;
            int pos = nums.length - 1;
            while (i < pos) {
                int temp = nums[i];
                nums[i] = nums[pos];
                nums[pos] = temp;
                i ++;
                pos --;
            }
        }

        public static void main(String[] args) {
//            int[] nums = new int[]{3,2,1};
//            nextPermutation(nums);
//            for(int n : nums) {
//                System.out.println(n);
//            }

            int[] nums1 = new int[]{1,5,8,9,3,6,9,7,6,5};
            nextPermutation(nums1);
            for(int n : nums1) {
                System.out.println(n);
            }
        }
    }

    /**
     * 33. 搜索旋转排序数组
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

     * 你可以假设数组中不存在重复的元素。

     * 你的算法时间复杂度必须是 O(log n) 级别。

     * 示例 1:

     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:

     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     */
    static class Solution4 {
        // 思路，因为是排序数组，所以可以使用二分查找来找目标值
        public static int search1(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            // 遍历数组，找到旋转点的索引
            int l = 0;
            int r = nums.length - 1;
            int rolate = -1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid + 1] < nums[mid]) {
                    rolate = mid + 1;
                    break;
                }
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] > nums[l]) {
                    l = mid;
                }
                if (nums[mid] < nums[r]) {
                    r = mid;
                }
            }

            l = 0;
            r = nums.length - 1;
            if (rolate > 0) {
                if (target >= nums[rolate] && target <= nums[nums.length - 1]) {
                    l = rolate;
                    r = nums.length - 1;
                } else if (target >= nums[0] && target <= nums[rolate - 1]) {
                    l = 0;
                    r = rolate - 1;
                } else {
                    return -1;
                }
            }
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] > target) {
                    r = mid - 1;
                }
                if (nums[mid] < target) {
                    l = mid + 1;
                }
            }
            return  -1;
        }

        // 二分法优化，将数组切为两半，一定是一部分有序，一部分无序，target在有序的部分中则二分，在无序的部分中重复前面的操作。
        public static int search(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int l = 0;
            int r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[l] <= nums[mid]) {
                    if (target >= nums[l] && target < nums[mid]) {
                        r = mid - 1;
                        break;
                    } else {
                        l = mid + 1;
                    }
                } else if (nums[mid] <= nums[r]) {
                    if (target > nums[mid] && target <= nums[r]) {
                        l = mid + 1;
                        break;
                    } else {
                        r = mid - 1;
                    }
                }
            }

            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] > target) {
                    r = mid - 1;
                }
                if (nums[mid] < target) {
                    l = mid + 1;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] nums = new int[]{4,5,6,7,0,1,2};
//            System.out.println(search(nums, 0));
//
//            nums = new int[]{4,5,6,7,0,1,2};
//            System.out.println(search(nums, 3));
//
//            nums = new int[]{3,1};
//            System.out.println(search(nums, 3));
//
//            nums = new int[]{5,1,3};
//            System.out.println(search(nums, 3));

            nums = new int[]{3,1};
            System.out.println(search(nums, 0));
        }
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

     * 你的算法时间复杂度必须是 O(log n) 级别。

     * 如果数组中不存在目标值，返回 [-1, -1]。

     * 示例 1:

     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:

     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     */
    static class Solution5 {
        // 思路，二分法查找，查找左边最小的，和右边最大的
        public static int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) {
                return new int[]{-1,-1};
            }

            int left = searchLeft(nums, target);
            if (left >= 0) {
                int right = searchRight(nums, target, left);
                return new int[]{left, right};
            }
            return new int[]{-1, -1};
        }

        private static int searchLeft(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    if (mid == 0 || nums[mid - 1] < target) {
                        return mid;
                    }
                    r = mid - 1;
                }
                if (nums[mid] < target) {
                    l = mid + 1;
                }
                if (nums[mid] > target) {
                    r = mid - 1;
                }
            }
            return -1;
        }

        private static int searchRight(int[] nums, int target, int l) {
            int r = nums.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    if (mid == nums.length - 1 || nums[mid + 1] > target) {
                        return mid;
                    }
                    l = mid + 1;
                }
                if (nums[mid] < target) {
                    l = mid + 1;
                }
                if (nums[mid] > target) {
                    r = mid - 1;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] res = searchRange(new int[]{5}, 6);
            for (int i : res) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵表示一个图像。

     * 将图像顺时针旋转 90 度。

     * 说明：

     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

     * 示例 1:

     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],

     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * 示例 2:

     * 给定 matrix =
     * [
     * [ 5, 1, 9,11],
     * [ 2, 4, 8,10],
     * [13, 3, 6, 7],
     * [15,14,12,16]
     * ],

     * 原地旋转输入矩阵，使其变为:
     * [
     * [15,13, 2, 5],
     * [14, 3, 4, 1],
     * [12, 6, 8, 9],
     * [16, 7,10,11]
     * ]
     */
    static class Solution6 {
        // 思路
        // 先找外围，再一层层计算内圈
        // 这四个位置要背下来
        // 对(i,j)而言，其90度旋转对应的四个点为(j, col-i),(row-i, col-j),(row-j,i)
        class Solution {
            public void rotate(int[][] matrix) {
                if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                    return;
                }

                int col = matrix[0].length-1;
                int row = matrix.length-1;

                int colF = col;
                int rowF = row;

                for (int i = 0; i < rowF ; i++) {
                    for (int j = i; j < colF; j++) {
                        int x2 = j;
                        int y2 = col - i;

                        int x3 = row - i;
                        int y3 = col - j;

                        int x4 = row - j;
                        int y4 = i;

                        swap(matrix, i, j, x2, y2);
                        swap(matrix, i, j, x3, y3);
                        swap(matrix, i, j, x4, y4);
                    }
                    rowF--;
                    colF--;
                }
            }

            public void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
                int temp = matrix[x1][y1];
                matrix[x1][y1] = matrix[x2][y2];
                matrix[x2][y2] = temp;
            }
        }
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

     * 示例:

     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:

     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    static class Solution7 {
        // dp[i] = dp[i - 1] > 0 : dp[i - 1] + nums[i] : 0 + nums[i]
        public static int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1;i < nums.length; i ++) {
                dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
                max = Math.max(max, dp[i]);
            }

            return max;
        }

        public static void main(String[] args) {
            System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        }
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

     * 示例:

     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：

     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    static class Solution8 {
        public List<List<String>> groupAnagrams(String[] strs) {

            return new ArrayList<>();
        }

    }

    /**
     * 238. 除自身以外数组的乘积
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    static class Solution9 {
        // 左右两个指针循环两遍即可
        public static int[] productExceptSelf(int[] nums) {
            int left = 1;
            int right = 1;
            int[] output = new int[nums.length];
            // output赋值i位置left的乘积
            for (int i = 0; i < nums.length; i ++) {
                output[i] = left;
                left *= nums[i];
            }

            // output赋值i位置left*right的乘积
            for (int i = nums.length - 1; i >=0; i --) {
                output[i] *= right;
                right *= nums[i];
            }
            return output;
        }

        public static void main(String[] args) {
            int[] res = productExceptSelf(new int[]{1,2,3,4});
            for (int r : res) {
                System.out.println(r);
            }
        }
    }

    /**
     * 54. 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    static class Solution10 {
        public static List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> list = new ArrayList<>();
            if(matrix==null||matrix.length==0) return list;

            int left = 0;
            int right = matrix[0].length - 1;
            int up = 0;
            int down = matrix.length - 1;
            while (vaild(up, down, left, right)) {
                spiral(matrix, list, up, down, left, right);
                left ++;
                right --;
                up ++;
                down --;
            }
            return list;
        }

        static boolean vaild(int up, int down, int left, int right) {
            if (up <= down && left <= right) {
                return true;
            }
            return false;
        }

        static void spiral(int[][] matrix, List<Integer> list, int up, int down, int left, int right) {
            if (up == down) {
                for (int i = left;i <= right; i ++) {
                    list.add(matrix[up][i]);
                }
            } else if (left == right) {
                for (int i = up; i <= down; i ++) {
                    list.add(matrix[i][left]);
                }
            } else {
                for (int i = left; i <= right; i ++) {
                    list.add(matrix[up][i]);
                }
                for (int i = up + 1; i <= down; i ++) {
                    list.add(matrix[i][right]);
                }
                for (int i = right - 1; i >= left; i --) {
                    list.add(matrix[down][i]);
                }
                for (int i = down - 1; i > up; i --) {
                    list.add(matrix[i][left]);
                }
            }
        }

        public static void main(String[] args) {
            int[][] matrix = new int[][]{
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{
                    {2,3,4},
                    {5,6,7},
                    {8,9,10},
                    {11,12,13},
                    {14,15,16}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9,10,11,12}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{
                    {1},{2},{3}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{
                    {1,2,3}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{
                    {1}
            };
            System.out.println(spiralOrder(matrix));

            matrix = new int[][]{

            };
            System.out.println(spiralOrder(matrix));


        }
    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     *
     * 示例 1:
     *
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
     * 示例 2:
     *
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     */
    static class Solution11 {
        // 暴力回溯
        public static boolean canJump1(int[] nums) {
            if (nums.length == 0) {
                return false;
            }
            return jump(nums, 0, nums.length - 1);
        }

        private static boolean jump(int[] nums, int index, int step) {
            if (step <= 0) {
                return true;
            }

            for (int i = nums[index]; i >= 1 ;i --) {
                if (jump(nums, index + i, step - i)) {
                    return true;
                }
            }
            return false;
        }

        // 贪心，从后往前遍历，如果该位置能提供所需步数，则重置，不能则步数加一
        public static boolean canJump2(int[] nums) {
            int step=1;
            for(int i=nums.length-2;i>=0;i--){
                if(nums[i]>=step) {
                    step = 1;
                } else {
                    step ++;
                }
            }
            return step <= 1;
        }

        // 动态规划，dp[i]代表i位置能否达到
        // dp[i] = len(i) > Max(nums[i - 1] + i - 1) ? false : true;
        public static boolean canJump(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length; i ++) {
                if (i > max) {
                    return false;
                }
                max = Math.max(nums[i] + i, max);
            }
            return true;
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

        public static String booleanToString(boolean input) {
            return input ? "True" : "False";
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                int[] nums = stringToIntegerArray(line);

                boolean ret = canJump(nums);

                String out = booleanToString(ret);

                System.out.print(out);
            }
        }
    }

    /**
     * 56. 合并区间
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: [[1,4],[0,5]]
     * 输出: [[0,5]]
     * 解释: 区间 [1,4] 和 [0,5] 可被视为重叠区间。
     */
    static class Solution12 {
        // 解法1，主要是练习快排
        public static List<Interval> merge1(List<Interval> intervals) {
            if (intervals.size() == 0) {
                return new ArrayList<>();
            }

            // intervals排序，把start较小的放在前面，start相同则比较end，end小的直接过滤
            quickSort(intervals, 0, intervals.size() - 1);
            int st = intervals.get(0).start;
            int ed = intervals.get(0).end;

            List<Interval> res = new ArrayList<>();
            for (Interval interval : intervals) {
                if (interval.start <= ed) {
                    ed = Math.max(ed, interval.end);
                } else {
                    Interval inter = new Interval(st, ed);
                    res.add(inter);
                    st = interval.start;
                    ed = interval.end;
                }
            }
            Interval inter = new Interval(st, ed);
            res.add(inter);
            return res;
        }

        private static void quickSort(List<Interval> intervals, int low, int high) {
            int start = low;
            int end = high;
            int key = intervals.get(low).start;

            while (end > start) {
                while (end > start && intervals.get(end).start >= key) {
                    end --;
                }
                if (intervals.get(end).start <= key) {
                    int tempSt = intervals.get(start).start;
                    int tempEd = intervals.get(start).end;
                    intervals.get(start).start = intervals.get(end).start;
                    intervals.get(start).end = intervals.get(end).end;
                    intervals.get(end).start = tempSt;
                    intervals.get(end).end = tempEd;
                }

                while (end > start && intervals.get(start).start <= key) {
                    start ++;
                }
                if (intervals.get(start).start >= key) {
                    int tempSt = intervals.get(start).start;
                    int tempEd = intervals.get(start).end;
                    intervals.get(start).start = intervals.get(end).start;
                    intervals.get(start).end = intervals.get(end).end;
                    intervals.get(end).start = tempSt;
                    intervals.get(end).end = tempEd;
                }
            }
            //递归
            if(start>low) quickSort(intervals,low,start-1);//左边序列。第一个索引位置到关键值索引-1
            if(end<high) quickSort(intervals,end+1,high);//右边序列。从关键值索引+1到最后一个
        }

        // 解法2，建立start和end数组，i,j指针指向start数组，移动start数组的i，当start[i + 1] > end[i]时，(start[j],end[i])为一个合并区间
        public static List<Interval> merge(List<Interval> intervals) {
            if (intervals.size() == 0) {
                return new ArrayList<>();
            }

            int[] start = new int[intervals.size()];
            int[] end = new int[intervals.size()];

            for (int i = 0;i < intervals.size(); i ++) {
                start[i] = intervals.get(i).start;
                end[i] = intervals.get(i).end;
            }

            Arrays.sort(start);
            Arrays.sort(end);

            List<Interval> res = new ArrayList<>();
            for (int i = 0, j = 0; i < start.length; i ++) {
                if (i == intervals.size() - 1 || start[i + 1] > end[i]) {
                    res.add(new Interval(start[j], end[i]));
                    j = i + 1;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            List<Interval> intervals = new ArrayList<>();
            Interval interval1 = new Interval(1,4);
            intervals.add(interval1);
            Interval interval2 = new Interval(0,4);
            intervals.add(interval2);
            System.out.println(merge(intervals));
        }
    }

    /**
     * 88. 合并两个有序数组
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     */
    static class Solution13 {
        public static void merge(int[] nums1, int m, int[] nums2, int n) {
            int p = m - 1;
            int q = n - 1;
            for (int i = m + n - 1; i >= 0; i --) {
                if (q < 0) {
                    break;
                }
                if (p >= 0 && nums1[p] > nums2[q]) {
                    nums1[i] = nums1[p];
                    p --;
                } else {
                    nums1[i] = nums2[q];
                    q --;
                }
            }
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

        public static String integerArrayToString(int[] nums, int length) {
            if (length == 0) {
                return "[]";
            }

            String result = "";
            for(int index = 0; index < length; index++) {
                int number = nums[index];
                result += Integer.toString(number) + ", ";
            }
            return "[" + result.substring(0, result.length() - 2) + "]";
        }

        public static String integerArrayToString(int[] nums) {
            return integerArrayToString(nums, nums.length);
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                int[] nums1 = stringToIntegerArray(line);
                line = in.readLine();
                int m = Integer.parseInt(line);
                line = in.readLine();
                int[] nums2 = stringToIntegerArray(line);
                line = in.readLine();
                int n = Integer.parseInt(line);

                merge(nums1, m, nums2, n);
                String out = integerArrayToString(nums1);

                System.out.print(out);
            }
        }
    }

    /**
     * 59. 螺旋矩阵 II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     */
    static class Solution14 {
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            int num = 1;
            int up = 0;
            int down = n - 1;
            int left = 0;
            int right = n - 1;
            while (num <= n * n) {
                num = fillMatrix(res, up, down, left, right, num);
                up ++;
                down --;
                left ++;
                right --;
            }
            return res;
        }

        private int fillMatrix(int[][] res, int up, int down, int left, int right, int num) {
            // 从左到右
            for (int i = left; i <= right; i ++) {
                res[up][i] = num;
                num ++;
            }
            // 从上到下
            for (int i = up + 1; i <= down; i ++) {
                res[i][right] = num;
                num ++;
            }
            // 从右到左
            for (int i = right - 1; i >= left; i --) {
                res[down][i] = num;
                num ++;
            }
            // 从下到上
            for (int i = down - 1; i > up; i --) {
                res[i][left] = num;
                num ++;
            }
            return num;
        }
    }

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    static class Solution15 {
        public List<List<Integer>> subsets(int[] nums) {
            return new ArrayList<>();
        }
    }

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     *
     * 输入: [1,2,3,4]
     * 输出: false
     * 示例 3:
     *
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     */
    static class Solution16 {
        public static boolean containsDuplicate(int[] nums) {

            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < nums.length ; i ++) {
                if (set.size() < i) {
                    return true;
                }
                set.add(nums[i]);
            }
            return set.size() < nums.length;
        }

        public static void main(String[] args) {
            System.out.println(containsDuplicate(new int[]{7,3,2,1,2}));
        }
    }

    /**
     * 57. 插入区间
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     *
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出: [[1,5],[6,9]]
     * 示例 2:
     *
     * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出: [[1,2],[3,10],[12,16]]
     * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */
    static class Solution17 {
        // 找到要插入的start和end位置，进行插入
        public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            List<Interval> res = new ArrayList<>();
            if (intervals.size() == 0) {
                res.add(newInterval);
                return res;
            }

            int start = 0;
            int end = 0;
            int i = 0;
            for (; i < intervals.size();i ++) {
                // 插入头部
                if (i == 0 && newInterval.end < intervals.get(i).start) {
                    res.add(newInterval);
                    res.addAll(intervals);
                    return res;
                }
                // 插入尾部
                if (i == intervals.size() - 1 && newInterval.start > intervals.get(i).end) {
                    res.add(intervals.get(i));
                    res.add(newInterval);
                    return res;
                }
                if (newInterval.start >= intervals.get(i).start && newInterval.start <= intervals.get(i).end) {
                    start = intervals.get(i).start;
                    break;
                }
                if (newInterval.start < intervals.get(i).start) {
                    start = newInterval.start;
                    break;
                }
                res.add(intervals.get(i));
            }

            int j = i;
            while (j < intervals.size()) {
                if (newInterval.end < intervals.get(j).start) {
                    end = newInterval.end;
                    Interval interval = new Interval(start, end);
                    res.add(interval);
                    break;
                }
                if (j == intervals.size() - 1 && newInterval.end > intervals.get(j).end) {
                    end = newInterval.end;
                    Interval interval = new Interval(start, end);
                    res.add(interval);
                    j ++;
                    break;
                }
                if (newInterval.end >= intervals.get(j).start && newInterval.end <= intervals.get(j).end) {
                    end = intervals.get(j).end;
                    Interval interval = new Interval(start, end);
                    res.add(interval);
                    j ++;
                    break;
                }
                j ++;
            }

            for (int k = j; k < intervals.size(); k ++) {
                res.add(intervals.get(k));
            }
            return res;
        }

        // 偷懒解法，利用56题的合并区间，先把newInterval加入进list，再调用合并区间
        public static List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
            intervals.add(newInterval);
            return Solution12.merge(intervals);
        }


            public static void main(String[] args) {
            List<Interval> intervals = new ArrayList<>();
            Interval interval = new Interval(1,2);
            Interval interval1 = new Interval(3,5);
            Interval interval2 = new Interval(6,7);
            Interval interval3 = new Interval(8,10);
            Interval interval4 = new Interval(12,16);
            intervals.add(interval);
            intervals.add(interval1);
            intervals.add(interval2);
            intervals.add(interval3);
            intervals.add(interval4);

            List<Interval> intervals1 = new ArrayList<>();
            Interval interval5 = new Interval(1,5);
            intervals1.add(interval5);

            Interval newInterval = new Interval(0,3);

            List<Interval> res = insert(intervals1, newInterval);
            for (Interval i : res) {
                System.out.println(i.start + "   " + i.end);
            }
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     */
    static class Solution18 {
        // 解法1，利用快排的思路，当key的索引为len(nums)-k时，即为第k个最大的元素
        public static int findKthLargest1(int[] nums, int k) {
            return quickFindKth(nums, nums.length - k, 0, nums.length - 1);
        }

        // 解法2，Hash思想
        public static int findKthLargest(int[] nums, int k) {
            if (nums.length <= 1) {
                return nums[0];
            }
            int min = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i ++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
                if (nums[i] < min) {
                    min = nums[i];
                }
            }

            int[] hashArr = new int[max - min + 1];
            for (int n : nums) {
                hashArr[max - n] ++;
            }
            int sum = 0;
            for (int i = 0; i < hashArr.length; i ++) {
                sum += hashArr[i];
                if (sum >= k) {
                    return max - i;
                }
            }
            return -1;
        }

        // 解法3，快速选择
        public int findKthLargest2(int[] nums, int k) {
            int left=0,right=nums.length-1;
            while(left<right){
                int pivot = partition(nums, left,right);
                if(pivot ==k-1) {
                    return nums[pivot];
                } else if(pivot>k-1){
                    right=pivot-1;
                } else {
                    left=pivot+1;
                }
            }
            return nums[left];
        }
        private int partition(int[] nums,int left,int right) {
            //先获取三个数的中位数
            int pivot = median3(nums,left,right);
            int start=left,end=right-1;
            while(start<end) {
                //从pivot左边开始，停在第一个比pivot小的地方，等待交换
                while(nums[++start]>pivot) {}
                //从pivot右边开始，停在第一个比pivot大的地方，等待交换
                while(nums[--end]<pivot) {}
                if(start<end) {
                    swap(nums,start,end);
                }
            }
            //此时，交换start与pivot
            swap(nums,start, right-1);
            return start;
        }

        private int median3(int[] nums,int left, int right){
            int median=(left+right)/2;
            if(nums[left]<nums[median]) {
                swap(nums, left, median);
            }
            if(nums[left]<nums[right]) {
                swap(nums,left, right);
            }
            if(nums[median]<nums[right]) {
                swap(nums, median, right);
            }
            swap(nums, median, right-1);
            return nums[right-1];

        }

        public static int quickFindKth(int[] nums, int k, int low, int high) {
            int start = low;
            int end = high;
            int key = nums[start];// 第0位是索引，第1位是key值
            int index = -1;

            while (start < end) {
                while (start < end && key <= nums[end]) {
                    end --;
                }

                if (nums[end] < key) {
                    swap(nums, start, end);
                    index = end;
                }

                while (start < end && nums[start] <= key) {
                    start ++;
                }

                if (nums[start] > key) {
                    swap(nums, start, end);
                    index = start;
                }
            }

            if (index == k) {
                return nums[index];
            }

            if (low < start) {
                return quickFindKth(nums, k, low, start - 1);
            }
            if (end < high) {
                return quickFindKth(nums, k, end + 1, high);
            }

            return nums[k];
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
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

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                int[] nums = stringToIntegerArray(line);
                line = in.readLine();
                int k = Integer.parseInt(line);

                int ret = findKthLargest1(nums, k);

                String out = String.valueOf(ret);

                System.out.print(out);
            }
        }
    }

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    static class Solution19{
        // 解题思路，回溯算法，任意位置的数的子集是其后面的数的子集再加上它和它本身
        public static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(nums, 0, res);
            return res;
        }

        private static void dfs(int[] nums, int i, List<List<Integer>> res) {
            if (i == nums.length) {
                res.add(new ArrayList<>());
                return;
            }

            List<List<Integer>> res1 = new ArrayList<>();
            for (List<Integer> list : res) {
                List<Integer> list1 = new ArrayList<>(list);
                list1.add(nums[i]);
                res1.add(list1);
            }
            res.addAll(res1);

            List<Integer> sub = new ArrayList<>();
            sub.add(nums[i]);
            res.add(sub);

            dfs(nums, ++i, res);
        }

        public static void main(String[] args) {
            System.out.println(subsets(new int[]{1}));
        }
    }
}
