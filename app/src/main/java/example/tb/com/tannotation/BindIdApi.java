package example.tb.com.tannotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther tb
 * @time 2018/3/20 下午2:24
 * @desc 执行具体绑定控件逻辑的api
 */
public class BindIdApi {
    public static void bindId(Activity obj) {
        Class<?> cls = obj.getClass();
        //使用反射调用setContentView
        if (cls.isAnnotationPresent(BindId.class)) {
            // 得到这个类的BindId注解
            BindId mId = (BindId) cls.getAnnotation(BindId.class);
            // 得到注解的值
            int id = mId.value();
            try {
                Method method = cls.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(obj, id);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        // 使用反射调用findViewById
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindId.class)) {
                BindId mId = field.getAnnotation(BindId.class);
                int id = mId.value();
                // 使用反射调用findViewById，并为字段设置值
                try {
                    Method method = cls.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    Object view = method.invoke(obj, id);
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void bindId2(Activity obj) {
        Class<?> cls = obj.getClass();
        if (cls.isAnnotationPresent(BindId.class)) {
            // 得到这个类的BindId注解
            BindId mId = (BindId) cls.getAnnotation(BindId.class);
            // 得到注解的值
            int id = mId.value();
            obj.setContentView(id);
        }
        // 使用反射调用findViewById
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindId.class)) {
                BindId mId = field.getAnnotation(BindId.class);
                int id = mId.value();
                View view=obj.findViewById(id);
                try {
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
