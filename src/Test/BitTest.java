package Test;

/**
 * 位运算联系
 * Created by houjue on 2019-01-24.
 */
public class BitTest {
    /**
     * 231. 2的幂
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     *
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     *
     * 输入: 218
     * 输出: false
     */
    static class Solution1 {
        public static boolean isPowerOfTwo1(int n) {
            while (n > 1 && (n & 1) == 0) {
                n >>= 1;
            }
            return n == 1;
        }

        // 优化
        public static boolean isPowerOfTwo(int n) {
            return n > 0 && (n & n - 1) == 0;
        }

        public static void main(String[] args) {
            System.out.println(isPowerOfTwo(4));
        }
    }

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    static class Solution2 {
        // 异或：相同为0，不同为1，对同一个数异或两次，原数不变。
        // 异或也是位运算的加法，当对某个数异或两次时，等于执行了0 + 1、1 + 1，而1 + 1=0，相当于恢复了原数
        public static int singleNumber(int[] nums) {
            int a = 0;
            for (int num : nums) {
                a = a ^ num;
            }

            return a;
        }

        public static void main(String[] args) {
            System.out.println(singleNumber(new int[]{4,1,2,1,2}));
        }
    }
}
