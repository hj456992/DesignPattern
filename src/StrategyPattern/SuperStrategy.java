package StrategyPattern;

/**
 * 策略模式父类，定义商品价格策略基本方法，子策略继承该策略
 * Created by houjue on 2018/11/6.
 */
public abstract class SuperStrategy {
    public abstract double getPrice(double price);
}
