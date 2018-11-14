package FactoryPattern.FactoryMethodPattern;

/**
 * 减法工厂
 * Created by houjue on 2018/11/11.
 */
public class SubFactory implements CalFactory {

    @Override
    public Operation createOperation() {
        return new OperationSub();
    }
}
