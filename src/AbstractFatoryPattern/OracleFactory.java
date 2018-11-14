package AbstractFatoryPattern;

/**
 * oracle工厂实现类
 * Created by houjue on 2018/11/12.
 */
public class OracleFactory implements IFactory {
    @Override
    public IUser createUser() {
        return new OracleUserDao();
    }

    @Override
    public IDepartment createDepartment() {
        return new OracleDepartDao();
    }
}
