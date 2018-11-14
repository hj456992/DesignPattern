package StrategyPattern;

/**
 * 策略模式子类，定义打折商品
 * Created by houjue on 2018/11/6.
 */
public class DiscountStrategy extends SuperStrategy {
    private Double discount;

    public DiscountStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double getPrice(double price) {
        if (null == discount) {
            return price;
        }
        return this.discount * price;
    }
}
