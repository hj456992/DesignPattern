package MementoPattern;

/**
 * 备忘录，负责存储Originator的内部状态
 * Created by houjue on 2018/11/14.
 */
public class Memento {
    // 保存的Originator的副本状态
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
