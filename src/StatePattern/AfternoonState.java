package StatePattern;

/**
 * 具体状态类，下午
 * Created by houjue on 2018/11/14.
 */
public class AfternoonState extends State {
    @Override
    void writeProgram(Work work) {
        if (work.getHour() < 17) {
            System.out.println("下午虽然有点困，但写代码不费劲");
        } else {
            work.setState(new DinnerTimeState());
            work.writeProgam();
        }
    }
}
