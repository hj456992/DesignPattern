package StatePattern;

/**
 * 具体状态类，深夜
 * Created by houjue on 2018/11/14.
 */
public class EveningState extends State{
    @Override
    void writeProgram(Work work) {
        if (work.getHour() < 21) {
            System.out.println("晚上加班改BUG，越改越是来脾气");
        } else {
            System.out.println("今晚又要加通宵，想想代码好无力");
        }
    }
}
