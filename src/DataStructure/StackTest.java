package DataStructure;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 描述:
 * 堆栈题目练习
 *
 * @author 侯珏
 * @create 2018-11-23 23:59
 */
public class StackTest {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     *
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     *
     * 输入: "{[]}"
     * 输出: true
     */
    static class Solution1 {
        private static boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            Stack<Character> stack = new Stack<>();
            Map<Character, Character> paramMap = new HashMap<>();
            paramMap.put(')','(');
            paramMap.put(']','[');
            paramMap.put('}','{');

            for (int i = 0; i < s.length(); i ++) {
                Character c = s.charAt(i);
                if (paramMap.values().contains(c)) {
                    stack.push(c);
                } else if (paramMap.keySet().contains(c)) {
                    if (stack.isEmpty() || !stack.peek().equals(paramMap.get(c))) {
                        return false;
                    }
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }

        public static void main(String[] args) {
            String s1 = "()";
            String s2 = "()[]{}";
            String s3 = "(]";
            String s4 = "([)]";
            String s5 = "{[]}";
            System.out.println(isValid(s1));
            System.out.println(isValid(s2));
            System.out.println(isValid(s3));
            System.out.println(isValid(s4));
            System.out.println(isValid(s5));
        }
    }

    /**
     * 使用栈实现队列的下列操作：
     *
     * push(x) -- 将一个元素放入队列的尾部。
     * pop() -- 从队列首部移除元素。
     * peek() -- 返回队列首部的元素。
     * empty() -- 返回队列是否为空。
     * 示例:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // 返回 1
     * queue.pop();   // 返回 1
     * queue.empty(); // 返回 false
     */
    static class Solution2 {
        /**
         * Your MyQueue object will be instantiated and called as such:
         * MyQueue obj = new MyQueue();
         * obj.push(x);
         * int param_2 = obj.pop();
         * int param_3 = obj.peek();
         * boolean param_4 = obj.empty();
         */
        class MyQueue {
            Stack<Integer> inStack;
            Stack<Integer> outStack;

            /** Initialize your data structure here. */
            public MyQueue() {
                inStack = new Stack<>();
                outStack = new Stack<>();
            }

            /** Push element x to the back of queue. */
            public void push(int x) {
                inStack.push(x);
            }

            /** Removes the element from in front of queue and returns that element. */
            public int pop() {
                int result = peek();
                outStack.pop();
                return result;
            }

            /** Get the front element. */
            public int peek() {
                if (!outStack.isEmpty()) {
                   return outStack.peek();
                }
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
                return outStack.peek();
            }

            /** Returns whether the queue is empty. */
            public boolean empty() {
                return inStack.isEmpty() && outStack.isEmpty();
            }
        }
    }
}
