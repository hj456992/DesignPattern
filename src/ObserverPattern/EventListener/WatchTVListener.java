package ObserverPattern.EventListener;

import java.util.Date;

/**
 * Created by houjue on 2018/11/13.
 */
public class WatchTVListener {
    public void watchingTV() {
        System.out.println("老师没来，兴致勃勃看电视");
    }

    public void stopWatchingTV(Date date) {
        System.out.println("老师来了，停止看电视，电视进度条时间：" + date);
    }
}
