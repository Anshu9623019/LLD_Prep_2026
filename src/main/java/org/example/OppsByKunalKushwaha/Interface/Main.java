package org.example.OppsByKunalKushwaha.Interface;

public class Main {
    public static void main(String[] args) {
        Engine car = new Car();
        car.accelarate();
        car.start();
        car.stop();

        Media obj = new Car();
        obj.start();

        NiceCar niceCar = new NiceCar(new ElectricEngine(),new CDPlayer());
        niceCar.start();
        niceCar.stop();
        niceCar.stopMusic();
        niceCar.upgradeEngine();
        niceCar.stop();


    }
}
