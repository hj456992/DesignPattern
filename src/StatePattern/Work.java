package StatePattern;

/**
 * 工作类
 * Created by houjue on 2018/11/14.
 */
public class Work {
    private int hour;
    private State state;
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Work(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void writeProgam() {
        this.state.writeProgram(this);
    }
}
