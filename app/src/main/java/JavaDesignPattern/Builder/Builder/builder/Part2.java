package JavaDesignPattern.Builder.Builder.builder;

/**
 * Created by admin on 2017/7/6.
 */

public class Part2  implements  PartInterFace{
    public Part2(){}
    public Part2(String part2Str){
        this.part2Str = part2Str;
    }
    public String part2Str;
    public String getPart2Str(){
        return part2Str;
    }
    public void setPart2Str(String part2Str){
        this.part2Str = part2Str;
    }

    @Override
    public void setPartStr(String str) {
        this.part2Str = str;
    }
}
