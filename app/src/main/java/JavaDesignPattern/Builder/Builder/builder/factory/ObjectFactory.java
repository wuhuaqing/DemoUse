package JavaDesignPattern.Builder.Builder.builder.factory;

import JavaDesignPattern.Builder.Builder.builder.Part1;
import JavaDesignPattern.Builder.Builder.builder.Part2;
import JavaDesignPattern.Builder.Builder.builder.PartInterFace;

/**
 * Created by admin on 2017/7/6.
 * 对象构建工厂类
 */

public class ObjectFactory  {

    public static PartInterFace getInstance(String className,String str){
        if("Part1".equals(className)){
            return  new Part1(str);
        }else if("Part2".equals(className)){
            return  new Part2(str);
        }else{
            return null;
        }
    }

    /***
     * 使用反射、泛型来获取多种类型的对象数据
     * @param clazz
     * @param <T>  参数类型限定
     * @return
     */
    public static <T> T getInstance(Class<T>  clazz ){
        if( clazz!=null){
            T bean= null;
            try {
                bean = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return  bean;
        }else{
            return null;
        }
    }
}
