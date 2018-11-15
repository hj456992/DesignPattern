package CommandPattern;

/**
 * 命令模式：
 * 将一个请求封装为对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤销的操作。
 *
 * 优点：
 * 第一，它能较容易地设计一个命令队列；第二，在需要的情况下，可以较容易地将命令记入日志；第三，允许接收请求的一方决定是否要否决请求。
 * 第四，可以容易地实现对请求的撤销和重做；第五，由于加进新的具体命令类不影响其他的类，因此增加新的具体命令类很容易。
 * 其实还有最关键的优点就是命令模式把请求一个操作的对象与知道怎么执行一个操作的对象分割开。[DP]
 *
 * 测试场景：
 * 客人去饭店吃饭，向服务员点餐，服务员将客户的点餐请求传给后厨去处理
 * 客人，代表客户端，负责发起请求
 * 服务员，执行已经预先加载好的命令
 * 点餐，为抽象的命令
 * 点一盘鸡肉，为具体的命令
 * 后厨，接受命令，执行操作
 * Created by houjue on 2018/11/15.
 */
public class Main {

    public static void main(String[] args) {
        // 开业前准备
        Kitchen kitchen = new Kitchen();
        Order order1 = new OrderChicken(kitchen);
        Waiter waiter = new Waiter();

        // 顾客点餐
        waiter.setOrder(order1);
        waiter.executeCommond();
    }
}
