package BuilderPattern;

/**
 * 具体建造类B，定义B类型的产品建造方案
 * Created by houjue on 2018/11/12.
 */
public class ConcreteBuilderB extends Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("组件X");
    }

    @Override
    public void buildPartB() {
        product.add("组件Y");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
