package DecoratorPattern;

/**
 * 对应图中的ConcreteComponent
 * Created by houjue on 2018/11/7.
 */
public class Man implements Person {
    private String name;

    public Man(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("男生【"+name+"】的装饰为：");
    }
}
