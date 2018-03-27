package example.tb.com.module_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther tb
 * @time 2018/3/27 下午5:08
 * @desc 不被混淆的地方可以加上此注解（适用于类，方法，构造函数，属性）
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface NoProguard {
}
