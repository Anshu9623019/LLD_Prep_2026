package org.example.OppsByKunalKushwaha.Interface;

public class CDPlayer implements Media{
    @Override
    public void start() {
        System.out.println("Start CD Playe");
    }

    @Override
    public void stop() {
        System.out.println("Stop CD player");
    }
}
