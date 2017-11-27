package JavaDesignPattern.Builder.Builder.builder;

import JavaDesignPattern.Builder.Builder.builder.factory.ObjectFactory;

/**
 * Created by admin on 2017/7/5.
 */

public class BigObjBuilder  implements ObjBuilderInterFace <PartInterFace> {


    @Override
    public Part1  getBuilderPart1() {
        Part1 part1 = new Part1();
        part1.setPart1Str("这是小一咯");
        return part1;
    }

    @Override
    public Part2 getBuilderPart2() {
         Part2 part2 =  new Part2();
         part2.setPart2Str("这是小二咯");
        return part2;
    }


    /**
     *   通过返回接口来实现一个方法可以返回多种对象的操作，只要这些类实现了该接口，就可以使用该方法来构建该类的实例对象
     * @param clazz
     * @param str
     * @return
     */
    @Override
    public PartInterFace getBuilderPart(Class clazz,String str) {
        //使用工场模式构建对象
        PartInterFace partInterFace = (PartInterFace)ObjectFactory.getInstance(clazz);
        partInterFace.setPartStr(str);
        return partInterFace;
    }
}
