package StatePattern;

/**
 * 具体状态类，下班状态
 * Created by houjue on 2018/11/14.
 */
public class RestStatus extends State {
    @Override
    void writeProgram(Work work) {
        if (work.getHour() < 22) {
            System.out.println("play PS4！");
        } else {
            System.out.println("睡觉");
        }
    }
}
