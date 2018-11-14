package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题类
 * Created by houjue on 2018/11/12.
 */
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    // 添加观察者
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // 移除观察者
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    // 通知观察者更新
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
