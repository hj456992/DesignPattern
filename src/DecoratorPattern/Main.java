package DecoratorPattern;

/**
 * 装饰模式：
 * 动态的给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活
 * 装饰模式把每个要装饰的功能放在单独的类中，并让这个类包装它所要装饰的对象，因此，当需要特定的功能时，
 * 客户端对象可以根据需求，有选择、有顺序的选择要装饰的功能
 * 最大优点：
 * 把类的核心功能和装饰功能区分开，去除相关类中重复的装饰逻辑
 *
 * 测试场景：
 * 1、人需要装扮
 * 2、男生需要男生装扮 女生需要女生装扮
 * 3、装扮有牛仔裤、喇叭裤、短裤、卫衣、毛衣、T-shirt、裙子等
 * 装饰模式实现方式：
 * 1、创建人这个接口，并定义装饰的行为
 * 2、创建男生、女生实际对象继承人的接口，并重载装扮行为为男生装饰、女生装饰
 * 3、创建服饰装饰类，聚合人的接口，并通过实现具体的人的对象来决定男、女装饰
 * 4、创建具体的服饰类，继承服饰装饰类，重载父类的装饰行为，并添加当前类的装饰
 * Created by houjue on 2018/11/6.
 */
public class Main {

    public static void main(String[] args) {
        Woman zhucaiyun = new Woman("朱彩云");
        Man hj = new Man("侯珏");

        SkirtDecorator skirt = new SkirtDecorator();
        TShirtDecorator tShirt = new TShirtDecorator();

        skirt.Decorator(zhucaiyun);
        tShirt.Decorator(hj);

        skirt.show();
        tShirt.show();
    }
}
