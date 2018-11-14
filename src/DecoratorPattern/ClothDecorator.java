package DecoratorPattern;

/**
 * Created by houjue on 2018/11/7.
 */
public class ClothDecorator implements Person {
    private Person person;

    public void Decorator(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        person.show();
    }
}
