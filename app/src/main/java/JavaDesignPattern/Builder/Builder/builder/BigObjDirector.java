package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/5.
 * builder 模式中的组装类
 */

public class BigObjDirector implements DirectorInterFace<BigObj> {

    @Override
    public BigObj crateBigObg() {
        BigObjBuilder builder = new BigObjBuilder();
        // Part1 part1  = (Part1) builder.getBuilderPart1();
        //Part2 part2  = (Part2) builder.getBuilderPart2();
        //使用构建器构建不同类的对象实例
        Part1 part1 = (Part1) builder.getBuilderPart(Part1.class, "这是小一咯");
        Part2 part2 = (Part2) builder.getBuilderPart(Part2.class, "这是小二咯");
        BigObj bo = new BigObj();
        bo.part1 = part1;
        bo.part2 = part2;
        return bo;
    }
}
