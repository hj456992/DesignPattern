package FactoryPattern.SimpleFactoryPatern;

/**
 * 简单工厂模式
 *
 * 场景：
 * 根据输入的运算符做+、-、*、/运算
 * Created by houjue on 2018/11/11.
 */
public class Main {
    public static void main(String[] args) {
        Operation operationAdd = OperationFactory.createOperation("+");
        operationAdd.setNumberA(20);
        operationAdd.setNumberB(30);
        System.out.println(operationAdd.getResult());
        Operation operationSub = OperationFactory.createOperation("-");
        operationSub.setNumberA(20);
        operationSub.setNumberB(30);
        System.out.println(operationSub.getResult());
    }
}
