package Algorithm;

/**
 * 描述:
 * 分治算法练习
 *
 * @author 侯珏
 * @create 2018-11-30 0:54
 */
public class DivideConquer {
    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     *
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     *
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     *
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
     */
    static class Solution1 {
        /**
         * 思路：将x的n次方转化为x的n/2次方*x的n/2次方的问题，依次循环下去，直到n/2=0或者n/2=1。
         * 此方法容易超时，或者栈溢出
         */
        public static double myPow(double x, int n) {
            // 递归终止条件
            if (n == 0) {
                return 1;
            }

            // 切分子问题,n 小于 0， n 是奇数，n 是偶数
            if (n < 0) {
                return n == Integer.MIN_VALUE ?  (1/x) * (1 / myPow(x, -n)): 1 / myPow(x, -n);
            }
            if (n % 2 == 1) {
                return x * myPow(x, n - 1);
            }
            return myPow(x * x, n/2);
        }

        /**
         * while循环，采用位运算
         * 推荐使用
         */
        public static double myPow2(double x, int n) {
            // 边界值处理
            double res = 1;
            if (n == Integer.MIN_VALUE) {
                n = n + 1;
                res *= x;
            }
            if (n < 0) {
                x = 1 / x;
                n = -n;
            }

            while (n > 0) {
                // n&1==1代表奇数，因为奇数最后一位恒为1，且n经过位运算最后一次一定为2的0次方，即1，所以该步一定会判断到
                if ((n&1) == 1) {
                    res *= x;
                }
                x *= x;
                // n右移一位，做折半处理
                n >>=1;
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(myPow(1, -2147483648));
            System.out.println(myPow2(2,  4));
        }
    }
}
