package AbstractFatoryPattern;

/**
 * mysql实现user操作类
 * Created by houjue on 2018/11/12.
 */
public class MysqlUserDao implements IUser {
    @Override
    public void addUser() {
        System.out.println("mysql数据库插入一条user记录");
    }

    @Override
    public void removeUser() {
        System.out.println("mysql数据库去除一条user记录");
    }
}
