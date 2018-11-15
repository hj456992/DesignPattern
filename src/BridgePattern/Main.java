package BridgePattern;

/**
 * 桥接模式：
 * 将抽象部分与它的实现部分相分离，使他们都可以独立的变化。
 * 什么叫抽象与它的实现分离，这并不是说，让抽象类与其派生类分离，因为这没有任何意义。实现指的是抽象类和它的派生类用来实现自己的对象[DPE]。
 * 就刚才的例子而言，就是让‘手机’既可以按照品牌来分类，也可以按照功能来分类。
 *
 * 通俗理解(摘自《大话》)：
 * 实现系统可能有多角度分类，每一种分类都有可能变化，那么就把这种多角度分离出来让它们独立变化，减少它们之间的耦合。
 *
 * 测试场景：
 * 参考该package下的三个图
 * 注：此模式较难理解，故把大话设计模式中的参考图全部拷贝过来。
 * Created by houjue on 2018/11/15.
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个A品牌手机
        PhoneBrand phoneA = new PhoneA();
        // 创建一个通讯录软件
        PhoneSoft contact = new Contact();
        // A安装通讯录
        phoneA.setSoft(contact);
        // A运行通讯录
        phoneA.run();

        // 创建一个B品牌手机
        PhoneBrand phoneB = new PhoneB();
        // 创建一个游戏软件
        PhoneSoft game = new Game();
        // B安装游戏
        phoneB.setSoft(game);
        // B运行游戏
        phoneB.run();
    }
}
