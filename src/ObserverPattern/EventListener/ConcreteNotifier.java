package ObserverPattern.EventListener;

/**
 * Created by houjue on 2018/11/13.
 */
public class ConcreteNotifier extends Notifier {
    @Override
    public void addListener(Object object, String methodName, Object... args) {
        this.getHandler().addEvent(object, methodName, args);
    }

    @Override
    public void notifyX() throws Exception {
        this.getHandler().invoke();
    }
}
