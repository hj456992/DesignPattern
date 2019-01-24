package DataStructure;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 描述:
 * 队列题目练习
 *
 * @author 侯珏
 * @create 2018-11-23 23:59
 */
public class QueueTest {

    /**
     * 使用队列实现栈的下列操作：
     *
     * push(x) -- 元素 x 入栈
     * pop() -- 移除栈顶元素
     * top() -- 获取栈顶元素
     * empty() -- 返回栈是否为空
     */
    static class Solution1 {
        /**
         * Your MyStack object will be instantiated and called as such:
         * MyStack obj = new MyStack();
         * obj.push(x);
         * int param_2 = obj.pop();
         * int param_3 = obj.top();
         * boolean param_4 = obj.empty();
         */
        static class MyStack {
            /**
             * 解题思路：定义两个个队列，一个暂存队列，一个出队列
             */
            static List<Integer> midQueue = new LinkedList<>();
            static List<Integer> outQueue = new LinkedList<>();

            /** Initialize your data structure here. */
            public MyStack() {

            }

            /** Push element x onto stack. */
            public void push(int x) {
                if (outQueue.isEmpty()) {
                    outQueue.add(x);
                } else {
                    midQueue.addAll(outQueue);
                    outQueue.clear();
                    outQueue.add(x);
                    if (!midQueue.isEmpty()) {
                        outQueue.addAll(midQueue);
                    }
                    midQueue.clear();
                }
            }

            /** Removes the element on top of the stack and returns that element. */
            public int pop() {
                if (outQueue.isEmpty()) {
                    return 0;
                }
                return outQueue.remove(0);
            }

            /** Get the top element. */
            public int top() {
                if (outQueue.isEmpty()) {
                    return 0;
                }
                return outQueue.get(0);
            }

            /** Returns whether the stack is empty. */
            public boolean empty() {
                return outQueue.isEmpty();
            }
        }

        public static void main(String[] args) {
            MyStack stack = new MyStack();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        }

    }
}
