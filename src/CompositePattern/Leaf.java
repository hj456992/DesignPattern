package CompositePattern;

/**
 * Created by houjue on 2018/11/14.
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    void add(Component component) {

    }

    @Override
    void remove(Component component) {

    }

    @Override
    void display() {
        System.out.println("该叶子节点名为"+this.getName());
    }
}
