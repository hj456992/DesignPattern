package ObserverPattern;

import java.beans.EventHandler;

/**
 * (不熟练，要多练)
 * 观察者模式：
 *   观察者模式又叫做发布-订阅模式。
 *   观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 当主题对象状态发生变化时，通知所有的观察者，使他们能够自动更新自己。
 *
 * 观察模式优点：
 *   将一个系统分割成一系列相互协作的类有一个很不好的副作用，那就是需要维护相关对象间的一致性。
 * 我们不希望为了维持一致性而使各类紧密耦合，这样会给维护、扩展和重用都带来不便[DP]。
 * 而观察者模式的关键对象是主题Subject和观察者Observer，一个Subject可以有任意数目的依赖它的Observer，
 * 一旦Subject的状态发生了改变，所有的Observer都可以得到通知。Subject发出通知时并不需要知道谁是它的观察者，
 * 也就是说，具体观察者是谁，它根本不需要知道。而任何一个具体观察者不知道也不需要知道其他观察者的存在。
 *
 * 观察者模式所做的工作其实就是在解除耦合。让耦合的双方都依赖于抽象，而不是依赖于具体。从而使得各自的变化都不会影响另一边的变化。
 *
 * 观察模式不足：
 * 尽管已经用了依赖倒转原则，但是‘抽象通知者’还是依赖‘抽象观察者’，也就是说，万一没有了抽象观察者这样的接口，我这通知的功能就完不成了。
 * 另外就是每个具体观察者，它不一定是‘更新’的方法要调用
 *
 * 接口和抽象类的选择：
 * 现实编程中，具体的观察者完全有可能是风马牛不相及的类，但它们都需要根据通知者的通知来做出Update（）的操作，
 * 所以让它们都实现下面这样的一个接口就可以实现这个想法了。
 *
 * JDK源码实现：
 * 在JDK源码下的java.util.Observable和java.util.Observer，分别代表观察者模式中的Subject和Observer。
 *
 * 测试场景：
 * 与示例图中类似。
 * Created by houjue on 2018/11/12.
 */
public class Main {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer = new ConcreteObserver(subject, "X");
        Observer observer1 = new ConcreteObserver(subject, "Y");
        Observer observer2 = new ConcreteObserver(subject, "Z");

        subject.attach(observer);
        subject.attach(observer1);
        subject.attach(observer2);

        subject.setSubjectState("ABC");
        subject.notifyObserver();
    }
}
