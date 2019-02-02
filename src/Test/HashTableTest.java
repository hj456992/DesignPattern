package Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希表练习
 * Created by houjue on 2019-01-29.
 */
public class HashTableTest {

    /**
     * 599. 两个列表的最小索引总和
     * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     *
     * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
     *
     * 示例 1:
     *
     * 输入:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     * 输出: ["Shogun"]
     * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
     * 示例 2:
     *
     * 输入:
     * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
     * ["KFC", "Shogun", "Burger King"]
     * 输出: ["Shogun"]
     * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
     * 提示:
     *
     * 两个列表的长度范围都在 [1, 1000]内。
     * 两个列表中的字符串的长度将在[1，30]的范围内。
     * 下标从0开始，到列表的长度减1。
     * 两个列表都没有重复的元素。
     */
    static class Solution1 {
        public static String[] findRestaurant(String[] list1, String[] list2) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < list1.length; i ++) {
                map.put(list1[i], i);
            }

            int min = Integer.MAX_VALUE;
            String[] list3 = new String[list1.length];
            int j = 0;
            for (int i = 0; i < list2.length; i ++) {
                if (map.containsKey(list2[i])) {
                    if (min == map.get(list2[i]) + i) {
                        list3[j++] = list2[i];
                    } else if (min > map.get(list2[i]) + i) {
                        j = 0;
                        list3[j++] = list2[i];
                        min = map.get(list2[i]) + i;
                    }
                }
            }
            String[] res = new String[j];
            for (int i = 0; i < j; i ++) {
                res[i] = list3[i];
            }
            return res;
        }

        public static void main(String[] args) {
//            System.out.println(findRestaurant(new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
//                    new String[]{"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"}));

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(1,1);
            map.put(12,1);
            map.put(13,1);
            map.put(14,1);
            map.put(15,1);
            map.put(16,1);
            map.put(17,1);
            map.put(18,1);
            map.put(19,1);
            map.put(111,1);
            map.put(112,1);
            map.put(113,1);
            map.put(114,1);
            map.put(115,1);
        }
    }
}
