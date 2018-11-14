package ProxyPattern;

/**
 * 火车票代理类，代理了票务中心的服务
 * Created by houjue on 2018/11/9.
 */
public class RailwayTicketProxy implements TicketService {
    private RailwayTicketCenter ticketCenter;

    public RailwayTicketProxy(Consumer consumer) {
        this.ticketCenter = new RailwayTicketCenter(consumer);
    }

    @Override
    public void sellOneTicket() {
        ticketCenter.sellOneTicket();
    }

    @Override
    public void sellAllTickets() {
        ticketCenter.sellAllTickets();
    }
}
