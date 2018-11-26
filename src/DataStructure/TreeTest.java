package DataStructure;

/**
 * 描述:
 * 树题目练习
 *
 * @author 侯珏
 * @create 2018-11-26 19:30
 */
public class TreeTest {

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     */
    static class Solution1 {
        static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
        }

        /**
         * 思路，通过递归的方法，查找左子树的最大值和右子树的最小值，只要左子树最大值大于根节点，或者右子树最小值小于根节点，
         * 则其不为二叉树
         *
         * @param root
         * @return
         */
        public static boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }

        /**
         * 递归函数
         * @param root 当前节点
         * @param min 当前根节点的下界，对右子树而言，下届为其父节点的值
         * @param max 当前根节点的上界，对左子树而言，上届为其父节点的值
         * @return
         */
        private static boolean helper(TreeNode root, Integer min, Integer max) {
            if (root == null) {
                return true;
            }
            // 右子树最小值必须大于父节点的值
            if (min != null && min >= root.val) {
                return false;
            }
            // 左子树最大值必须小于父节点的值
            if (max != null && max <= root.val) {
                return false;
            }
            // 递归左右子树，直到没有子节点
            return helper(root.left, min, root.val) && helper(root.right, root.val, max);
        }

        public static void main(String[] args) {
            TreeNode treeNode = new TreeNode(2);
            TreeNode left = new TreeNode(1);
            TreeNode right = new TreeNode(3);
            treeNode.left = left;
            treeNode.right = right;
            System.out.println(isValidBST(treeNode));
        }
    }
}
