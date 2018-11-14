package StrategyPattern;

/**
 * 策略模式子类，定义满减商品
 * Created by houjue on 2018/11/6.
 */
public class ReturnStrategy extends SuperStrategy {
    private Double priceCondition;
    private Double priceReturn;

    public ReturnStrategy(Double priceCondition, Double priceReturn) {
        this.priceCondition = priceCondition;
        this.priceReturn = priceReturn;
    }

    @Override
    public double getPrice(double price) {
        // 没有满减活动
        if (null == priceCondition || null == priceReturn) {
            return price;
        }
        // 价格小于满减活动条件
        if (price < priceCondition) {
            return price;
        }
        // 符合满减活动
        price = price - priceReturn;
        return price;
    }
}
