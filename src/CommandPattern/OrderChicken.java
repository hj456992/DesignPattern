package CommandPattern;

/**
 * 具体的命令类
 * Created by houjue on 2018/11/15.
 */
public class OrderChicken implements Order {
    private Kitchen kitchen;

    public OrderChicken(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void execute() {
        kitchen.cookChicken();
    }
}
