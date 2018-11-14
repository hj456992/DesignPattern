package AdapterPattern;

/**
 * Created by houjue on 2018/11/14.
 */
public class Translator extends AmericanPlayer {

    private ChinesePlayer chinesePlayer;

    public Translator(String name) {
        super(name);
        this.chinesePlayer = new ChinesePlayer(name);
    }

    @Override
    void attack() {
        chinesePlayer.attack();
    }
}
