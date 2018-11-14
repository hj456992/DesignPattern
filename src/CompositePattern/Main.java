package CompositePattern;

/**
 * 组合模式：
 * 将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式用户对单个对象和组合对象的使用具有一致性。
 *
 * Created by houjue on 2018/11/14.
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个根节点
        Component root = new Composite("根");
        // 添加一个左子树
        Component leftChildTree = new Composite("左子树");
        // 左子树添加一个左叶子节点
        leftChildTree.add(new Leaf("左树左叶子"));
        // 左子树添加一个右叶子节点
        leftChildTree.add(new Leaf("左树右叶子"));
        root.add(leftChildTree);
        root.display();
        System.out.println();
        // 添加一个右子树
        Component rightChildTree = new Composite("右子树");
        root.add(rightChildTree);
        root.display();
        System.out.println();
        // 右子树添加一个左叶子节点
        rightChildTree.add(new Leaf("右树左叶子"));
        // 右子树添加一个右叶子节点
        rightChildTree.add(new Leaf("右树右叶子"));
        root.display();
        System.out.println();
        // 删除左子树
        root.remove(leftChildTree);
        root.display();

    }
}
