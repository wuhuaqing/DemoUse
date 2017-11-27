package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/6.
 */

public class PartOrther2 implements  PartOrtherInterFace {
    public int c;
    public PartOrther2(){}
    public PartOrther2(int a,int b){
          c = a*b;
    }
}
