package example.tb.com.tannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther tb
 * @time 2018/3/22 下午2:11
 * @desc
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    String methodName();
    String listenerSetter();
    Class listenerType();
}
