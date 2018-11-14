package AbstractFatoryPattern;

/**
 * oracle实现depart操作类
 * Created by houjue on 2018/11/12.
 */
public class OracleDepartDao implements IDepartment {

    @Override
    public void addDepartment() {
        System.out.println("oracle数据库插入一条depart记录");
    }

    @Override
    public void removeDepartment() {
        System.out.println("oracle数据库去除一条depart记录");
    }
}
