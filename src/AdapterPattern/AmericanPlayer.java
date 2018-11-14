package AdapterPattern;

/**
 * Target类
 * Created by houjue on 2018/11/14.
 */
public abstract class AmericanPlayer {
    private String name;

    public AmericanPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void attack();
}
