package MementoPattern;

/**
 * 备忘录模式：
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。
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
