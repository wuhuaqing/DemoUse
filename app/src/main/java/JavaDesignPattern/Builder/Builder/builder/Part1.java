package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/6.
 */

public class Part1 implements PartInterFace {
    public Part1(){}
    public Part1(String part1Str){
        this.part1Str = part1Str;
    }
    public String part1Str;
    public String getPart1Str(){
        return part1Str;
    }
    public void setPart1Str(String part1Str){
        this.part1Str = part1Str;
    }

    @Override
    public void setPartStr(String str) {
        this.part1Str = str;
    }
}
