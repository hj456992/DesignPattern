package MementoPattern;

/**
 * 管理者，负责保存好备忘录Memento
 * Created by houjue on 2018/11/14.
 */
public class CareTaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public CareTaker(Memento memento) {
        this.memento = memento;
    }
}
