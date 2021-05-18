package main.java.pers.xiaoan.singleton;

/**
 * 枚举单例
 * @author: 康小安
 * @createDate: 2019/12/11 12:33
 */
public class EnumSingleton {

    private EnumSingleton() {}

    private enum Singleton{
        /** 接口 */
        INSTANCE;
        private final EnumSingleton instance;
        Singleton() {
            instance = new EnumSingleton();
        }
        private EnumSingleton getInstance() {
            return instance;
        }
    }
    
    /**
     * 对外调用接口
     * @author: 康小安
     * @createDate: 2021/5/17 14:52
     * @return: pers.xiaoan.singleton.enumsingleton.EnumSingleton
     */
    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}

