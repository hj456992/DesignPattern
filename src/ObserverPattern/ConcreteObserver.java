package ObserverPattern;

/**
 * 具体观察者类
 * Created by houjue on 2018/11/12.
 */
public class ConcreteObserver extends Observer {
    private String name;
    private String subjectState;
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject, String name) {
        this.subject = subject;
        this.name = name;
    }

    @Override
    public void update() {
        subjectState = subject.getSubjectState();
        System.out.println("观察者"+ name+ "的新状态是"+ subjectState);
    }
}
