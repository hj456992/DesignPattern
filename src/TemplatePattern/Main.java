package TemplatePattern;

/**
 * 模板方法模式：
 * 定义一个操作中的算法的骨架，而将一些操作步骤延迟到其子类中。模板方法使得子类不改变一个算法的结构而能重定义该算法的某些特定步骤。
 *
 * 优点：
 * 模板方法模式是通过把不变行为搬移到超类，去除子类中的重复代码来体现它的优势。
 *
 * 使用场景：
 * 当不变的和可变的行为在方法的子类实现中混合在一起的时候，不变的行为就会在子类中重复出现。
 * 我们通过模板方法模式把这些行为搬移到单一的地方，这样就帮助子类摆脱重复的不变行为的纠缠。
 *
 * JDK中实现：
 * 参考concurrent包中的AQS与锁机制
 *
 * 场景：
 * 学生做试卷，不同学生做同一套试题（模板），但是答案（具体步骤）不同
 * Created by houjue on 2018/11/11.
 */
public class Main {
    public static void main(String[] args) {
        TestPaper studentA = new StudentA();
        TestPaper studentB = new StudentB();

        studentA.testQuestion1();
        studentB.testQuestion1();
    }
}
