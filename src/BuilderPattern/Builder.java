package BuilderPattern;

/**
 * 建造者抽象类，定义生成和构建产品的行为
 * Created by houjue on 2018/11/12.
 */
public abstract class Builder {
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract Product getResult();
}
