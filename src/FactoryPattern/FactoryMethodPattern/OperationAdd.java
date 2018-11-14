package FactoryPattern.FactoryMethodPattern;

/**
 * 加法类
 * Created by houjue on 2018/11/11.
 */
public class OperationAdd extends Operation {

    @Override
    public double getResult() {
        return this.getNumberA() + this.getNumberB();
    }
}
