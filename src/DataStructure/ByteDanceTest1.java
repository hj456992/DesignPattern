package DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 字节跳动题库练习题（1）
 *
 * @author 侯珏
 * @create 2018-11-26 23:56
 */
public class ByteDanceTest1 {
    /**
     * 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
    static class Solution1 {
        /**
         * 解题思路：使用滑动窗口
         * @param s
         * @return
         */
        public static int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            // 最长子串的长度
            int length = 0;
            // 使用hashMap来存储字符所在的位置索引
            Map<Character, Integer> map = new HashMap<>();
            // 建立一个滑动窗口[i,j]，j向后滑动，当j所在位置的字符出现在窗口中，将i移动到j1 + 1
            for (int j = 0, i = 0; j < s.length(); j ++) {
                if (map.keySet().contains(s.charAt(j))) {
                    // 此处加max原因，可参考用例"abba"
                    i = Math.max(map.get(s.charAt(j)) + 1, i);
                }
                map.put(s.charAt(j), j);
                length = Math.max(length, j - i + 1);
            }
            return length;
        }

        public static void main(String[] args) {
            String s1 = "abba";
            String s2 = "bbbbb";
            String s3 = "pwwkew";

            System.out.println(lengthOfLongestSubstring(s1));
            System.out.println(lengthOfLongestSubstring(s2));
            System.out.println(lengthOfLongestSubstring(s3));
        }
    }
}
