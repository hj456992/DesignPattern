package BridgePattern;

/**
 * 手机品牌A
 * Created by houjue on 2018/11/15.
 */
public class PhoneA extends PhoneBrand {

    @Override
    void run() {
        this.getSoft().run();
    }
}
