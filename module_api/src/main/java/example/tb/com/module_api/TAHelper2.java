package example.tb.com.module_api;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Constructor;

import example.tb.com.module_compiler.one.ProxyInfo;

/**
 * @auther tb
 * @time 2018/3/27 下午2:51
 * @desc 实现帮助注入的类
 */
public class TAHelper2 {
    public static void inject(Activity o) {
        inject(o, o.getWindow().getDecorView());
    }
    
    public static void inject(Activity host, View root) {
        String classFullName = host.getClass().getName() + ProxyInfo.ClassSuffix;
        try {
            Class proxy = Class.forName(classFullName);
            Constructor constructor=proxy.getDeclaredConstructor(host.getClass(),View.class);
            constructor.setAccessible(true);
            constructor.newInstance(host,root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
