package BuilderPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类，由很多部分组成
 * Created by houjue on 2018/11/12.
 */
public class Product {
    private List<String> parts = new ArrayList<>();

    // 添加组件
    public void add(String part) {
        parts.add(part);
    }

    // 展示产品
    public void show() {
        System.out.println("该产品由以下部件组成");
        for (String part : parts) {
            System.out.println("组件：" + part);
        }
    }
}
