package DecoratorPattern;

/**
 * Created by houjue on 2018/11/7.
 */
public class TShirtDecorator extends ClothDecorator {

    @Override
    public void show() {
        super.show();
        addExtraClothes();
    }

    private void addExtraClothes() {
        System.out.println("加棉Tshirt");
    }
}
