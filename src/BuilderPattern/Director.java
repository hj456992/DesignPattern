package BuilderPattern;

/**
 * Created by houjue on 2018/11/12.
 */
public class Director {

    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
