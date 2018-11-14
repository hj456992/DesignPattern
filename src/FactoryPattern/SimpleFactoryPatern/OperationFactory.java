package FactoryPattern.SimpleFactoryPatern;

/**
 * 工厂类
 * Created by houjue on 2018/11/11.
 */
public class OperationFactory {
    public static Operation createOperation(String op) {
        Operation operation = null;
        // todo 乘除待补全
        switch (op) {
            case "+" :
                operation = new OperationAdd();
                break;
            case "-" :
                operation = new OperationSub();
                break;
            default:
                    break;
        }
        return operation;
    }
}
