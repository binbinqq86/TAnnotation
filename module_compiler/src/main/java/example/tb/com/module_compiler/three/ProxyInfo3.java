package example.tb.com.module_compiler.three;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * @auther tb
 * @time 2018/3/27 下午2:44
 * @desc 对应需要生成某个类的全部相关信息(采用javaPoet)
 */
public class ProxyInfo3 {
    /**
     * 类
     */
    public TypeElement typeElement;
    /**
     * 类注解的值(布局id)
     */
    public int value;
    public String packageName;
    /**
     * key为id，也就是成员变量注解的值，value为对应的成员变量
     */
    public Map<Integer, VariableElement> mInjectElements = new HashMap<>();
    
    public Map<Integer, ExecutableElement> mInjectMethods = new HashMap<>();
    
    /**
     * 采用类名方式不能被混淆(否则编译阶段跟运行阶段，该字符串会不一样)，或者采用字符串方式
     */
    public static final String PROXY = "TA";
    public static final String ClassSuffix = "_" + PROXY;
    
    public String getProxyClassFullName() {
        return typeElement.getQualifiedName().toString() + ClassSuffix;
    }
    
    public String getClassName() {
        return typeElement.getSimpleName().toString() + ClassSuffix;
    }
    
    /**
     * $L,原封不动
     * $S,字符串
     * $T,类
     *
     * @return
     */
    public String generateJavaCode() {
        ClassName viewClass = ClassName.get("android.view", "View");
        ClassName keepClass = ClassName.get("android.support.annotation", "Keep");
        ClassName clickClass = ClassName.get("android.view", "View.OnClickListener");
        ClassName typeClass = ClassName.get(typeElement.getQualifiedName().toString().replace("." + typeElement.getSimpleName().toString(), ""), typeElement.getSimpleName().toString());
        
        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(typeClass, "host", Modifier.FINAL)
                .addParameter(viewClass, "object", Modifier.FINAL);
        if (value > 0) {
            builder.addStatement("host.setContentView($L)", value);
        }
        for (int id : mInjectElements.keySet()) {
            VariableElement variableElement = mInjectElements.get(id);
            String name = variableElement.getSimpleName().toString();
            String type = variableElement.asType().toString();
            //这里object如果不为空，则可以传入view等对象
            builder.addStatement("host.$L=($L)object.findViewById($L)", name, type, id);
        }
        for (int id : mInjectMethods.keySet()) {
            ExecutableElement executableElement = mInjectMethods.get(id);
            VariableElement variableElement = mInjectElements.get(id);
            String name = variableElement.getSimpleName().toString();
            TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(clickClass)
                    .addMethod(MethodSpec.methodBuilder("onClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(viewClass, "view")
                            .addStatement("host.$L(host.$L)", executableElement.getSimpleName().toString(), name)
                            .returns(void.class)
                            .build())
                    .build();
            builder.addStatement("host.$L.setOnClickListener($L)", name, comparator);
        }
        MethodSpec methodSpec = builder.build();
        TypeSpec typeSpec = TypeSpec.classBuilder(getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(keepClass)
                .addMethod(methodSpec)
                .build();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
        return javaFile.toString();
    }
    
}
