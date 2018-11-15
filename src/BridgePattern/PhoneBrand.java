package BridgePattern;

/**
 * 手机品牌类
 * Created by houjue on 2018/11/15.
 */
public abstract class PhoneBrand {
    private PhoneSoft soft;

    // 设置软件
    public void setSoft(PhoneSoft soft) {
        this.soft = soft;
    }

    public PhoneSoft getSoft() {
        return soft;
    }

    // 运行
    abstract void run();
}
