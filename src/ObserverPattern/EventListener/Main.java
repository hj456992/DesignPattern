package ObserverPattern.EventListener;

import java.util.Date;

/**
 * 观察者模式加强：事件委托机制
 * 委托就是一种引用方法的类型。一旦为委托分配了方法，委托将与该方法具有完全相同的行为。
 * 委托方法的使用可以像其他任何方法一样，具有参数和返回值。
 * 委托可以看作是对函数的抽象，是函数的‘类’，委托的实例将代表一个具体的函数。
 * 如图所示，委托类可视为event
 *
 * 优点：
 * 一个委托可以搭载多个方法，所有方法被依次唤起。更重要的是，它可以使得委托对象所搭载的方法并不需要属于同一个类。
 * 局限性：
 * 但委托也是有前提的，那就是委托对象所搭载的所有方法必须具有相同的原形和形式，也就是拥有相同的参数列表和返回值类型。
 * 观察者和通知者解开耦合了，而且，如果我想要想要观察者做出不同的态度，只需要在客户端进行不同的Method的方法就可以了。
 *
 * 测试场景：
 * 一个班级，有两类学生，A类：不学习，玩，但是玩的东西不一样，有的是做游戏，有的是看电视，
 * B类：放哨的学生，专门看老师的动向，如果老师进班了就立即通知大家。
 * 如此就形成了一个需求，放哨的学生要通知所有玩的学生：老师来了，而不同的学生有不同的反应，有的马上把电视关闭，有的停止玩游戏。
 * 设计的要求如下，让A类学生和B类学生完全解耦，即A类完全不知道B类的学生，却可以通知B类的学生。
 * 来源：
 * https://blog.csdn.net/qiumuxia0921/article/details/52067604
 * 作者：邱慕夏
 *
 * Created by houjue on 2018/11/12.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 创建放哨学生A
        ConcreteNotifier studentA = new ConcreteNotifier();

        // 创建玩游戏的学生B
        PlayGameListener studentB = new PlayGameListener();
        // B玩游戏
        studentB.playingGame();
        // 创建看电视的学生C
        WatchTVListener studentC = new WatchTVListener();
        // C看电视
        studentC.watchingTV();

        // B和C委托A放哨
        studentA.addListener(studentB, "stopPlayingGame", new Date());
        studentA.addListener(studentC, "stopWatchingTV", new Date());

        try {
           Thread.sleep(10000);
        } catch (Exception e) {

        }
        // 10秒后，老师来了，A通知所有人
        studentA.notifyX();

    }
}
