package CompositePattern;

/**
 * 组合模式：
 * 将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式用户对单个对象和组合对象的使用具有一致性。
 *
 * 透明方式：
 * 在Component中声明所有用来管理子对象的方法，其中包括Add、Remove等。这样实现Component接口的所有子类都具备了Add和Remove。
 * 这样做的好处就是叶节点和枝节点对于外界没有区别，它们具备完全一致的行为接口。但问题也很明显，
 * 因为Leaf类本身不具备Add（）、Remove（）方法的功能，所以实现它是没有意义的。
 *
 * 安全方式：
 * 在Component接口中不去声明Add和Remove方法，那么子类的Leaf也就不需要去实现它，而是在Composite声明所有用来管理子类对象的方法，
 * 这样做就不会出现刚才提到的问题，不过由于不够透明，所以树叶和树枝类将不具有相同的接口，客户端的调用需要做相应的判断，带来了不便。
 *
 * 何时使用：
 * 当你发现需求中是体现部分与整体层次的结构时，以及你希望用户可以忽略组合对象与单个对象的不同，统一地使用组合结构中的所有对象时，
 * 就应该考虑用组合模式了。
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
