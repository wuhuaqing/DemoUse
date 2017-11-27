package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/6.
 * 对象组装类
 */

public class OtherObjDirector  implements DirectorInterFace<OtherObj> {

    @Override
    public  OtherObj crateBigObg() {
        OtherObj otherObj = new OtherObj();
        OtherObjBuilder otherObjBuilder= new OtherObjBuilder();
        PartOrther1 partOrther1 = (PartOrther1) otherObjBuilder.getBuilderPart1();
        PartOrther2 partOrther2 = (PartOrther2) otherObjBuilder.getBuilderPart2();
        otherObj.partOrther1 = partOrther1;
        otherObj.partOrther2 = partOrther2;
        return otherObj;
    }
}
