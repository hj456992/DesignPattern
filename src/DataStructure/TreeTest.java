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

    /**
     * 二叉树最小公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *         _______3______
     *        /              \
     *     ___5__          ___1__
     *    /      \        /      \
     *    6      _2       0       8
     *          /  \
     *          7   4
     * 示例 1:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     */
    static class Solution2 {
        private class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
        }

        /**
         * (要背)
         * 思路，通过递归的方式，找p和q的位置，
         * 1、如果p或q为当前子树的root节点，返回root
         * 2、如果当前递归的root节点的左子树和右子树都有值，即p和q分别分散在左右子树，则root节点为其根节点
         * 3、如果当前递归的root节点有一棵子树没有值，即说明p和q在另一棵子树上，则返回的root即为最小公共祖先
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return root;
            }
            if (root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // 关键步骤，收缩到最小公共子树的时候，即为此种情形
            if (left != null && right != null) {
                return root;
            }

            if (left == null && right != null) {
                return right;
            }

            return left;
        }
    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     *         _______6______
     *        /              \
     *     ___2__          ___8__
     *    /      \        /      \
     *    0      _4       7       9
     *          /  \
     *          3   5
     * 示例 1:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     */
    static class Solution3 {
        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 思路：与二叉树最小公共祖先的思路类似，不过可以利用二叉搜索树的性质，左子树的节点小于其根节点，右子树的节点大于其根节点的特性来做优化
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return root;
            }
            // p、q均小于root节点，只遍历左子树
            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestor(root.left, p, q);
            }
            // p、q均大于root节点，只遍历右子树
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestor(root.right, p, q);
            }

            return root;
        }
    }
}
