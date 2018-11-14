package ObserverPattern.EventListener;

import java.util.Date;

/**
 * Created by houjue on 2018/11/13.
 */
public class PlayGameListener {
    public void playingGame() {
        System.out.println("老师没来，兴致勃勃玩游戏");
    }

    public void stopPlayingGame(Date date) {
        System.out.println("老师来了，停止玩游戏，游戏进度条时间：" + date);
    }
}
