package SingletonPattern;

/**
 * 静态内部类
 * Created by houjue on 2018/11/15.
 */
public class SingletonB {

    private static class SingletonHolder {
        private static final SingletonB INSTANCE = new SingletonB();
    }

    private SingletonB() {
    }

    public static final SingletonB getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
