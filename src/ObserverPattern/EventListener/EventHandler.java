package ObserverPattern.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houjue on 2018/11/13.
 */
public class EventHandler {
    private List<Event> events;

    public EventHandler() {
        this.events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Object object, String methodName, Object... args) {
        this.events.add(new Event(object, methodName, args));
    }

    public void invoke() throws Exception {
        for (Event event : this.events) {
            event.invoke();
        }
    }
}
