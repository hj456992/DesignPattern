package StrategyPattern;

import java.io.IOException;
import java.util.Scanner;

/**
 * 策略模式：
 * 定义了算法的家族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化，不会影响到使用算法的客户
 *
 * 策略模式是一种定义一系列算法的方法，从概念上来看，所有这些算法完成的都是相同的工作，只是实现不同，
 * 它可以以相同的方式调用所有的算法，减少了各种算法类与使用算法类之间的耦合
 *
 * 策略模式的Strategy类层次为Context定义了一系列的可供重用的算法或行为。继承有助于析取出这些算法中的公共功能[DP]。
 *
 * 对于打折、返利或者其他的算法，其实都是对实际商品收费的一种计算方式，通过继承，可以得到它们的公共功能，即getPrice()
 *
 * 另外一个策略模式的优点是简化了单元测试，因为每个算法都有自己的类，可以通过自己的接口单独测试
 *
 * 通过将不同的行为封装在不同的策略中，避免了在一个类中使用大量的条件语句来选择合适的行为，可以在使用这些行为的类中消除条件语句，
 * 即策略模式封装了变化
 *
 * 策略模式就是用来封装算法的，但在实践中，我们发现可以用它来封装几乎任何类型的规则，
 * 只要在分析过程中听到需要在不同时间应用不同的业务规则，就可以考虑使用策略模式处理这种变化的可能性
 *
 * 在基本的策略模式中，选择所用具体实现的职责由客户端对象承担，并转给策略模式的Context对象
 *
 * 策略模式测试类
 * 场景：
 * 商场商品出售策略
 * 1、原价销售
 * 2、打折销售
 * 3、满减活动
 *
 * 思考：如何在context中去掉switch
 * Created by houjue on 2018/11/5.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("请输入商品价格");
        double price = new Scanner(System.in).nextDouble();
        System.out.println("请输入优惠策略，1、无优惠 2、打八折 3、满300减100");
        int strategyType = new Scanner(System.in).nextInt();
        String type = null;
        switch (strategyType) {
            case 1:
                type = "原价";
                break;
            case 2:
                type = "打八折";
                break;
            case 3:
                type = "满300减100";
                break;
            default:break;
        }
        Context context = new Context(type);
        System.out.println("实际商品价格为：" + context.getResult(price));
    }
}
