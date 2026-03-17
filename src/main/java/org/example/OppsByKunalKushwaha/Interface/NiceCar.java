package org.example.OppsByKunalKushwaha.Interface;

public class NiceCar {

    Engine engine;
    CDPlayer cdPlayer;

    public NiceCar(){
        this.engine = new PowerEngine();
    }

    public NiceCar(Engine engine,CDPlayer cdPlayer) {
        this.engine = engine;
        this.cdPlayer = cdPlayer;
    }

    void start(){
        engine.start();
    }
    void stop(){
        engine.stop();
    }

    void startMusic(){
        cdPlayer.start();
    }
    void stopMusic(){
        cdPlayer.stop();
    }

    void upgradeEngine(){
        engine = new ElectricEngine();
    }
}
