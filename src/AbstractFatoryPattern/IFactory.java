package AbstractFatoryPattern;

/**
 * 抽象工厂类
 * Created by houjue on 2018/11/12.
 */
public interface IFactory {
    // 创建user对象
    IUser createUser();

    // 创建department对象
    IDepartment createDepartment();
}
