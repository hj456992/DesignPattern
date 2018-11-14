package StatePattern;

/**
 * 具体状态类，上午
 * Created by houjue on 2018/11/14.
 */
public class MorningState extends State {
    @Override
    void writeProgram(Work work) {
        if (work.getHour() < 12) {
            System.out.println("每天早晨撸个类，代码充满着秀气");
        } else {
            work.setState(new NoonState());
            work.writeProgam();
        }
    }
}
