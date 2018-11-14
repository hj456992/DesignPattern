package CompositePattern;

/**
 * Created by houjue on 2018/11/14.
 */
public abstract class Component {
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void add(Component component);
    abstract void remove(Component component);
    abstract void display();
}
