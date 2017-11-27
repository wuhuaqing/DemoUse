package JavaDesignPattern.Builder.Builder;

import JavaDesignPattern.Builder.Builder.builder.BigObj;
import JavaDesignPattern.Builder.Builder.builder.BigObjDirector;
import JavaDesignPattern.Builder.Builder.builder.OtherObj;
import JavaDesignPattern.Builder.Builder.builder.OtherObjDirector;

/**
 * Created by admin on 2017/7/5.
 */

public class Client {

    public static void main(String[] args){
         Car car =   new Car.Builder().buildCircle("好轮胎")
                 .buildEngee("强悍牌引擎")
                 .buildZuowei("超舒服座位")
                 .builDoor("超酷炫车门")
                 .createCar();
        System.out.println(car.circle);


        //使用builder模式创建的类对象，使用该类对象的内部实现比较复杂的情况，同时，各种组装顺序也会产生不同的对象类型。
        BigObj bo = new BigObjDirector().crateBigObg();
        System.out.println(bo.part1.getPart1Str());
        System.out.println(bo.part2.getPart2Str());


        OtherObj oo = new OtherObjDirector().crateBigObg();
        System.out.println(oo.partOrther1.c);
        System.out.println(oo.partOrther2.c);

    }
}
