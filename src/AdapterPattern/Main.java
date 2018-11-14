package AdapterPattern;

/**
 * 适配器模式：
 * 将一个类的接口转换成客户希望的另外一个的接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 *
 * 何时使用：
 * 系统的数据和行为都正确，但接口不符时，我们应该考虑用适配器，目的是使控制范围之外的一个原有对象与某个接口匹配。
 * 适配器模式主要应用于希望复用一些现存的类，但是接口又与复用环境要求不一致的情况。
 * 在双方都不太容易修改的时候再使用适配器模式适配。
 * 设计一系统时考虑使用第三方开发组件，而这个组件的接口与我们自己的系统接口是不相同的，而我们也完全没有必要为了迎合它而改动自己的接口，
 * 此时尽管是在开发的设计阶段，也是可以考虑用适配器模式来解决接口不同的问题。
 *
 * 两种类型：
 * 类适配器模式（需要多重继承，java不适用）和对象适配器模式
 *
 * 测试场景：
 * A（Target）来到B（Adaptee）的国家打球，A发出进攻信号，由于语言不通，找来翻译C（Adapter），C把A的进攻翻译后发出
 * Created by houjue on 2018/11/14.
 */
public class Main {
    public static void main(String[] args) {
        AmericanPlayer front = new Front("James");
        front.attack();

        AmericanPlayer ym = new Translator("姚明");
        ym.attack();
    }
}
