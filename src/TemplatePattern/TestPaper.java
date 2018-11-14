package TemplatePattern;

/**
 * 抽象类,定义试题模板及填写答案行为
 * Created by houjue on 2018/11/11.
 */
public abstract class TestPaper {

    public void testQuestion1() {
        System.out.println("全班最好看的是谁：a.Lili b.Kitty c.Mary");
        System.out.println(answer());
    }

    protected abstract String answer();
}
