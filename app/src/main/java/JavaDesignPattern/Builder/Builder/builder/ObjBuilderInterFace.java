package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/5.
 * 建造接口
 */

public interface ObjBuilderInterFace<T>  {
       T getBuilderPart1();
       T getBuilderPart2();
       T getBuilderPart(Class  clazz,String str);
}
