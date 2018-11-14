package CompositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houjue on 2018/11/14.
 */
public class Composite extends Component {
    private List<Component> components = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    void add(Component component) {
        components.add(component);
    }

    @Override
    void remove(Component component) {
        components.remove(component);
    }

    @Override
    void display() {
        System.out.println(this.getName());
        printNode(this.components, null);
    }

    private void printNode(List<Component> components, String op) {
        if (components.size() == 0) {
            return;
        }
        if (op == null) {
            op = "-";
        } else {
            op += "-";
        }
        for (Component component : components) {
            System.out.println(op + component.getName());
            if (component instanceof  Composite) {
                Composite composite = (Composite) component;
                printNode(composite.getComponents(), op);
            }
        }
    }
}
