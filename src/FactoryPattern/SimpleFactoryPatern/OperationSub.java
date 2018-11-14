package FactoryPattern.SimpleFactoryPatern;

/**
 * 减法类
 * Created by houjue on 2018/11/11.
 */
public class OperationSub extends Operation {
    @Override
    public double getResult() {
        return this.getNumberA() - this.getNumberB();
    }
}
