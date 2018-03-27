package example.tb.com.module_api;

import example.tb.com.module_compiler.ProxyInfo;

/**
 * @auther tb
 * @time 2018/3/27 下午2:51
 * @desc
 */
public class TAHelper {
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
