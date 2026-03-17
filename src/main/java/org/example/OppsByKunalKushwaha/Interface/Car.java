package org.example.OppsByKunalKushwaha.Interface;

public class Car implements Engine, Brake, Media{


    @Override
    public void start() {
        System.out.println("I start like a normal car");
    }

    @Override
    public void stop() {
        System.out.println("I stop like a normal car");
    }

    @Override
    public void accelarate() {
        System.out.println("I accelerate as normal car");
    }
}
