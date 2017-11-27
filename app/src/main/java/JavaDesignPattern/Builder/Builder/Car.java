package JavaDesignPattern.Builder.Builder;


/**
 * Created by admin on 2017/7/5.
 */

public class Car {
    private Car(){}
    public String circle;
    public String zuowei;
    public String engee;
    public String door;

      static class Builder{

        public String circle;
        public String zuowei;
        public String engee;
        public String door;

        public Builder buildCircle(String circle){
            this.circle =circle;
            return this;
        }
        public Builder buildZuowei(String zuowei){
            this.zuowei =zuowei;
            return this;
        }
        public Builder buildEngee(String engee){
            this.engee =engee;
            return this;
        }
        public Builder builDoor(String door){
            this.door =door;
            return this;
        }

        public Car createCar(){
            Car car = new Car();
            car.circle = circle;
            car.zuowei = zuowei;
            car.door = door;
            car.engee = engee;
            return car;
        }
    }
}
