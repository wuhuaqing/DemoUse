package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/6.
 * 对象构建类
 */

public class OtherObjBuilder implements  ObjBuilderInterFace<PartOrtherInterFace> {
    @Override
    public PartOrtherInterFace getBuilderPart1() {
        return new PartOrther1(1,2);
    }

    @Override
    public PartOrtherInterFace getBuilderPart2() {
        return new PartOrther2(1,2);
    }

    @Override
    public PartOrtherInterFace getBuilderPart(Class clazz, String str) {
        return null;
    }


}
