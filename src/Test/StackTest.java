package Test;

import java.util.*;

/**
 * Created by houjue on 2018/11/20.
 */
public class StackTest {
    public static void main(String[] args) {
        // 测试练习1
//        String s1 = "()";
//        String s2 = ")(";
//        String s3 = "[()]";
//        String s4 = "(])";
//        String s5 = "((123 + 234))";
//        String s6 = "(([[]]))1234";
//
//        System.out.println("s1 is " + isStrVaild(s1));
//        System.out.println("s2 is " + isStrVaild(s2));
//        System.out.println("s3 is " + isStrVaild(s3));
//        System.out.println("s4 is " + isStrVaild(s4));
//        System.out.println("s5 is " + isStrVaild(s5));
//        System.out.println("s6 is " + isStrVaild(s6));

        // 测试练习2
        List<String> s1 = new ArrayList<>();
        s1.add("1");
        s1.add("2");
        s1.add("3");
        s1.add("4");
        stackToQueue(s1);
    }

    // 栈练习1：检验字符串是否有效
    private static boolean isStrVaild(String s) {
        // 初始化参数map
        Map<Character, Character> paramStrMap = new HashMap<>();
        paramStrMap.put(')','(');
        paramStrMap.put(']','[');
        // 堆及参数初始化
        Stack<Character> stack = new Stack<Character>();
        List<Character> values = new ArrayList<>(paramStrMap.values());
        List<Character> keys = new ArrayList<>(paramStrMap.keySet());
        for (int i = 0 ;i < s.length(); i ++) {
            Character c =  s.charAt(i);
            if (values.contains(c)) {
                stack.push(c);
            } else if (keys.contains(c)) {
                // 堆为空，直接返回
                if (stack.isEmpty()) {
                    return false;
                }
                // 栈是先入后出，peek的值为当前栈头
                if (paramStrMap.get(c).equals(stack.peek())){
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    // 栈练习2：栈转队列
    private static void stackToQueue(List<String> strings) {
        /**
         * 思路：
         * 栈是先入后出，队列是先入先出。所以栈实现队列的话，可以创建两个栈，一个为输入栈，一个为输出栈，
         * 通过将数据从输入栈转入输出栈，对数据的顺序给颠倒，从而从输出栈中获取的数据看似队列的样子。
         */
        Stack<String> inStack = new Stack<String>();
        Stack<String> outStack = new Stack<String>();

        // 往输入栈中加入数据
        for (String s : strings) {
            inStack.push(s);
        }
        // 从输入栈，往输出栈中加入数据
        while (!inStack.isEmpty()) {
            System.out.println(inStack.peek());
            outStack.push(inStack.pop());
        }

        while (!outStack.isEmpty()) {
            System.out.println(outStack.pop());
        }
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     */
    static class Solution2 {
        // 定义l指向当前的高，r向后移动，如果当前位置的值小于l，放入数组中，当前位置大于等于l，进行计算
        // 相当于把原数组拆成两个由低到高的数组，再进行计算
        public static int trap0(int[] height) {
            int l = 0;
            int[] stackL = new int[height.length];
            int sl = 0;
            int res = 0;
            for (int i = 0; i < height.length; i ++) {
                if (height[i] >= height[l]) {
                    if (sl != 0) {
                        for (int j = sl - 1; j > 0; j--) {
                            res += (height[l] - stackL[j]);
                        }
                    }
                    l = i;
                    sl = 0;
                }
                stackL[sl ++] = height[i];
            }

            if (sl != 0) {
                l = sl - 1;
                int sr = 0;
                int[] stackR = new int[sl];
                for (int i = sl - 1; i >= 0; i --) {
                    if (stackL[i] >= stackL[l]) {
                        if (sr != 0) {
                            for (int j = sr - 1; j > 0; j--) {
                                res += (stackL[l] - stackR[j]);
                            }
                        }
                        l = i;
                        sr = 0;
                    }
                    stackR[sr ++] = stackL[i];
                }
            }

            return res;
        }

        // 算法优化，两边往中间夹
        public static int trap1(int[] height) {
            if (height.length < 3) {
                return 0;
            }
            int l = 0;
            int r = height.length - 1;
            //find the left and right edge which can hold water
            while (l < r && height[l] <= height[l + 1]) {
                l ++;
            }
            while (l < r && height[r] <= height[r - 1]) {
                r --;
            }

            int ans = 0;
            while (l < r) {
                int left = height[l];
                int right = height[r];
                if (left <= right) {
                    // add volum until an edge larger than the left edge
                    while (l < r && height[++l] <= left) {
                        ans += (left - height[l]);
                    }
                } else {
                    // add volum until an edge larger than the right edge
                    while (l < r && height[--r] <= right) {
                        ans += (right - height[r]);
                    }
                }
            }
            return ans;
        }

        public static void main(String[] args) {
//            System.out.println(trap1(new int[]{2,0,2}));
            System.out.println(trap1(new int[]{5,2,6,3,4}));
//            System.out.println(trap1(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
//            System.out.println(trap1(new int[]{0}));
        }
    }
}
