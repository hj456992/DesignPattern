package StatePattern;

/**
 * 具体状态类，中午
 * Created by houjue on 2018/11/14.
 */
public class NoonState extends State{

    @Override
    void writeProgram(Work work) {
        if (work.getHour() < 14) {
            System.out.println("每天中午睡个觉，下午工作更神气");
        } else {
            work.setState(new AfternoonState());
            work.writeProgam();
        }
    }
}
