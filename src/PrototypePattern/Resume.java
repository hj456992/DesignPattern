package PrototypePattern;

/**
 * 简历类，实现克隆接口
 * Created by houjue on 2018/11/11.
 */
public class Resume implements Cloneable {
    private String name;

    private String sex;

    private String age;

    private String joinWorkTime;

    private String company;

    public Resume(String name) {
        this.name = name;
    }

    public void setPersonalInfo(String sex, String age) {
        this.sex = sex;
        this.age = age;
    }

    public void setWorkExprience(String joinWorkTime, String company) {
        this.joinWorkTime = joinWorkTime;
        this.company = company;
    }

    public void display() {
        System.out.println("姓名：" + name + "，性别："+sex+"，年龄："+age);
        System.out.println("工作经历：" + joinWorkTime + " " + company);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
