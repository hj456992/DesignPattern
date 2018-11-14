package DecoratorPattern;

/**
 * Created by houjue on 2018/11/7.
 */
public class SkirtDecorator extends ClothDecorator {


    @Override
    public void show() {
        super.show();
        System.out.println("裙子");
    }
}
