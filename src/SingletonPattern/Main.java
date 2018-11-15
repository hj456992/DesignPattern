package SingletonPattern;

/**
 * 单例模式：
 * 保证一个类仅有一个示例，并提供一个访问它的全局节点。
 *
 * 好处：
 * 单例模式因为Singleton类封装它的唯一实例，这样它可以严格地控制客户怎样访问它以及何时访问它。简单地说就是对唯一实例的受控访问。
 *
 * 测试场景：
 * 双重检查锁（懒汉式）;
 * 静态内部类（懒汉式）;
 * 其他可参考：
 * https://blog.csdn.net/nsw911439370/article/details/50456231
 *
 * Created by houjue on 2018/11/15.
 */
public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1.equals(singleton2)) {
            System.out.println("单例模式：双重检查锁生效");
        }

        SingletonB singleton3 = SingletonB.getInstance();
        SingletonB singleton4 = SingletonB.getInstance();
        if (singleton3.equals(singleton4)) {
            System.out.println("单例模式：静态内部类生效");
        }
    }
}
