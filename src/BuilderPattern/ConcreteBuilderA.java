package BuilderPattern;

/**
 * 具体建造类A，定义A类型的产品建造方案
 * Created by houjue on 2018/11/12.
 */
public class ConcreteBuilderA extends Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("组件A");
    }

    @Override
    public void buildPartB() {
        product.add("组件B");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
