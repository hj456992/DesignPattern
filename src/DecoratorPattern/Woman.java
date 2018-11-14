package DecoratorPattern;

/**
 * 对应图中的ConcreteComponent
 * Created by houjue on 2018/11/7.
 */
public class Woman implements Person {
    private String name;

    public Woman(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("女生【" + name + "】的装饰为：");
    }
}
