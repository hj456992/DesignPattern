package Algorithm;

import java.util.*;

/**
 * 描述:
 * 深度优先搜索和广度优先搜索题目练习
 *
 * @author 侯珏
 * @create 2018-12-01 16:04
 */
public class DFSAndBFSTest {

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    private static class Solution1 {
        private class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
        }

        // 广度优先实现
        public List<List<Integer>> levelOrder1(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // 此题结构为树，不需要使用visited访问集合

            List<List<Integer>> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                List<Integer> currentLevel = new ArrayList<>();
                // 当前队列长度，即为当前层的元素个数
                int size = queue.size();

                // 循环遍历元素，将结果加入结果集，并将节点的子节点加入队列
                for (int i = 0; i < size; i ++) {
                    TreeNode node = queue.poll();
                    currentLevel.add(node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                result.add(currentLevel);
            }
            return result;
        }

        /**
         * 深度优先模板
         * // node：当前节点，visited：已访问的节点set
         * void dfs(node, visited) {
         *     visited.add(node);
         *     // 处理当前节点逻辑
         *     ...
         *
         *     // 对当前节点的孩子节点遍历，并对孩子节点深度遍历
         *     for (Node childNode : node.children) {
         *         if (!visited.contain(childNode)) {
         *             dfs(childNode, visited);
         *         }
         *     }
         * }
         */
        // 深度优先实现
        public List<List<Integer>> levelOrder2(TreeNode root) {
            // 递归的层级
            int level = 0;
            return dfs(root, level, new ArrayList<>());
        }

        private List<List<Integer>> dfs(TreeNode root, int level, List<List<Integer>> result) {
            // 递归终止条件，当前节点为空
            if (root == null) {
                return new ArrayList<>();
            }
            // 如果当前层级没有结果，则新增一个list
            List<Integer> curList;
            if (result.size() == level) {
                curList = new ArrayList<>();
                curList.add(root.val);
                result.add(curList);
            } else {
                curList = result.get(level);
                curList.add(root.val);
                result.set(level, curList);
            }

            dfs(root.left, level + 1, result);
            dfs(root.right, level + 1, result);

            return result;
        }
    }

    /**
     * 二叉树的最大深度
     *
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
    static class Solution2 {
        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        // 广度优先算法实现
        public int maxDepth1(TreeNode root) {
            // 第一步，验参
            if (root == null) {
                return 0;
            }
            // 第二步，定义队列和访问集合
            LinkedList<TreeNode> queue = new LinkedList<>();
            Set<TreeNode> visited = new HashSet<>();// 此题不需要
            // 第三步，初始化队列
            queue.offer(root);

            int max = 0;
            while (!queue.isEmpty()) {
                max ++;
                int size = queue.size();

                for (int i = 0; i < size; i ++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            return max;
        }

        // 深度优先算法实现
        public int maxDepth(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode root, int level) {
            if (root == null) {
                return level;
            }

            // 记录左子树和右子树的深度
            int leftLevel = dfs(root.left, level + 1);
            int rightLevel = dfs(root.right, level + 1);

            return Math.max(leftLevel, rightLevel);
        }
    }

    /**
     * 括号生成
     *
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     */
    static class Solution3 {
        /**
         * 解题思路：
         * 本题可转化为根节点为(，每条链路最多有n个(节点的二叉树，通过遍历，找到所有的子节点和叶子节点，每一条链路，即为一个解
         */
        static class Node {
            Integer leftCount;
            Integer rightCount;
            String val;

            Node(Integer leftCount, Integer rightCount, String val) {
                this.leftCount = leftCount;
                this.rightCount = rightCount;
                this.val = val;
            }
        }
        // 广度优先搜索
        private static List<String> generateParenthesis(int n) {
            if (n == 0) {
                return new ArrayList<>();
            }

            // 队列中的元素为当前链路的字符串
            LinkedList<Node> queue = new LinkedList<>();
            queue.offer(new Node(1, 0, "("));

            List<String> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i ++) {
                    Node curNode = queue.poll();
                    int leftCount = curNode.leftCount;
                    int rightCount = curNode.rightCount;
                    if (leftCount == n && rightCount == n) {
                        result.add(curNode.val);
                    }
                    if (leftCount < n) {
                        queue.offer(new Node(leftCount + 1, rightCount, curNode.val + "("));
                    }
                    if (rightCount < leftCount) {
                        queue.offer(new Node(leftCount, rightCount + 1, curNode.val + ")"));
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            List<String> result = generateParenthesis(3);
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}
