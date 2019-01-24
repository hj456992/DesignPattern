package Test;

import java.util.*;

/**
 * Created by houjue on 2018/12/5.
 */
public class ByteDanceTest {
    /**
     * 最长公共前缀
     *
     * 编写一个函数来查找字符串数组中的最长公共前缀。

     * 如果不存在公共前缀，返回空字符串 ""。

     * 示例 1:

     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:

     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:

     * 所有输入只包含小写字母 a-z 。
     */
    static class Solution1 {
        public static void main(String[] args) {
            System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
            System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
        }

        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }

            String commonPrefix = strs[0];
            // find min
            // compare and find common prefix
            for (int i = 0; i < strs.length; i ++) {
                if (strs[i].isEmpty()) {
                    return "";
                }
                int len = strs[i].length();
                if (len < commonPrefix.length()) {
                    commonPrefix = commonPrefix.substring(0, strs[i].length());
                }
                while (strs[i].indexOf(commonPrefix) != 0) {
                    commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
                    if (commonPrefix.isEmpty()) {
                        return "";
                    }
                }
            }
            return commonPrefix;
        }
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。

     * 示例1:

     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").


     * 示例2:

     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False


     * 注意：

     * 输入的字符串只包含小写字母
     * 两个字符串的长度都在 [1, 10,000] 之间
     */
    static class Solution2 {

        public static void main(String[] args) {
            System.out.println(checkInclusion("ab", "eidbaooo"));
            System.out.println(checkInclusion("abcdxabcde","abcdeabcdx"));
        }

        public static boolean checkInclusion(String s1, String s2) {
            if (s1.isEmpty()) {
                return true;
            }
            if (s2.isEmpty() || s1.length() > s2.length()) {
                return false;
            }

            int[] s1Arr = new int[26];
            for (int i = 0;i < s1.length();i ++) {
                s1Arr[s1.charAt(i) - 'a'] ++;
            }

            // 定义一个右界为i的滑动窗口
            int[] s2Arr = new int[26];
            for (int i = 0; i < s2.length(); i ++) {
                if (i >= s1.length()) {
                    --s2Arr[s2.charAt(i-s1.length()) - 'a'];
                }
                s2Arr[s2.charAt(i) - 'a'] ++;
                if (Arrays.equals(s2Arr, s1Arr)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

     * 示例 1:

     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:

     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：

     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */
    static class Solution3 {

        public static void main(String[] args) {
            System.out.println(multiply("9", "9"));
        }

        /**
         * 解题思路：数字的乘法过程如下，已1234*45为例
         *
         *         1234
         * *         45
         * ------------
         *        06170---(4*5+0=(2)0进2，3*5+2=(1)7进1，2*5+1=(1)1进1，1*5+1=6)
         * +      49360---类似上述
         * ------------
         *        55530
         */
        public static String multiply(String num1, String num2) {
            if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
                return "";
            }
            if ("0".equals(num1) || "0".equals(num2)) {
                return "0";
            }

            char[] n1 = num1.toCharArray();
            char[] n2 = num2.toCharArray();
            // 开辟一个二维数组，最多有num2.length行、num1.length+num2.length列，填充数据从col.length-row-1开始填，每向下一行
            // ，则向前进一列填充，末尾补0
            int[][] board = new int[n2.length][n1.length + n2.length];

            for (int row = 0; row < n2.length; row ++) {
                int[] curCol = board[row];
                // 当前行的乘数，如例子所示，为5、4
                int curN2Num = n2[n2.length - 1 - row] - '0';
                // 进位数
                int temp = 0;
                // 从后向前
                int n1Index = n1.length;
                for (int col = n1.length + n2.length - 1; col >= 0; col --) {
                    // num1的数字
                    int curN1Num = n1Index > 0 ? n1[--n1Index] - '0' : 0;
                    int p = curN1Num * curN2Num + temp;
                    if (col > row) {
                        curCol[col - row] = (char) (p % 10);
                    } else if (temp > 0){
                        curCol[0] = temp;
                    }
                    temp = p / 10;
                }
                board[row] = curCol;
            }

            // 进位数
            int temp = 0;
            // 结果数组
            char[] resArr = new char[n1.length + n2.length];
            // 计算board
            for (int col = n1.length + n2.length - 1; col >= 0; col --) {
                int r = 0;
                for (int row = 0; row < n2.length; row ++) {
                    r += board[row][col];
                }
                int m = (r + temp) % 10;
                resArr[col] = (char) ('0' + m);
                temp = (r + temp) / 10;
            }

            int startIndex = 0;
            for (int i = 0; i < resArr.length; i ++) {
                if (resArr[i] == '0') {
                    startIndex = i + 1;
                    continue;
                }
                break;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = startIndex; i < resArr.length; i ++) {
                sb.append(resArr[i]);
            }
            return sb.toString();
        }
    }

    /**
     * 翻转字符串里的单词
     *
     * 给定一个字符串，逐个翻转字符串中的每个单词。

     * 示例:

     * 输入: "the sky is blue",
     * 输出: "blue is sky the".
     * 说明:

     * 无空格字符构成一个单词。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    static class Solution4 {
        public static String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            s = s.trim();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            for (int i = s.length() - 1;i >=0; i --) {
                if (s.charAt(i) == ' '){
                    if (sb1.length() > 0) {
                        if (sb.length() > 0) {
                            sb.append(' ');
                        }
                        sb.append(reverseString(sb1.toString()));
                        sb1 = new StringBuilder();
                    }
                    continue;
                }
                sb1.append(s.charAt(i));
                if (i == 0) {
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    sb.append(reverseString(sb1.toString()));
                }
            }
            return sb.toString();
        }

        private static String reverseString(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i >=0; i --) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(reverseWords("the sky is blue"));
        }
    }

    /**
     * 简化路径
     *
     * 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。

     * 例如，
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"

     * 边界情况:

     * 你是否考虑了 路径 = "/../" 的情况？
     * 在这种情况下，你需返回 "/" 。
     * 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
     * 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。
     */
    static class Solution5 {
        // 解题思路，利用栈进行解决
        public static String simplifyPath(String path) {
            if (path == null || "".equals(path)) {
                return "";
            }

            LinkedList<String> queue = new LinkedList<>();
            // 分割字符串
            String[] strings = path.split("/");
            for (String str : strings){
                if ("".equals(str) || ".".equals(str)) {
                    continue;
                }
                if ("..".equals(str)){
                    if (!queue.isEmpty()) {
                        queue.removeLast();
                    }
                } else {
                    queue.add(str);
                }
            }

            if (queue.isEmpty()) {
                return "/";
            }

            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                String str = queue.pop();
                sb.append("/").append(str);
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(simplifyPath("/.."));
//            System.out.println(simplifyPath("/a/./b/../../c/"));
//            System.out.println(simplifyPath("/../"));
//            System.out.println(simplifyPath("/home//foo/"));
        }
    }

    /**
     * 复原IP地址
     *
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

     * 示例:

     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     */
    static class Solution6 {
        // ip地址验证
        // 1、总长度小于等于12
        // 2、每一个地址段数字在0~255的区间内，长度小于等于3
        // 3、总共有4个地址段
        public static List<String> restoreIpAddresses1(String s) {
            if (s.length() > 12 || s.length() < 4) {
                return new ArrayList<>();
            }
            for (char c : s.toCharArray()) {
                int num = c - '0';
                if (num < 0 || num > 9) {
                    return new ArrayList<>();
                }
            }
            // 暴力求解
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 3; i ++) {
                String fir = s.substring(0, i + 1);
                if (isVaild(fir)) {
                    for (int j = i + 1; j < s.length() - 2; j++) {
                        String sec = s.substring(i + 1, j + 1);
                        if (isVaild(sec)) {
                            for (int k = j + 1; k < s.length() - 1; k++) {
                                String t = s.substring(j + 1, k + 1);
                                if (isVaild(t)) {
                                    String f = s.substring(k + 1);
                                    if (isVaild(f)) {
                                        res.add(fir + "." + sec + "." + t + "." + f);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return res;
        }

        private static boolean isVaild(String s) {
            if (s == null || "".equals(s) || s.length() == 0) {
                return false;
            }
            if ("0".equals(s)) {
                return true;
            }
            if (s.startsWith("0")) {
                return false;
            }
            int num = Integer.parseInt(s);
            if (num >= 0 && num <= 255) {
                return true;
            }
            return false;
        }

        // 深度优先遍历
        public static List<String> restoreIpAddresses2(String s) {
            if (s.length() > 12 || s.length() < 4) {
                return new ArrayList<>();
            }
            for (char c : s.toCharArray()) {
                int num = c - '0';
                if (num < 0 || num > 9) {
                    return new ArrayList<>();
                }
            }
            List<String> res = new ArrayList<>();
            dfs(0, 0, res, new String[4], s);
            return res;
        }

        private static void dfs (int level, int index, List<String> res, String[] pathArr, String s) {
            // 递归结束条件，当前level为3，即已经填充了三个地址段
            if (level == 3) {
                String last = s.substring(index);
                if (isVaild(last)) {
                    StringBuilder path = new StringBuilder();
                    for (int i = 0; i < pathArr.length - 1; i ++) {
                        path.append(pathArr[i]).append(".");
                    }
                    path.append(last);
                    res.add(path.toString());
                }
                return;
            }

            // 递归主体，验证当前层的地址是否合法
            for (int i = index; i < index + 3 && i < s.length(); i ++) {
                String temp = s.substring(index, i + 1);
                if (isVaild(temp)) {
                    pathArr[level] = temp;
                    dfs(level + 1, i + 1, res, pathArr, s);
                }
            }
        }

        public static void main(String[] args) {
            System.out.println(restoreIpAddresses2("255255255255"));
        }
    }

    /**
     * LRU cache
     */
    static class Solution7 {
        /**
         * LRUCache cache = new LRUCache( 2 * 缓存容量 * );

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
        class LRUCache {
            private LRU cache;

            public LRUCache(int capacity) {
                this.cache = new LRU(capacity);
            }

            public int get(int key) {
                if (cache.containsKey(key)) {
                    return cache.get(key);
                }
                return -1;
            }

            public void put(int key, int value) {
                cache.put(key, value);
            }

            class LRU extends LinkedHashMap<Integer, Integer> {
                int capacity;

                public LRU(int capacity) {
                    // 注意，需要开启 ordering mode，此时若元素被访问（put、get）就会被移到链表尾
                    super(capacity, 0.75f, true);
                    this.capacity = capacity;
                }

                /**
                 * removeEldestEntry 方法会在插入元素之后调用，用以判断是否需要移除链表表头元素（最近最少访问元素）
                 */
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return this.size() > capacity;
                }
            }
        }
    }

    /**
     * 最小栈
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     */
    static class Solution8 {
        static class MinStack {
            private int min;
            private Stack<Integer> stack;

            /** initialize your data structure here. */
            public MinStack() {
                stack = new Stack<>();
            }

            public void push(int x) {
                stack.push(x);
                if (x < min || stack.size() == 1) {
                    min = x;
                }
            }

            public void pop() {
                stack.pop();
                if (stack.size() == 0) {
                    min = 0;
                    return;
                }
                min = stack.get(0);
                for (int i = 0;i < stack.size(); i ++) {
                    if (stack.get(i) < min) {
                        min = stack.get(i);
                    }
                }
            }

            public int top() {
                if (stack.size() == 0) {
                    return 0;
                }
                return stack.peek();
            }

            public int getMin() {
                return min;
            }
        }

        public static void main(String[] args) {
            MinStack minStack = new MinStack();
            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            System.out.println(minStack.getMin());
            minStack.pop();
            minStack.top();
            System.out.println(minStack.getMin());
        }
    }
}
