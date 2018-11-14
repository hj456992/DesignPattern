package AbstractFatoryPattern;

/**
 * 抽象工厂模式：
 * 提供一个创建一些相关或者相互依赖的对象的接口，而无需指定它们具体的类。
 *
 * 通常是在运行时刻再创建一个ConcreteFactory类的实例，这个具体的工厂再创建具有特定实现的产品对象，
 * 也就是说，为创建不同的产品对象，客户端应使用不同的具体工厂。
 *
 * 优点：
 *   最大的好处便是易于交换产品系列，由于具体工厂类，例如IFactory factory = new OracleFactory（），
 * 在一个应用中只需要在初始化的时候出现一次，这就使得改变一个应用的具体工厂变得非常容易，它只需要改变具体工厂即可使用不同的产品配置。
 *   第二大好处是，它让具体的创建实例过程与客户端分离，客户端是通过它们的抽象接口操纵实例，
 * 产品的具体类名也被具体工厂的实现分离，不会出现在客户代码中。事实上，你刚才写的例子，
 * 客户端所认识的只有IUser和IDepartment，至于它是用mysql来实现还是oracle来实现就不知道了。
 *
 * 缺点：
 * 比如我们现在要增加项目表Project，那就至少要增加三个类，IProject、SqlserverProject、AccessProject，
 * 还需要更改IFactory、SqlserverFactory和AccessFactory才可以完全实现。要改三个类，太糟糕了。
 *
 * 所有在用简单工厂的地方，都可以考虑用反射技术来去除switch或if，解除分支判断带来的耦合（反射实现后续补全）。
 *
 * 测试场景，如抽象工厂实例图所示
 * Created by houjue on 2018/11/12.
 */
public class Main {

    public static void main(String[] args) {
        IFactory factory = new OracleFactory();

        IUser user= factory.createUser();
        IDepartment department = factory.createDepartment();

        user.addUser();
        user.removeUser();

        department.addDepartment();
        department.removeDepartment();
    }
}
