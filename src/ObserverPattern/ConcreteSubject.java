package ObserverPattern;

/**
 * 具体主题类
 * Created by houjue on 2018/11/12.
 */
public class ConcreteSubject extends Subject {
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}
