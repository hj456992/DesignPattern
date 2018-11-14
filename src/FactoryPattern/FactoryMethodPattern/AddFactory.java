package FactoryPattern.FactoryMethodPattern;

/**
 * 加法工厂
 * Created by houjue on 2018/11/11.
 */
public class AddFactory implements CalFactory {
    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }
}
