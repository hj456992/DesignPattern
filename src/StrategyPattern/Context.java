package StrategyPattern;

/**
 * 聚合不同策略的上下文文本类，由传入的策略类型决定何种策略，简单工厂模式的实现
 * Created by houjue on 2018/11/6.
 */
public class Context {
    private SuperStrategy strategy;

    public Context(String type) {
        if (null == type || "".equals(type)) {
            throw new RuntimeException("请选择优惠策略");
        }
        switch (type) {
            case "原价" :
                this.strategy = new NormalStrategy();
                break;
            case "打八折":
                this.strategy = new DiscountStrategy(0.8d);
                break;
            case "满300减100":
                this.strategy = new ReturnStrategy(300.0d, 100.0d);
            default:
                break;
        }
    }

    public double getResult(double price) {
        if (null == this.strategy) {
            throw new RuntimeException("请先选择优惠策略");
        }
        return this.strategy.getPrice(price);
    }
}
