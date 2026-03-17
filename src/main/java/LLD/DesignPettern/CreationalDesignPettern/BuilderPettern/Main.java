package LLD.DesignPettern.CreationalDesignPettern.BuilderPettern;


class  Car {
    String engine;
    int wheel;
    int seats;
    String color;
    boolean sunProof;
    boolean navigableSystem;

    public Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheel = builder.wheel;
        this.seats = builder.seats;
        this.color = builder.color;
        this.sunProof = builder.sunProof;
        this.navigableSystem = builder.navigableSystem;
    }

    public String getEngine() {
        return engine;
    }

    public int getWheel() {
        return wheel;
    }

    public int getSeats() {
        return seats;
    }

    public String getColor() {
        return color;
    }

    public boolean isSunProof() {
        return sunProof;
    }

    public boolean isNavigableSystem() {
        return navigableSystem;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheel=" + wheel +
                ", seats=" + seats +
                ", color='" + color + '\'' +
                ", sunProof=" + sunProof +
                ", navigableSystem=" + navigableSystem +
                '}';
    }

   static class CarBuilder {
        String engine;
        int wheel = 4;
        int seats = 5;
        String color = "Black";
        boolean sunProof = false;
        boolean navigableSystem = false;

        public CarBuilder setEngine(String engine){
            this.engine = engine;
            return this;
        }
        public CarBuilder setWheel(int wheel){
            this.wheel = wheel;
            return this;
        }
        public CarBuilder setSeats(int seats){
            this.seats = seats;
            return this;
        }
        public CarBuilder setColor(String color){
            this.color = color;
            return this;
        }
        public CarBuilder setSunproof(boolean sunproof){
            this.sunProof = sunproof;
            return this;
        }
        public CarBuilder setNavigable(boolean navigable){
            this.navigableSystem = navigable ;
            return this;
        }

        public Car build(){
            return new Car(this);
        }

    }
}

public class Main {
    public static void main(String[] args) {
        Car.CarBuilder carBuilder = new Car.CarBuilder();
         Car car = carBuilder.setEngine("V8").setColor("Blue").setNavigable(true).build();
        System.out.println(car.toString());
    }
}
