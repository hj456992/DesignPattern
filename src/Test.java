import java.util.*;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author 侯珏
 * @create 2018-11-22 0:30
 */
public class Test {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

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
     // 广度优先搜索实现二叉树层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 此处为树，不需要以访问的节点的集合
        // Set<TreeNode> visited = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();
        // 一次循环即一层的节点数
        while (!queue.isEmpty()) {
           int curLevel = queue.size();
           List<Integer> curList = new ArrayList<>();
           for (int i = 0;i < curLevel; i ++) {
               TreeNode node = queue.poll();
               curList.add(node.val);
               if (node.left != null) {
                   queue.offer(node.left);
               }
               if (node.right != null) {
                   queue.offer(node.right);
               }
           }
           result.add(curList);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "123";
        System.out.println(s.substring(s.length() - 1));
    }
}
