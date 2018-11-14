package PrototypePattern;

/**
 * 原型模式：
 * 原型模式其实就是从一个对象再创建另外一个可定制的对象，而且不需知道任何创建的细节。
 * 注意：
 * 1、java中有cloneable接口可以实现克隆功能
 * 2、clone方法仅对当前类中的字段进行复制，即当前类中有引用其他类时，复制的时候其他类依然指向原有的引用，并未进行深拷贝
 * 3、如想对引用的类属性进行深拷贝，需要再引用类中继承cloneable接口，并实现clone功能
 *
 * 场景：
 * 求职中所有人的简历格式均为名字必填，个人信息或者工作经验可填多个，所以简历模板要可复用
 * Created by houjue on 2018/11/11.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Resume a = new Resume("侯珏");
        a.setPersonalInfo("男","25");
        a.setWorkExprience("2014-10", "111公司");

        Resume b = (Resume) a.clone();
        b.setWorkExprience("2016-01", "222公司");

        a.display();
        b.display();
    }
}
