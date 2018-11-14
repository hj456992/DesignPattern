package ObserverPattern.EventListener;

/**
 * Created by houjue on 2018/11/13.
 */
public abstract class Notifier {
    private EventHandler handler;

    public Notifier() {
        this.handler = new EventHandler();
    }

    public EventHandler getHandler() {
        return handler;
    }

    public void setHandler(EventHandler handler) {
        this.handler = handler;
    }

    public abstract void addListener(Object object, String methodName, Object ...args);

    public abstract void notifyX() throws Exception;
}
