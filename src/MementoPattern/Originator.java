package MementoPattern;

/**
 * 发起人
 * Created by houjue on 2018/11/14.
 */
public class Originator {
    // 需要保存的属性
    private String state;
    // 不需要保存的属性
    private String name;

    public Originator(String state, String name) {
        this.state = state;
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Memento createMemento() {
        return new Memento(this.state);
    }

    public void setMemento(Memento memento) {
        this.state = memento.getState();
    }

    public void show() {
        System.out.println(this.name + " " + this.state);
    }
}
