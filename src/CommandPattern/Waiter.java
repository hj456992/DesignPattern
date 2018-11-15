package CommandPattern;

/**
 * 服务员类，发起命令方
 * Created by houjue on 2018/11/15.
 */
public class Waiter {

    private Order order;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void executeCommond() {
        order.execute();
    }
}
