package StatePattern;

/**
 * 具体状态类，晚饭时间
 * Created by houjue on 2018/11/14.
 */
public class DinnerTimeState extends State {
    @Override
    void writeProgram(Work work) {
        // 工作完成，转入下班状态
        if (work.getFinished()) {
            work.setState(new RestStatus());
            work.writeProgam();
        } else {
            if (work.getHour() < 19) {
                System.out.println("晚饭时间想BUG，想不出方案好生气");
            } else {
                work.setState(new EveningState());
                work.writeProgam();
            }
        }
    }
}
