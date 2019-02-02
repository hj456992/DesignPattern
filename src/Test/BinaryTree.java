package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树练习题
 * Created by houjue on 2019-01-28.
 */
public class BinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    static class Solution1 {
        // 中序遍历：左子树-根节点-右子树

        // 递归解法
        List<Integer> res = new ArrayList<>();
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);

            return res;
        }

        // 迭代解法
        public List<Integer> inorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    res.add(cur.val);
                    cur = cur.right;
                }
            }

            return res;
        }
    }

    /**
     * 144. 二叉树的前序遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     *  示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    static class Solution2 {
        // 前序遍历：根节点-左子树-右子树

        // 递归实现
        List<Integer> res = new ArrayList<>();
        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);

            return res;
        }

        // 迭代实现
        public List<Integer> preorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    res.add(cur.val);
                    stack.push(cur.right);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                }
            }
            return res;
        }
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * 示例 1:
     *
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     */
    static class Solution3 {
        // 中序遍历找到最小值t，自增，等于k时返回节点值

        // 迭代法
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            int count = 0;
            while (cur != null || !stack.isEmpty() || count < k) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    if (++ count == k) {
                        return cur.val;
                    }
                    cur = cur.right;
                }
            }
            return -1;
        }

        // 递归法
        int count = 0;
        public int kthSmallest1(TreeNode root, int k) {
            if (root == null) {
                return 0;
            }

            int left = kthSmallest1(root.left, k);
            if (++ count == k) {
                return root.val;
            }
            if (count > k) {
                return left;
            }
            return kthSmallest1(root.right, k);
        }

    }
}
