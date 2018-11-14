package FacadePattern;

/**
 * 基金类，代理投资股票、保险、期货业务
 * Created by houjue on 2018/11/12.
 */
public class Funds {
    private Stock stock;
    private Insurance insurance;
    private Futures futures;

    public Funds() {
        stock = new Stock();
        insurance = new Insurance();
        futures = new Futures();
    }

    // 基金购买
    public void buyFunds() {
        stock.buy();
        insurance.buy();
        futures.buy();
    }
    // 基金卖出
    public void sellFunds() {
        stock.sell();
        insurance.sell();
        futures.sell();
    }
}
