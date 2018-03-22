package example.tb.com.tannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

/**
 * @auther tb
 * @time 2018/3/21 上午11:47
 * @desc view的点击事件
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//这个是为了采用动态代理实现注解注入
@EventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick")
public @interface BindOnClick {
    int[] value();
}
