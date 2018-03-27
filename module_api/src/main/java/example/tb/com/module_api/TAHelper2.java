package example.tb.com.module_api;

import example.tb.com.module_compiler.one.ProxyInfo;

/**
 * @auther tb
 * @time 2018/3/27 下午2:51
 * @desc 实现帮助注入的类
 */
public class TAHelper2 {
    public static void inject(Object o) {
        inject(o, o);
    }
    
    public static void inject(Object host, Object root) {
        String classFullName = host.getClass().getName() + ProxyInfo.ClassSuffix;
        try {
            Class proxy = Class.forName(classFullName);
            TA injector = (TA) proxy.newInstance();
            injector.inject(host, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
