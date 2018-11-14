package AbstractFatoryPattern;

/**
 * mysql工厂实现类
 * Created by houjue on 2018/11/12.
 */
public class MysqlFactory implements IFactory {
    @Override
    public IUser createUser() {
        return new MysqlUserDao();
    }

    @Override
    public IDepartment createDepartment() {
        return new MysqlDepartDao();
    }
}
