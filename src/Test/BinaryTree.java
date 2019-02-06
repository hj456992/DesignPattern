package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 二叉树练习题
 * Created by houjue on 2019-01-28.
 */
public class BinaryTree {
    public static class TreeNode {
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

    /**
     * 124. 二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     */
    static class Solution4 {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只有两种可能:
         1. 子节点的最大路径由其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成
         2. 最终的最大路径由左右子树都在最大路径中, 加上该节点的值构成
         **/
        int max = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }

        private int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int maxLeft = Math.max(0, dfs(root.left));
            int maxRight =  Math.max(0, dfs(root.right));
            // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
            max = Math.max(max, maxLeft + maxRight + root.val);

            return Math.max(maxLeft, maxRight) + root.val;
        }
    }

    /**
     * 687. 最长同值路径
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     *
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     *
     * 示例 1:
     *
     * 输入:
     *
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     * 输出:
     *
     * 2
     * 示例 2:
     *
     * 输入:
     *
     *               1
     *              / \
     *             4   5
     *            / \   \
     *           4   4   5
     * 输出:
     *
     * 2
     */
    static class Solution5 {
        // 解题思路类似于124题,
        // 对于任意一个节点, 如果最长同值路径包含该节点, 那么只可能是两种情况:
        // 1. 其左右子树中加上该节点后所构成的同值路径中较长的那个继续向父节点回溯构成最长同值路径
        // 2. 左右子树加上该节点都在最长同值路径中, 构成了最终的最长同值路径
        // 需要注意因为要求同值, 所以在判断左右子树能构成的同值路径时要加入当前节点的值作为判断依据
        private static int maxL = 0;
        private static int longestUnivaluePath(TreeNode root) {
            if (root == null) {
                return maxL;
            }
            dfs(root, root.val);
            return maxL;
        }

        private static int dfs(TreeNode root, int val) {
            if (root == null) {
                return 0;
            }
            int leftL = dfs(root.left, root.val);
            int rightL = dfs(root.right, root.val);
            maxL = Math.max(leftL + rightL, maxL);
            if (val == root.val) {
                // 左右子树较长的向上回溯
                return Math.max(leftL, rightL) + 1;
            }
            return 0;
        }

        private static class MainClass {
            public static TreeNode stringToTreeNode(String input) {
                input = input.trim();
                input = input.substring(1, input.length() - 1);
                if (input.length() == 0) {
                    return null;
                }

                String[] parts = input.split(",");
                String item = parts[0];
                TreeNode root = new TreeNode(Integer.parseInt(item));
                Queue<TreeNode> nodeQueue = new LinkedList<>();
                nodeQueue.add(root);

                int index = 1;
                while(!nodeQueue.isEmpty()) {
                    TreeNode node = nodeQueue.remove();

                    if (index == parts.length) {
                        break;
                    }

                    item = parts[index++];
                    item = item.trim();
                    if (!item.equals("null")) {
                        int leftNumber = Integer.parseInt(item);
                        node.left = new TreeNode(leftNumber);
                        nodeQueue.add(node.left);
                    }

                    if (index == parts.length) {
                        break;
                    }

                    item = parts[index++];
                    item = item.trim();
                    if (!item.equals("null")) {
                        int rightNumber = Integer.parseInt(item);
                        node.right = new TreeNode(rightNumber);
                        nodeQueue.add(node.right);
                    }
                }
                return root;
            }

            public static void main(String[] args) throws IOException {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = in.readLine()) != null) {
                    TreeNode root = stringToTreeNode(line);

                    int ret = longestUnivaluePath(root);

                    String out = String.valueOf(ret);

                    System.out.print(out);
                }
            }
        }
    }
}
