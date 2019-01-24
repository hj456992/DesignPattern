package Test;

import java.util.*;

/**
 * Created by houjue on 2018/11/30.
 */
public class Algorithm {

    /**
     * 求众数
     *
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

     * 你可以假设数组是非空的，并且给定的数组总是存在众数。

     * 示例 1:

     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:

     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     */
    static class Solution1 {
        // 解法1 用map存储数字对应的次数，找出大于n/2的数，时间复杂度为O(n)
        public int majorityElement1(int[] nums) {
            int length = nums.length;
            int max = 0;
            if ((length & 1) == 1) {
                max = length / 2 + 1;
            } else {
                max = length / 2;
            }
            int majorityE = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0;i < length; i ++) {
                if (map.containsKey(nums[i])) {
                    int count = map.get(nums[i]);
                    count ++;
                    if (count >= max) {
                        return nums[i];
                    }
                    map.put(nums[i], count);
                } else {
                    map.put(nums[i], 1);
                }
            }

            for (Integer num : map.keySet()) {
                if (map.get(num) >= max) {
                    return num;
                }
            }
            return majorityE;
        }

        // 解法2 排序，然后查n/2的数，因为必有众数，所以n/2的数必为众数，时间复杂度由排序决定，最快O(nlogn)
        public int majorityElement2(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    /**
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

     * 示例 1:

     * 输入: [3,2,3]
     * 输出: [3]
     * 示例 2:

     * 输入: [1,1,1,3,3,2,2,2]
     * 输出: [1,2]

     */
    static class Solution2 {
        // 解法1，用map存储每个数字的出现次数
        public List<Integer> majorityElement1(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0;i < nums.length; i ++) {
                if (map.containsKey(nums[i])) {
                    int count = map.get(nums[i]);
                    count ++;
                    map.put(nums[i], count);
                } else {
                    map.put(nums[i], 1);
                }
            }

            List<Integer> res = new ArrayList<>();
            for (Integer key : map.keySet()) {
                if (map.get(key) > (nums.length / 3)) {
                    res.add(key);
                }
            }
            return res;
        }

        // 解法2，因为是求大于n/3个的众数，所以结果集中最多只能有两个结果，因此，定义两个变量来存储结果，两个变量来存储结果的个数即可，时间复杂度为O(n)，空间复杂度为O(n)
        public static List<Integer> majorityElement2(int[] nums) {
            List<Integer> res=new ArrayList<Integer>();
            if(nums.length<3){
                for(int i:nums){
                    if(!res.contains(i))
                        res.add(i);
                }

                return res;
            }
            else{
                int num1=0;
                int num2=0;
                int count1=0;
                int count2=0;
                for(int i=0;i<nums.length;i++){
                    if(nums[i]==num1){
                        count1++;


                    }
                    else if(nums[i]==num2){
                        count2++;


                    }
                    else if(count1==0){
                        count1=1;
                        num1=nums[i];
                    }
                    else if(count2==0){
                        count2=1;
                        num2=nums[i];
                    }
                    else{
                        count1--;
                        count2--;
                    }
                }
                count1=0;
                count2=0;
                for(int i:nums){
                    if (i==num1)
                        count1++;
                    if (i==num2)
                        count2++;
                }
                if (count1>nums.length/3)
                    res.add(num1);
                if(count2>nums.length/3&&num2!=num1)
                    res.add(num2);
            }
            return res;
        }
    }

    /**
     * 翻转一棵二叉树。

     * 示例：

     * 输入：

     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：

     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */
    static class Solution3 {
        private class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
        }

        private static TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;

            invertTree(root.left);
            invertTree(root.right);

            return root;
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
    static class Solution4 {
        // 方法，贪心算法，遍历数组，后一位的数大于当前数，则当前位置买入，后一位卖出，最后计算卖出与买入的差价的和
        public static int maxProfit(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }

            int benefit = 0;
            for (int i = 0;i < prices.length - 1; i ++) {
                int cap = prices[i + 1] - prices[i];
                if (cap > 0) {
                    benefit += cap;
                }
            }

            return benefit;
        }

        public static void main(String[] args) {
            int[] arr1 = {7,1,5,3,6,4};
            int[] arr2 = {1,2,3,4,5};
            int[] arr3 = {7,6,4,3,1};
            System.out.println(maxProfit(arr1));
            System.out.println(maxProfit(arr2));
            System.out.println(maxProfit(arr3));
        }
    }

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
    static class Solution5 {
        // 解法，计算一个上升区间内的峰顶和峰底的差值，比较差值与最大值
        public static int maxProfit(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }

            int peakBottom = prices[0];
            int max = 0;
            for (int i = 1;i < prices.length; i ++) {
                int cap = prices[i] - peakBottom;
                if (cap > max) {
                    max = cap;
                }
                if (cap < 0) {
                    peakBottom = prices[i];
                }
            }

            return max;
        }

        public static void main(String[] args) {
            int[] arr1 = {7,1,5,3,6,4};
            int[] arr2 = {7,6,4,3,1};
            System.out.println(maxProfit(arr1));
            System.out.println(maxProfit(arr2));
        }
    }

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

     * 示例:

     * 输入: 4
     * 输出: [
     * [".Q..",  // 解法 1
     *  "...Q",
     *  "Q...",
     *  "..Q."],

     * ["..Q.",  // 解法 2
     *  "Q...",
     *  "...Q",
     *  ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     */
    static class Solution6 {
        // 存储已存在的皇后的列
        static Set<Integer> colSet = new HashSet<>();
        // 存储已存在的皇后的左斜线
        static Set<Integer> xyLeft = new HashSet<>();
        // 存储已存在的皇后的有些县
        static Set<Integer> xyRight = new HashSet<>();

        /**
         * n皇后问题要求n个皇后之间不能互相攻击，即任意两个皇后，不能在同一行、同一列、或同一斜线上
         * 解题思路：
         * 因为同一行同一列只能有一个皇后，所以，当[i,j]节点有皇后的时候，第i行，第j列均不能有皇后
         * 斜线上不能有皇后，可以转化为将棋盘以(0,0)为原点的坐标系，y=-x+c，y=x+c这两条线上没有皇后，即左：y+x=c，右：y-x=c
         */
        public static List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n == 1) {
                List<String> queens = new ArrayList<>();
                queens.add("Q");
                res.add(queens);
                return res;
            }
            if (n < 3) {
                return res;
            }
            // 深度优先遍历出所有皇后的位置
            dfs(0, n, new int[n], res);
            return res;
        }

        /**
         * 深度优先算法
         * @param row 当前行
         * @param n 总共层级
         * @param queenIndex 皇后位置
         */
        private static void dfs(int row, int n, int[] queenIndex, List<List<String>> res) {
            // 递归终止条件
            if (row == n) {
                List<String> queens = new ArrayList<>();
                for (int i = 0; i < n ;i ++) {
                    StringBuilder queen = new StringBuilder();
                    for (int j = 0; j < n; j ++) {
                        if (queenIndex[i] == j) {
                            queen.append("Q");
                        } else {
                            queen.append(".");
                        }
                    }
                    queens.add(queen.toString());
                }
                res.add(queens);
                return;
            }

            for (int col = 0; col < n; col ++) {
                // 判断此位置是否可以加入皇后
                if (colSet.contains(col) || xyLeft.contains(row + col) || xyRight.contains(col - row)) {
                    continue;
                }

                // 如果可以加入皇后
                colSet.add(col);
                xyLeft.add(row + col);
                xyRight.add(col - row);

                // 递归，遍历所有的层级，找到皇后的位置，放入结果集
                queenIndex[row] = col;
                dfs(row + 1, n,  queenIndex, res);

                // 递归完成，依次删除皇后的位置
                colSet.remove(col);
                xyLeft.remove(row + col);
                xyRight.remove(col - row);
            }
        }

        /**
         * [["Q....","..Q..","....Q",".Q...","...Q."],
         * ["Q....","...Q.",".Q...","....Q","..Q.."],
         * [".Q...","...Q.","Q....","..Q..","....Q"],
         * [".Q...","....Q","..Q..","Q....","...Q."],
         * ["..Q..","Q....","...Q.",".Q...","....Q"],
         * ["..Q..","....Q",".Q...","...Q.","Q...."],
         * ["...Q.","Q....","..Q..","....Q",".Q..."],
         * ["...Q.",".Q...","....Q","..Q..","Q...."],
         * ["....Q",".Q...","...Q.","Q....","..Q.."],
         * ["....Q","..Q..","Q....","...Q.",".Q..."]]
         */

        public static void main(String[] args) {
            List<List<String>> result = solveNQueens(15);
            System.out.println(result.toString());
            System.out.println(result.size());
        }

    }

    /**
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

     * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

     * 进阶:

     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？

     * 示例:

     * LRUCache cache = new LRUCache( 2 * 缓存容量 )
     * cache.put(1, 1);
     * cache.put(2, 2);
     * cache.get(1);       // 返回  1
     * cache.put(3, 3);    // 该操作会使得密钥 2 作废
     * cache.get(2);       // 返回 -1 (未找到)
     * cache.put(4, 4);    // 该操作会使得密钥 1 作废
     * cache.get(1);       // 返回 -1 (未找到)
     * cache.get(3);       // 返回  3
     * cache.get(4);       // 返回  4
     */
    static class Solution7 {

    }

    /**
     * 解数独
     *
     * 编写一个程序，通过已填充的空格来解决数独问题。

     * 一个数独的解法需遵循如下规则：

     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     */
    static class Solution8 {

        public static void main(String[] args) {
            char[][] board =
                    {{'3','.','2','7','.','.','.','.','9'}
                    ,{'.','.','8','.','.','.','.','4','5'}
                    ,{'.','.','4','.','.','1','3','.','.'}
                    ,{'.','.','.','.','5','9','.','.','.'}
                    ,{'.','9','.','.','3','.','.','6','.'}
                    ,{'.','.','.','2','6','.','.','.','.'}
                    ,{'.','.','1','4','.','.','2','.','.'}
                    ,{'2','6','.','.','.','.','1','.','.'}
                    ,{'4','.','.','.','.','2','5','.','.'}};
            solveSudoku(board);
            for (int i = 0; i < 9; i ++) {
                StringBuilder rowString = new StringBuilder();
                for (int j = 0; j < 9; j ++) {
                    rowString.append(board[i][j]);
                }
                System.out.println(rowString.toString());
            }
        }

        public static void solveSudoku(char[][] board) {
            dfs( board);
        }

        private static boolean dfs(char[][] board) {
            for (int i = 0; i < board.length; i ++) {
                for (int j = 0; j < board[0].length; j ++) {
                    if (board[i][j] == '.') {
                        // 当前位置是.则填充数字
                        for (char c = '1'; c <= '9'; c++) {
                            if (isValid(i, j, c, board)) {
                                board[i][j] = c;
                                // 填充下一个位置
                                if (dfs(board)) {
                                    return true;
                                } else {
                                    board[i][j] = '.';
                                }
                            }
                        }
                        // 1-9全都填不进去，返回false
                        return false;
                    }
                }
            }
            return true;
        }

        /**
         * 检查棋盘一个位置上的数字是否有效
         * @param row 数字所在行
         * @param col 数字坐在列
         * @param c 数字字符
         * @param board 棋盘
         * @return
         */
        private static boolean isValid(int row, int col, char c, char[][] board) {
            // 数字 1-9 在每一行只能出现一次
            for (int i = 0; i < 9; i ++) {
                if (board[row][i] == c) {
                    return false;
                }
            }
            // 数字 1-9 在每一列只能出现一次
            for (int j = 0; j < 9; j ++) {
                if (board[j][col] == c) {
                    return false;
                }
            }
            // 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
            for (int i = 3 * (row / 3); i < 3 * (row / 3 + 1); i ++) {
                for (int j = 3 * (col / 3);j < 3 * (col / 3 + 1); j ++) {
                    if (board[i][j] == c) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * 36. 有效的数独
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     */
    static class Solution9 {
        public boolean isValidSudoku(char[][] board) {

            return true;
        }
    }

    /**
     * 39、40. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

     * candidates 中的数字可以无限制重复被选取。

     * 说明：

     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。

     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     * [7],
     * [2,2,3]
     * ]

     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     */
    static class Solution10 {
        /**
         * [1,2,3]   100
         * [1..1(100)],
         * [1..1(98),2],[1..1(96),2,2]...[1,1,2..2(49)],
         * [1..1(95),2,3],[1..1(93),2,2,3]...[1,2..2(48),3],[1,2..2(45),3,3,3],[1,2,2,2,3..3(31)],
         */
        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return new ArrayList<>();
            }
            // 对数组排序，以更好的进行后续的计算
            Arrays.sort(candidates);

            List<List<Integer>> res = new ArrayList<>();
//            dfs39(candidates, target, 0, new ArrayList<>(), res);
            dfs40(candidates, target, 0, new ArrayList<>(), res);

            return res;
        }

        /**
         * 思路:当前组合=(target-前一个项的值)的组合加上前一个项的值
         * @param candidates
         * @param target
         * @param index
         * @param curList
         * @param res
         */
        private static void dfs39(int[] candidates, int target, int index, List<Integer> curList, List<List<Integer>> res) {
            // 终止条件，target = 0
            if (target == 0) {
                res.add(curList);
                return;
            }

            if (target < candidates[0]) {
                return;
            }
            for (int i = index; i < candidates.length && candidates[i] <= target; i ++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                // 深度拷贝，递归后自动复原
                List<Integer> list = new ArrayList<>(curList);
                list.add(candidates[i]);

                dfs39(candidates, target - candidates[i], i + 1, list, res);
            }
        }

        // 40题解法，添加了限制条件，即当前值和前一个值相等时即跳过
        private static void dfs40(int[] candidates, int target, int index, List<Integer> curList, List<List<Integer>> res) {
            // 终止条件，target = 0
            if (target == 0) {
                List<Integer> list = new ArrayList<>(curList);
                res.add(list);
                return;
            }

            if (target < candidates[0]) {
                return;
            }
            for (int i = index; i < candidates.length && candidates[i] <= target; i ++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                // 递归后复原
                curList.add(candidates[i]);
                dfs40(candidates, target - candidates[i], i + 1, curList, res);
                curList.remove(curList.size() - 1);
            }
        }

        public static void main(String[] args) {
            int[] candidates = new int[]{2,3,6,7};
            int target = 7;
            System.out.println(combinationSum(candidates, target));

            candidates = new int[]{2,3,5};
            target = 8;
            System.out.println(combinationSum(candidates, target));

            candidates = new int[]{2,3,5};
            target = 100;
            System.out.println(combinationSum(candidates, target));

            candidates = new int[]{10,1,2,7,6,1,5};
            target = 8;
            System.out.println(combinationSum(candidates, target));

            candidates = new int[]{2,5,2,1,2};
            target = 5;
            System.out.println(combinationSum(candidates, target));
        }
    }

    /**
     * 41. 缺失的第一个正数
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

     * 示例 1:
     * 输入: [1,2,0]
     * 输出: 3

     * 示例 2:
     * 输入: [3,4,-1,1,2]
     * 输出: 5

     * 示例 3:
     * 输入: [7,8,9,11,12]
     * 输出: 1

     * 说明:
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
     */
    static class Solution11 {
        // leetcode高赞答案
        public static int firstMissingPositive(int[] A) {
            int i = 0;
            /**
             * I just finished figuring out that case in my own solution, so it's still fresh in my mind.
             It's basically to deal with duplicates.

             If you have let's say two 1s, and one of the 1s is already in the correct position.
             Without that check, it's going to keep swapping the two 1s over and over again, infinite loop until the problem times out.

             It's basically saying 'if the element I'm supposed to fill this value with already contains this same value, then I'm done. I just move on, and don't swap.'
             As long as the space already contains one value that is correctly placed there, other duplicates don't matter.
             */
            while(i < A.length){
                if(A[i] >= 1 && A[i] <= A.length && A[A[i]-1] != A[i]) {
                    swap(A, i, A[i]-1);
                }else{
                    i++;
                }
            }
            i = 0;
            while(i < A.length && A[i] == i+1) i++;
            return i+1;
        }

        private static void swap(int[] A, int i, int j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        // 标准解题思路，把小于数组长度位置的数放在其数字对应的位置上，组成相对排序的数组，然后再进行一遍遍历即可
        public static int firstMissingPositive2(int[] nums) {
            // 第一遍循环，对数组进行排序
            for (int i = 0; i < nums.length; ) {
                // 负数和大于数组长度的数跳过
                if (nums[i] <= 0 || nums[i] > nums.length) {
                    i ++;
                    continue;
                }
                // 将数字放到其在数组索引中的位置上
                if (nums[nums[i] - 1] != nums[i]) {
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                } else {
                    i ++;
                }
            }

            // 第二遍循环，找到结果
            int res = 1;
            for (int i = 0; i < nums.length; i ++) {
                if (nums[i] <= 0) {
                    continue;
                }
                if (nums[i] != res) {
                    break;
                }
                res ++;
            }
            return res;
        }

        // hashSet解法
        public static int firstMissingPositive1(int[] nums) {
            int res = 1;
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (num <= 0) {
                    continue;
                }
                set.add(num);
                if (num == res) {
                    while (set.contains(res)) {
                        res ++;
                    }
                }
            }
            return res;
        }


        public static void main(String[] args) {
            int[] nums = new int[]{0,1,2};
            System.out.println(firstMissingPositive2(nums));

            nums = new int[]{3,7,-1,1,2,4};
            System.out.println(firstMissingPositive2(nums));

            nums = new int[]{7,8,9,2,3,4};
            System.out.println(firstMissingPositive2(nums));
        }
    }

    /**
     * 46. 全排列
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。

     * 示例:

     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    static class Solution12 {
        public static List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            recursive(nums, 0, res);
            return res;
        }

        private static void recursive(int[] nums, int index, List<List<Integer>> res) {
            if (index == nums.length - 1) {
                List<Integer> list = new ArrayList<>();
                for (int num : nums) {
                    list.add(num);
                }
                res.add(list);
                return;
            }

            // 寻找下一个排列
            for (int i = index; i < nums.length; i ++) {
                swap(nums, index, i);
                recursive(nums, index + 1, res);
                swap(nums, index, i);
            }
        }

        private static void swap(int[] nums, int l, int r) {
            int temp = nums[r];
            nums[r] = nums[l];
            nums[l] = temp;
        }

        public static void main(String[] args) {
            System.out.println(permute(new int[]{1,1,2}));
        }
    }

    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。

     * 示例:

     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     */
    static class Solution13 {
        public static List<List<Integer>> permuteUnique(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            recursive(nums, 0, res);
            return res;
        }

        private static void recursive(int[] nums, int index, List<List<Integer>> res) {
            if (index == nums.length - 1) {
                List<Integer> list = new ArrayList<>();
                for (int num : nums) {
                    list.add(num);
                }
                res.add(list);
                return;
            }

            // 寻找下一个排列
            // 1
//            for (int i = index; i <= nums.length - 1; ) {
//                int j = i;
//                // i为固定的开始值，j定位到下一个不同于i的值的位置
//                while (j < nums.length && nums[j] == nums[i]) {
//                    j ++;
//                }
//                swap(nums, index, i);
//                // 疑惑，为什么这里克隆可以，手动重新换位置就不行
//                int[] a2 = nums.clone();
//                recursive(a2, index + 1, res);
//                i = j;
//            }

            // 2
            for (int i = index; i <= nums.length - 1; i ++) {
                // index为此时固定的位置
                if (isDuplicate(nums, index, i)) {
                    swap(nums, index, i);
                    recursive(nums, index + 1, res);
                    swap(nums, index, i);
                }
            }
        }

        private static boolean isDuplicate(int[] nums, int index, int i) {
            for (int j = index; j < i; j ++) {
                if (nums[j] == nums[i]) {
                    return false;
                }
            }
            return true;
        }

        private static void swap(int[] nums, int l, int r) {
            int temp = nums[r];
            nums[r] = nums[l];
            nums[l] = temp;
        }

        public static void main(String[] args) {
            long n = 1;
            long res= 1;
            while (n <= 10000) {
                res *= n;
                n ++;
            }
            System.out.println(res);
//            System.out.println(permuteUnique(new int[]{1,1,2,2}));
        }
    }
}
