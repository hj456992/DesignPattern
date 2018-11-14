package MementoPattern;

/**
 * 备忘录模式：
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。
 *
 * 何时使用：
 *   Memento模式比较适用于功能比较复杂的，但需要维护或记录属性历史的类，或者需要保存的属性只是众多属性中的一小部分时，
 * Originator可以根据保存的Memento信息还原到前一状态。
 *   如果在某个系统中使用命令模式时，需要实现命令的撤销功能，那么命令模式可以使用备忘录模式来存储可撤销操作的状态[DP]。
 *   有时一些对象的内部信息必须保存在对象以外的地方，但是必须要由对象自己读取，
 * 这时，使用备忘录可以把复杂的对象内部信息对其他的对象屏蔽起来[DP]，从而可以恰当地保持封装的边界。
 *   最大的作用还是在当角色的状态改变的时候，有可能这个状态无效，这时候就可以使用暂时存储起来的备忘录将状态复原[DP]。
 *
 * Created by houjue on 2018/11/14.
 */
public class Main {

    public static void main(String[] args) {
        Originator originator = new Originator("start game", "hj");
        originator.show();

        // 备份
        Memento memento = originator.createMemento();
        CareTaker careTaker = new CareTaker(memento);

        // 改变状态
        originator.setState("game over");
        originator.show();

        // 恢复
        originator.setMemento(careTaker.getMemento());
        originator.show();
    }
}
