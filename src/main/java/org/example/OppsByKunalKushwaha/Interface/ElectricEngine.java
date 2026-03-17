package org.example.OppsByKunalKushwaha.Interface;

public class ElectricEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Start the Electric engine");
    }

    @Override
    public void stop() {
        System.out.println("stop the Electric engine");
    }

    @Override
    public void accelarate() {
        System.out.println("accelerate the Electric engine");
    }
}
