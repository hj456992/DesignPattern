package AdapterPattern;

/**
 * Created by houjue on 2018/11/14.
 */
public class Front extends AmericanPlayer {

    public Front(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(this.getName() +" attack");
    }
}
