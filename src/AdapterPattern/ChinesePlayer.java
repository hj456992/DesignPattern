package AdapterPattern;

/**
 * Created by houjue on 2018/11/14.
 */
public class ChinesePlayer {
    private String name;

    public ChinesePlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(this.name +" 进攻");
    }
}
