package BuilderPattern;

/**
 * 建造者模式：
 * 将一个复杂对象的构建和其表示分离，使得同样的构建过程可以创建不同的表示。
 * 如果我们用了建造者模式，那么用户就只需指定需要建造的类型就可以得到它们，而具体建造的过程和细节就不需知道了。
 *
 * 何时使用建造者模式：
 * 1、主要是用于创建一些复杂的对象，这些对象内部构建间的建造顺序通常是稳定的，但对象内部的构建通常面临着复杂的变化。
 * 2、建造者模式的好处就是使得建造代码与表示代码分离，由于建造者隐藏了该产品是如何组装的，
 * 所以若需要改变一个产品的内部表示，只需要再定义一个具体的建造者就可以了。
 *
 * 测试场景：
 * 暂无，如图中构建抽象类描述建造者模式（基本代码实现）。
 * Created by houjue on 2018/11/12.
 */
public class Main {
    public static void main(String[] args) {
        // 创建指挥者
        Director director = new Director();
        // 创建具体的产品构造方案
        ConcreteBuilderA builderA = new ConcreteBuilderA();
        // 建造产品
        director.construct(builderA);

        // 创建具体的产品构造方案
        ConcreteBuilderB builderB = new ConcreteBuilderB();
        // 建造产品
        director.construct(builderB);

        // 输出产品
        Product p1 = builderA.getResult();
        p1.show();
        // 输出产品
        Product p2 = builderB.getResult();
        p2.show();
    }
}
