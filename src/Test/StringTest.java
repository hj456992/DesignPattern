package Test;

import java.util.*;

/**
 * Created by houjue on 2018/12/11.
 */
public class StringTest {
    /**
     * 重构字符串
     * <p>
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * <p>
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: S = "aab"
     * 输出: "aba"
     * 示例 2:
     * <p>
     * 输入: S = "aaab"
     * 输出: ""
     * 注意:
     * <p>
     * S 只包含小写字母并且长度在[1, 500]区间内。
     */
    static class Solution1 {
        public static String reorganizeString(String S) {
            if (S.length() == 0) {
                return "";
            }

            int length = S.length();
            int[] count = new int[26];
            for (int i = 0; i < S.length(); i++) {
                count[S.charAt(i) - 'a']++;
            }
            // 把字符串放入堆，优先级按照字符数量排序，字符数量相同按照aiscll码排序
            Queue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                    a.count == b.count ? b.letter - a.letter : b.count - a.count);
            for (int i = 0; i < 26; i++) {
                if (count[i] > (length + 1) / 2) {
                    return "";
                }
                if (count[i] > 0) {
                    pq.offer(new MultiChar(count[i], (char) ('a' + i)));
                }
            }
            StringBuilder sb = new StringBuilder();
            while (pq.size() >= 2) {
                MultiChar c1 = pq.poll();
                MultiChar c2 = pq.poll();

                sb.append(c1.letter);
                sb.append(c2.letter);

                if (--c1.count > 0) {
                    pq.offer(c1);
                }
                if (--c2.count > 0) {
                    pq.offer(c2);
                }
            }
            if (pq.size() > 0) {
                MultiChar c1 = pq.poll();
                sb.append(c1.letter);
            }
            return sb.toString();
        }

        static class MultiChar {
            int count;
            char letter;

            MultiChar(int ct, char ch) {
                count = ct;
                letter = ch;
            }
        }

        public static void main(String[] args) {
            System.out.println(reorganizeString("vvvlo"));
        }
    }

    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     * 示例 2:
     * <p>
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     */
    static class Solution2 {
        public int countSubstrings(String s) {
            return 0;
        }
    }

    /**
     * 最长回文子串
     * <p>
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     */
    static class Solution3 {
        // 动态规划，DP[i,j]代表s[i]到s[j]之间是否是回文字符串，true代表是回文字符串，false代表不是
        // DP[i,j] = DP[i + 1, j - 1] and s[i] == s[j]
        public static String longestPalindrome(String s) {
            if (s.length() == 0) {
                return "";
            }
            String result = "";

            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        // i,j相等的情况下，长度分别为0，1，2时肯定是回文字符串
                        if (j - i < 3 || dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                        }
                    }
                    if (result.length() >= j - i + 1) {
                        continue;
                    }
                    if (dp[i][j]) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
            return result;
        }

        // 暴力求解
        public static String longestPalindrome1(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            if (s.length() == 1) {
                return s;
            }
            String result = "";
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j <= s.length(); j++) {
                    if (result.length() < s.substring(i, j).length()) {
                        if (isPalindrome(s.substring(i, j))) {
                            result = s.substring(i, j);
                        }
                    }
                }
            }
            return result;
        }

        private static boolean isPalindrome(String s) {
            if (s.length() == 1) {
                return true;
            }
            for (int i = 0; i < s.length() / 2; i++) {
                int end = s.length() - 1 - i;
                if (s.charAt(i) != s.charAt(end) && i != end) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            System.out.println(longestPalindrome("aaaa"));
        }
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * <p>
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * <p>
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * <p>
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * <p>
     * 说明：
     * <p>
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * <p>
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * <p>
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * <p>
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     * 因此无法执行有效的转换。
     * 示例 5:
     * <p>
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     * 因此返回 INT_MIN (−2^31) 。
     */
    static class Solution4 {
        public static int myAtoi(String str) {
            str = str.trim();
            if (str.length() < 1) {
                return 0;
            }

            if (str.charAt(0) < '0' || str.charAt(0) > '9') {
                if (!(str.charAt(0) == '-' || str.charAt(0) == '+')) {
                    return 0;
                }
            }
            boolean flag = str.charAt(0) != '-';

            int ret = 0;
            for (int i = 0; i < str.length(); i++) {
                if (i == 0 && (str.charAt(i) == '-' || str.charAt(0) == '+')) {
                    continue;
                }
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    break;
                }
                if (ret > Integer.MAX_VALUE / 10) {
                    return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                if (ret == Integer.MAX_VALUE / 10) {
                    int digit = 0;
                    if (flag) {
                        digit = Integer.MAX_VALUE % 10;
                        if (str.charAt(i) - '0' > digit) {
                            return Integer.MAX_VALUE;
                        }
                    } else {
                        digit = Integer.MAX_VALUE % 10 + 1;
                        if (str.charAt(i) - '0' > digit) {
                            return Integer.MIN_VALUE;
                        }
                    }

                }
                ret = ret * 10 + (str.charAt(i) - '0');
            }
            return flag ? ret : -ret;
        }

        public static void main(String[] args) {
            System.out.println(myAtoi(" 0000000000012345678"));
            System.out.println(myAtoi(" "));
            System.out.println(myAtoi("+1"));
            System.out.println(myAtoi("-+1"));
            System.out.println(myAtoi("-"));
            System.out.println(myAtoi("   -42"));
            System.out.println(myAtoi("4193 with words"));
            System.out.println(myAtoi("words and 987"));
            System.out.println(myAtoi("-2147483648"));
        }
    }

    /**
     * 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶:
     * <p>
     * 你能不将整数转为字符串来解决这个问题吗？
     */
    static class Solution5 {
        public static boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            if (x < 10) {
                return true;
            }
            int reverse = 0;
            int a = x;
            while (a > 0) {
                reverse = reverse * 10 + a % 10;
                a = a / 10;
            }
            return reverse == x;
        }

        public static void main(String[] args) {
            System.out.println(isPalindrome(1234567899));
            System.out.println(isPalindrome(-121));
            System.out.println(isPalindrome(1));
        }
    }

    /**
     * 224. 基本计算器
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     * <p>
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     * <p>
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * 说明：
     * <p>
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     */
    static class Solution6 {
        public static int calculate(String s) {
            // 当前的数字
            int num = 0;
            // 操作符+(1)、-(-1)
            int op = 1;
            // 结果
            int res = 0;

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case ' ':
                        break;
                    case '+':
                        res = res + num * op;
                        num = 0;
                        op = 1;
                        break;
                    case '-':
                        res = res + num * op;
                        num = 0;
                        op = -1;
                        break;
                    case '(':
                        stack.push(res);
                        stack.push(op);
                        res = 0;
                        num = 0;
                        op = 1;
                        break;
                    case ')':
                        res = (res + num * op) * stack.pop() + stack.pop();
                        num = 0;
                        op = 1;
                        break;
                    default:
                        num = num * 10 + (s.charAt(i) - '0');
                        break;
                }
            }
            return res + num * op;
        }

        public static void main(String[] args) {
            System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        }
    }

    /**
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     * <p>
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     * <p>
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     * <p>
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    static class Solution7 {
        public static int romanToInt(String s) {
            if (s.length() == 1) {
                switch (s.charAt(0)) {
                    case 'I':
                        return 1;
                    case 'V':
                        return 5;
                    case 'X':
                        return 10;
                    case 'L':
                        return 50;
                    case 'C':
                        return 100;
                    case 'D':
                        return 500;
                    case 'M':
                        return 1000;
                }
            }
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'I' && i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'V') {
                        res += 4;
                        i++;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'X') {
                        res += 9;
                        i++;
                        continue;
                    }
                }
                if (s.charAt(i) == 'X' && i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'L') {
                        res += 40;
                        i++;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'C') {
                        res += 90;
                        i++;
                        continue;
                    }
                }
                if (s.charAt(i) == 'C' && i < s.length() - 1) {
                    if (s.charAt(i + 1) == 'D') {
                        res += 400;
                        i++;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'M') {
                        res += 900;
                        i++;
                        continue;
                    }
                }
                switch (s.charAt(i)) {
                    case 'I':
                        res += 1;
                        break;
                    case 'V':
                        res += 5;
                        break;
                    case 'X':
                        res += 10;
                        break;
                    case 'L':
                        res += 50;
                        break;
                    case 'C':
                        res += 100;
                        break;
                    case 'D':
                        res += 500;
                        break;
                    case 'M':
                        res += 1000;
                        break;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(romanToInt("III"));
            System.out.println(romanToInt("IV"));
            System.out.println(romanToInt("IX"));
            System.out.println(romanToInt("LVIII"));
            System.out.println(romanToInt("MCMXCIV"));

        }
    }

    /**
     * 12. 整数转罗马数字
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 3
     * 输出: "III"
     * 示例 2:
     * <p>
     * 输入: 4
     * 输出: "IV"
     * 示例 3:
     * <p>
     * 输入: 9
     * 输出: "IX"
     * 示例 4:
     * <p>
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     * <p>
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    static class Solution8 {
        // 暴力
        public static String intToRoman(int num) {
            Stack<Integer> stack = new Stack<>();
            int n = 1;
            while (num != 0) {
                int a = num % 10;
                if (a == 9) {
                    stack.push(a * n);
                } else if (a >= 5) {
                    while (a % 5 > 0) {
                        stack.push(n);
                        a--;
                    }
                    while (a / 5 > 0) {
                        stack.push(5 * n);
                        a = a - 5;
                    }
                } else if (a >= 4) {
                    while (a / 4 > 0) {
                        stack.push(4 * n);
                        a = a - 4;
                    }
                } else {
                    while (a > 0) {
                        stack.push(n);
                        a--;
                    }
                }
                num = num / 10;
                n = n * 10;
            }
            StringBuilder ret = new StringBuilder();
            while (!stack.isEmpty()) {
                int a = stack.pop();
                switch (a) {
                    case 1:
                        ret.append("I");
                        break;
                    case 4:
                        ret.append("IV");
                        break;
                    case 5:
                        ret.append("V");
                        break;
                    case 9:
                        ret.append("IX");
                        break;
                    case 10:
                        ret.append("X");
                        break;
                    case 40:
                        ret.append("XL");
                        break;
                    case 50:
                        ret.append("L");
                        break;
                    case 90:
                        ret.append("XC");
                        break;
                    case 100:
                        ret.append("C");
                        break;
                    case 400:
                        ret.append("CD");
                        break;
                    case 500:
                        ret.append("D");
                        break;
                    case 900:
                        ret.append("CM");
                        break;
                    case 1000:
                        ret.append("M");
                        break;
                }
            }
            return ret.toString();
        }

        // 简化
        public static String intToRoman1(int num) {
            int[] val = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 13; i++) {
                while (num >= val[i]) {
                    sb.append(romans[i]);
                    num = num - val[i];
                }
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            String roman = intToRoman1(1994);
            System.out.println(roman);
            int val = Solution7.romanToInt(roman);
            System.out.println(val);
        }
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     */
    static class Solution9 {
        public static int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int st = 0;
            int similar = Integer.MAX_VALUE;
            int res = 0;
            while (st < nums.length - 2) {
                int num1 = nums[st];
                int i = st + 1;
                int j = nums.length - 1;
                while (i < j) {
                    int num2 = nums[i];
                    int num3 = nums[j];
                    int sum = num1 + num2 + num3;
                    if (sum == target) {
                        return target;
                    }
                    if (sum < target) {
                        if (target - sum < similar) {
                            similar = target - sum;
                            res = sum;
                        }
                        i++;
                    }
                    if (sum > target) {
                        if (sum - target < similar) {
                            similar = sum - target;
                            res = sum;
                        }
                        j--;
                    }
                }
                st++;
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4, 6}, 8));
        }
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例:
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    static class Solution10 {
        static Map<Character, String> map = new HashMap<>();
        static {
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
        }

        public static List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return new ArrayList<>();
            }
            if (digits.length() == 1) {
                List<String> result = new ArrayList<>();
                for (char c : map.get(digits.charAt(0)).toCharArray()) {
                    result.add(String.valueOf(c));
                }
                return result;
            }
            String[] sArr = new String[digits.length()];
            for (int i = 0; i < digits.length(); i ++) {
                sArr[i] = map.get(digits.charAt(i));
            }

            List<String> result = new ArrayList<>();
            dfs(sArr, 0, new StringBuilder(), result);
            return result;
        }

        private static void dfs(String[] sArr, int i, StringBuilder sb, List<String> result) {
            // terminal
            if (i == sArr.length - 1) {
                for (int k = 0;k < sArr[i].length(); k ++) {
                    result.add(sb.toString() + sArr[i].charAt(k));
                }
                return;
            }


            for (int j = 0; j < sArr[i].length(); j ++) {
                sb.append(sArr[i].charAt(j));
                dfs(sArr, i + 1, sb, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        public static void main(String[] args) {
            System.out.println(letterCombinations("2"));
        }
    }

    /**
     * 18. 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

     * 注意：

     * 答案中不可以包含重复的四元组。

     * 示例：

     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

     * 满足要求的四元组集合为：
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     */
    static class Solution11 {
        // 暴力，会有重复
        public static List<List<Integer>> fourSum(int[] nums, int target) {

            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length - 3; i ++) {
                for (int j = i + 1; j < nums.length - 2; j ++) {
                    for (int k = j + 1;k < nums.length - 1; k ++) {
                        for (int t = k + 1; t < nums.length; t ++) {
                            if (nums[i] + nums[j] + nums[k] + nums[t] == target) {
                                List<Integer> list = new ArrayList<>();
                                list.add(nums[i]);
                                list.add(nums[j]);
                                list.add(nums[k]);
                                list.add(nums[t]);
                                res.add(list);
                            }
                        }
                    }
                }
            }
            return res;
        }

        // 优化，类似三数和
        public static List<List<Integer>> fourSum1(int[] nums, int target) {
            if (nums.length < 4) {
                return new ArrayList<>();
            }
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            for (int i = 0; i < nums.length - 3 ; i ++) {
                int iCount = 1;
                if (i > 0 && nums[i] == nums[i - 1]){
                    continue;
                }
                for (int t = i + 1; t < nums.length - 2; t ++) {
                    if (nums[t] == nums[i]) {
                        iCount ++;
                        if (iCount > 2) {
                            continue;
                        }
                    }
                    if (t > i + 1 && nums[t] == nums[t - 1]){
                        continue;
                    }

                    int num1 = nums[i];
                    int num2 = nums[t];
                    int j = t + 1;
                    int k = nums.length - 1;
                    Set<Integer> set = new HashSet<>();
                    while (j < k) {
                        int sum = num1 + num2 + nums[j] + nums[k];
                        if (sum == target) {
                            if (!set.contains(nums[j])) {
                                List<Integer> list = new ArrayList<>();
                                list.add(num1);
                                list.add(num2);
                                list.add(nums[j]);
                                list.add(nums[k]);
                                res.add(list);

                                set.add(nums[j]);
                                set.add(nums[k]);
                            }

                            j ++;
                            k --;
                        }
                        if (sum < target) {
                            j ++;
                        }
                        if (sum > target) {
                            k --;
                        }
                    }
                }
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(fourSum1(new int[]{1, 0, -1, 0, -2, 2}, 0));
            System.out.println(fourSum1(new int[]{-1,-5,-5,-3,2,5,0,4}, -7));
            System.out.println(fourSum1(new int[]{-1,0,-5,-2,-2,-4,0,1,-2}, -9));
        }
    }

    /**
     * 11. 盛最多水的容器
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     */
    static class Solution12 {
        public static int maxArea(int[] height) {
            return 0;
        }

        public static void main(String[] args) {
            System.out.println(maxArea(new int[]{2,3,10,5,7,8,9}));
        }
    }

    /**
     * 28. 实现strStr()
     * 实现 strStr() 函数。

     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

     * 示例 1:

     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:

     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:

     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */
    static class Solution13 {
        public static int strStr(String haystack, String needle) {
            if (needle == null || "".equals(needle.trim()) ) {
                return 0;
            }
            if (haystack.length() == 0) {
                return -1;
            }
            needle = needle.trim();
            int nl = needle.length();

            for (int i = 0; i < haystack.length() - nl + 1; i ++) {
                String sub = haystack.substring(i, i + nl);
                if (sub.equals(needle)) {
                    return i;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            System.out.println(strStr("mississippi", "pi"));
            System.out.println(strStr("a", "a"));

        }
    }

    /**
     * 29. 两数相除

     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。

     * 示例 1:

     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     *
     * 示例 2:
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     *
     * 说明:
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。

     */
    static class Solution14 {
        // 暴力，会超时
        public static int divide(int dividend, int divisor) {
            if (divisor == 0) {
                return Integer.MAX_VALUE;
            }
            if (divisor == 1) {
                return dividend;
            }
            if (dividend == 0) {
                return 0;
            }
            boolean positive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
            if (dividend > 0) {
                dividend = -dividend;
            }
            if (divisor > 0) {
                divisor = -divisor;
            }
            if (divisor < (dividend / 2)) {
                return positive ? 1 : -1;
            }
            int count = 0;
            while (dividend <= divisor) {
                count ++;
                if (count == Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                dividend -= divisor;
            }
            return positive ? count : -count;
        }

        public static int divide1(int dividend, int divisor) {
            if (divisor == 1) {
                return dividend;
            }
            if (dividend == 0) {
                return 0;
            }
            boolean positive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
            long ndividend = dividend, ndivisor = divisor;
            if (ndivisor < 0) {
                ndivisor = -ndivisor;
            }
            if (ndividend < 0) {
                ndividend = -ndividend;
            }
            if (ndivisor > ndividend) {
                return 0;
            }
            if (ndivisor > (ndividend / 2)) {
                return positive ? 1 : -1;
            }
            int mul = 1;
            int res = 0;
            long mod = 0;
            while (ndivisor <= (ndividend >> 1)) {
                mod = mod + (ndividend & 1) * mul;
                ndividend = ndividend >> 1;

                mul <<= 1;
                if (ndivisor >= (ndividend >> 1)) {
                    if (Integer.MAX_VALUE - mul < res) {
                        return Integer.MAX_VALUE;
                    }
                    res += mul;
                    long gap = ndividend-ndivisor;
                    while (mul > 1) {
                        gap <<= 1;
                        mul >>= 1;
                    }
                    ndividend = mod + gap;
                    mul = 1;
                    mod = 0;
                }
            }
            if (ndividend > ndivisor) {
                res ++;
            }

            return positive ? res : -res;
        }

        // 优化
        public static int divide2(int dividend, int divisor) {
            if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
            if(dividend > 0 && divisor > 0) return divideHelper(-dividend, -divisor);
            else if(dividend > 0) return -divideHelper(-dividend,divisor);
            else if(divisor > 0) return -divideHelper(dividend,-divisor);
            else return divideHelper(dividend, divisor);
        }

        private static int divideHelper(int dividend, int divisor){
            // base case
            if(divisor < dividend) return 0;
            // get highest digit of divisor
            int cur = 0, res = 0;
            while((divisor << cur) >= dividend && divisor << cur < 0 && cur < 31) cur++;
            res = dividend - (divisor << cur-1);
            if(res > divisor) return 1 << cur-1;
            return (1 << cur-1)+divide2(res, divisor);
        }

        public static void main(String[] args) {
////            long c1 = System.currentTimeMillis();
//            System.out.println(divide1(-Integer.MAX_VALUE, Integer.MIN_VALUE));
//            System.out.println(-Integer.MAX_VALUE /Integer.MIN_VALUE);
            System.out.println(divide2(Integer.MAX_VALUE, 11));
            System.out.println(divide1(Integer.MAX_VALUE, 11));


//            System.out.println(divide1(Integer.MAX_VALUE, Integer.MIN_VALUE));
//            System.out.println(Integer.MAX_VALUE /Integer.MIN_VALUE);
//            long c2 = System.currentTimeMillis();
//            System.out.println(c2 - c1);

//            c1 = System.currentTimeMillis();
//            System.out.println(divide(1, 2));
//            c2 = System.currentTimeMillis();
//            System.out.println(c2 - c1);

        }
    }

    /**
     * 30. 与所有单词相关联的字串
     * 给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。

     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

     * 示例 1:

     * 输入:
     * s = "barfoothefoobarman",
     * words = ["foo","bar"]
     * 输出: [0,9]
     * 解释: 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     * 示例 2:

     * 输入:
     * s = "wordgoodstudentgoodword",
     * words = ["word","student"]
     * 输出: []
     */
    static class Solution15 {
        public List<Integer> findSubstring(String s, String[] words) {
            if (!check(s, words)) {
                return new ArrayList<>();
            }
            return null;
        }

        private static boolean check(String s, String[] words) {
            if (s.length() == 0 || words.length == 0) {
                return false;
            }
            int l = words[0].length();
            for (String word : words) {
                if (word.length() != l) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 32. 最长有效括号
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

     * 示例 1:
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"

     * 示例 2:
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     */
    static class Solution16 {
        static Map<Character, Character> map = new HashMap<>();
        static {
            map.put(')','(');
        }

        public static boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push('(');
                } else if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            return stack.empty();
        }

        // 1、暴力求解，i从0开始，j从i开始
        public static int longestValidParentheses(String s) {
            int maxlen = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 2; j <= s.length(); j+=2) {
                    if (isValid(s.substring(i, j))) {
                        maxlen = Math.max(maxlen, j - i);
                    }
                }
            }
            return maxlen;
        }

        // 2、栈解法，思路，定义两个指针i，j，i初始位置为j-t，t从1开始，当j走到)的位置时，计算i位置是否为(，如果是则t+1，i，j位置标为true
        public static int longestValidParentheses1(String s) {
            if (s.length() <= 1) {
                return 0;
            }
            boolean[] vaild = new boolean[s.length()];
            for (int j = 1; j < s.length(); j ++) {
                if (s.charAt(j) == ')') {
                    for (int i = j - 1; i >= 0 ;i --) {
                        if (!vaild[i]) {
                            if (s.charAt(i) == '(') {
                                vaild[i] = true;
                                vaild[j] = true;
                            }
                            break;
                        }
                    }

                }
            }

            int res = 0;
            int length = 0;
            for (int i = 0;i < vaild.length; i ++) {
                if (vaild[i]) {
                    length ++;
                } else {
                    res = Math.max(res, length);
                    length = 0;
                }
            }

            return Math.max(res,length);
        }

        // 2.1栈解法优化
        public int longestValidParentheses11(String s) {
            int maxans = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(i);
                    } else {
                        maxans = Math.max(maxans, i - stack.peek());
                    }
                }
            }
            return maxans;
        }

        // 3动态规划
        public int longestValidParentheses2(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }

        // 4数学解法，从左到右进行遍历，再从右到左进行遍历，取最大值
        public int longestValidParentheses3(String s) {
            int left = 0, right = 0, maxlength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * right);
                } else if (right >= left) {
                    left = right = 0;
                }
            }
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * left);
                } else if (left >= right) {
                    left = right = 0;
                }
            }
            return maxlength;
        }

        public static void main(String[] args) {
            String s = "(()())";
            System.out.println(longestValidParentheses1(s));

            s = "())";
            System.out.println(longestValidParentheses1(s));

            s = "(()";
            System.out.println(longestValidParentheses1(s));

            String s1 = ")()())";
            System.out.println(longestValidParentheses1(s1));
//
            String s2 =
                    "";
            System.out.println(longestValidParentheses1(s2));
        }
    }

    /**
     * 38. 报数
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

     * 注意：整数顺序将表示为一个字符串。



     * 示例 1:

     * 输入: 1
     * 输出: "1"
     * 示例 2:

     * 输入: 4
     * 输出: "1211"
     */
    static class Solution17 {
        // 6.   312211
        // 7.   13112221
        // 思路，通过归纳观察可得，后一个报数由前一个报数从左到右，相同的数的个数(count)+数(prev)组成
        public static String countAndSay(int n) {
            String s = "";
            for (int i = 1; i <= n; i ++) {
                if (i == 1) {
                    s = "1";
                } else {
                    char[] ca = s.toCharArray();
                    StringBuilder nextS = new StringBuilder();
                    char count = '1';
                    char prev = ca[0];
                    for (int j = 1; j < ca.length; j++) {
                        if (ca[j] == prev) {
                            count += 1;
                        } else {
                            nextS.append(count);
                            nextS.append(prev);
                            count = '1';
                        }
                        prev = ca[j];
                    }
                    nextS.append(count);
                    nextS.append(prev);
                    s = nextS.toString();
                }
            }
            return s;
        }

        public static void main(String[] args) {
            for (int i = 1; i <= 10; i ++) {
                System.out.println(countAndSay(i));
            }
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc"
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     */
    static class Solution18 {
        public static String reverseWords1(String s) {
            StringBuilder newString = new StringBuilder();
            for (int i = 0; i < s.length(); i ++) {
                if (s.charAt(i) == ' ') {
                    newString.append(' ');
                    continue;
                }
                char[] word = new char[s.length()];
                for (int j = i; j <= s.length(); j ++) {
                    if (j == s.length() || s.charAt(j) == ' ') {
                        for (int k = j - 1; k >= i; k --) {
                            newString.append(word[k]);
                        }
                        if (j != s.length()) {
                            newString.append(' ');
                        }
                        i = j;
                        break;
                    }
                    word[j] = s.charAt(j);
                }
            }

            return newString.toString();
        }

        // 优化
        public static String reverseWords(String s) {

            int len = s.length();
            if(len <=1) return s;
            char[] arr = s.toCharArray();
            int start = 0;
            while(start < len){
                int end = s.indexOf(' ', start);
                if(end == -1){
                    reverse(arr, start, len -1);
                    break;
                }
                else{
                    reverse(arr, start, end - 1);
                    start = end + 1;
                }

            }
            // return arr.toString();
            return String.valueOf(arr);
        }
        public static void reverse(char[] arr, int a , int b){
            while(a < b){
                char tmp = arr[a];
                arr[a] = arr[b];
                arr[b] = tmp;
                a ++;
                b--;
            }
        }

        public static void main(String[] args) {
            System.out.println(reverseWords("  Let's take LeetCode contest"));
        }
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     */
    static class Solution19 {
        public void reverseString(char[] s) {
            int i = 0;
            int j = s.length - 1;
            while (i < j) {
                char t = s[i];
                s[i] = s[j];
                s[j] = t;
                i ++;
                j --;
            }
        }
    }
}
