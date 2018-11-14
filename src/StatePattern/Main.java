package StatePattern;

/**
 * 状态模式：
 * 当一个对象的状态改变时允许改变其行为，这个对象看起来像是改变了其类。
 * 何时使用：
 *   状态模式主要解决的是当控制一个对象状态转换的条件表达式过于复杂时的情况。
 * 把状态的判断逻辑转移到表示不同状态的一系列类当中，可以把复杂的判断逻辑简化。
 * 当然，如果这个状态判断很简单，那就没必要用‘状态模式’了。
 *   当一个对象的行为取决于它的状态，并且它必须在运行时刻根据状态改变它的行为时，就可以考虑使用状态模式了。
 *
 * 测试场景：
 * 根据工作时间（status）和工作完成度来判断当前工作状态
 * 1、12点之前，工作状态精神饱满（ConcreteStatusA）
 * 2、下午2点之前，没有工作状态（ConcreteStatusB）
 * 3、下午5点之前，工作状态很好（ConcreteStatusC）
 * 4、晚上7点之前，工作状态低迷（ConcreteStatusD）
 * 5、晚上9点之前，工作状态烦躁且低迷（ConcreteStatusE）
 * 6、晚上9点之后，昏昏欲睡（ConcreteStatusE）
 *
 * Created by houjue on 2018/11/13.
 */
public class Main {
    public static void main(String[] args) {
        // 开启一天的工作
        Work work = new Work(new MorningState());
        work.setFinished(Boolean.FALSE);
        // 早上的状态
        work.setHour(11);
        work.writeProgam();
        // 中午的状态
        work.setHour(13);
        work.writeProgam();
        // 下午的状态
        work.setHour(15);
        work.writeProgam();
        // 工作完成
        work.setFinished(Boolean.FALSE);
        // 傍晚的状态
        work.setHour(18);
        work.writeProgam();
        // 晚上的状态
        work.setHour(20);
        work.writeProgam();
        // 深夜的状态
        work.setHour(23);
        work.writeProgam();
    }
}
