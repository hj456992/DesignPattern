package DataStructure;

import java.util.*;

/**
 * 描述:
 * Map题目练习
 *
 * @author 侯珏
 * @create 2018-11-26 16:43
 */
public class MapTest {

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     */
    static class Solution1 {

        /**
         * 思路：使用map来存储每个字节和每个字节出现的次数，来比较是否相同
         */
        static class Anagram {

            public boolean isAnagram(String s, String t) {
                if (s == null || t == null) {
                    return false;
                }
                if (s.length() != t.length()) {
                    return false;
                }
                Map<Character, Integer> mapS = new HashMap<>();

                for (int i = 0; i < s.length(); i ++) {
                    char cs = s.charAt(i);
                    if (mapS.containsKey(cs)) {
                        int count = mapS.get(cs);
                        count ++ ;
                        mapS.put(cs, count);
                    } else {
                        mapS.put(cs, 1);
                    }
                }

                for (int i = 0; i < t.length(); i ++) {
                    char cs = t.charAt(i);
                    if (!mapS.containsKey(cs)) {
                        return false;
                    }
                    int count = mapS.get(cs);
                    count --;
                    if (count == 0) {
                        mapS.remove(cs);
                    } else {
                        mapS.put(cs, count);
                    }
                }
                return mapS.isEmpty();
            }
        }

        public static void main(String[] args) {
            Anagram anagram = new Anagram();
            System.out.println(anagram.isAnagram("anagram","nagaram"));
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    static class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            if (nums.length <= 0) {
                return nums;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0;i < nums.length; i ++) {
                int result = target - nums[i];
                if (map.containsKey(result)) {
                    return new int[]{map.get(result), i};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     */
    static class Solution3 {
        /**
         * 思路，先排序，然后定义双指针，转化为num[i] + num[j] + num[k] = 0的问题(死背)
         * @param nums
         * @return
         */
        public static List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            int iNum =Integer.MAX_VALUE ;
            int jNum;
            int kNum;
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
                if (iNum == nums[i]) {
                    continue;
                }
                iNum = nums[i];
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum > 0) {
                        k--;
                    } else if (sum < 0) {
                        j++;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);

                        jNum = nums[j];
                        do {
                            j++;
                            if (j>=k) {
                                break;
                            }
                        } while (jNum == nums[j]);
                        kNum = nums[k];
                        do {
                            k--;
                            if (j>=k) {
                                break;
                            }
                        } while (kNum == nums[k]);
                    }
                }
            }
            return result;
        }

        // 无法解决排序问题，未解出答案
        public static List<List<Integer>> threeSum1(int[] nums) {
            if (nums.length <= 0) {
                return  new ArrayList<>();
            }
            Set<Integer[]> resultSet = new HashSet<>();

            // 排序，方便去重
            Arrays.sort(nums);// [-4,-1,-1,0,1,2]
            for (int i = 0; i < nums.length; i ++) {
                Set<Integer> set = new HashSet<>();
                for (int j = i + 1; j < nums.length; j ++) {
                    int res = 0 - (nums[i] + nums[j]);
                    if (set.contains(res)) {
                        Integer[] array = new Integer[]{nums[i], nums[j], res};
                        Arrays.sort(array);
                        resultSet.add(array);
                    }
                    set.add(nums[i]);
                    set.add(nums[j]);
                }
            }
            List<List<Integer>> result = new ArrayList<>();
            for (Integer[] array : resultSet) {
                result.add(Arrays.asList(array));
            }
            return result;
        }

        public static void main(String[] args) {
           int[] nums = {-1, 0, 1, 2, -1, -4};
            List<List<Integer>> res = threeSum(nums);
            for (List<Integer> list : res) {
                System.out.println(list.toString());
            }
        }
    }
}
