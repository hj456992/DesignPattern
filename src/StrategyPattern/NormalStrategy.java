package StrategyPattern;

/**
 * 策略模式子类，定义正常售价的商品
 * Created by houjue on 2018/11/6.
 */
public class NormalStrategy extends SuperStrategy {
    public NormalStrategy() {
    }

    @Override
    public double getPrice(double price) {
        return price;
    }
}
