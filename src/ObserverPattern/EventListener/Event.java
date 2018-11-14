package ObserverPattern.EventListener;

import java.lang.reflect.Method;

/**
 * Created by houjue on 2018/11/13.
 */
public class Event {
    // 要通知的对象
    private Object object;
    // 要执行的方法
    private String methodName;
    // 要执行的方法的传参
    private Object[] params;
    // 传参的类型
    private Class[] paramTypes;

    public Event() {
    }

    public Event(Object object, String methodName, Object[] params) {
        this.object = object;
        this.methodName = methodName;
        this.params = params;
        generateParamType(this.params);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    private void generateParamType(Object[] params) {
        this.paramTypes = new Class[params.length];
        for (int i=0; i < params.length; i ++) {
            this.paramTypes[i] = params[i].getClass();
        }
    }

    public void invoke() throws Exception {
        Method method = this.object.getClass().getMethod(this.methodName, this.paramTypes);

        if (method == null) {
            return;
        }
        method.invoke(this.object, this.params);
    }
}
