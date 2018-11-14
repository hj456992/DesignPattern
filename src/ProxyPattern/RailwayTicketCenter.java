package ProxyPattern;

/**
 * 火车票务中心类，实现了票务服务，对应图中的RealSubject
 * Created by houjue on 2018/11/9.
 */
public class RailwayTicketCenter implements TicketService {
    private Consumer consumer;

    public RailwayTicketCenter(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void sellOneTicket() {
        System.out.println("卖"+consumer.getName() + "一张火车票");
    }

    @Override
    public void sellAllTickets() {
        System.out.println("卖"+consumer.getName() + "全部火车票");
    }
}
