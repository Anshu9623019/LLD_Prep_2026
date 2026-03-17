package org.example.OppsByKunalKushwaha.Interface;

public class PowerEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Start the power engine");
    }

    @Override
    public void stop() {
        System.out.println("stop the power engine");
    }

    @Override
    public void accelarate() {
        System.out.println("accelerate the power engine");
    }
}
