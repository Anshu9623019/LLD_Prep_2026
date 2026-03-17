package LLD.DesignPettern.Structural.Adapter;

interface SmartDevice{
    void turnOff();
    void turnOn();
}

class  AirConditioner{
    public void connectViaBluetooth(){
        System.out.println("AC Connection via Bluetooth");
    }
    public void startColling(){
        System.out.println("Cooling stated");
    }
    public void stopColling(){
        System.out.println("Cooling stopped");
    }
    public void dicConnectViaBluetooth(){
        System.out.println("AC DisConnected via Bluetooth");
    }
}

class  SmartLight{
    public void connectViaWIFI(){
        System.out.println("Light Connection via WIFI");
    }
    public void switchOn(){
        System.out.println("Switch On");
    }
    public void switchOff(){
        System.out.println("Switch Off");
    }
    public void disConnectViaWIFI(){
        System.out.println("Light Disconnected via WIFI");
    }
}

class  ACAdapter implements SmartDevice{
    AirConditioner ac;

    ACAdapter(AirConditioner ac){
        this.ac = ac;
    }

    @Override
    public void turnOff() {
        ac.dicConnectViaBluetooth();
        ac.stopColling();
    }

    @Override
    public void turnOn() {
        ac.connectViaBluetooth();
        ac.startColling();
    }
}

class LightAdapter implements  SmartDevice{
    SmartLight light;
    LightAdapter(SmartLight light){
        this.light  = light;
    }
    @Override
    public void turnOff() {
        light.disConnectViaWIFI();
        light.switchOff();
    }
    @Override
    public void turnOn() {
        light.connectViaWIFI();
        light.switchOn();
    }
}

public class Main {
    public static void main(String[] args) {

        SmartDevice acDevice = new ACAdapter(new AirConditioner());
        acDevice.turnOn();
        acDevice.turnOff();
        SmartDevice light = new LightAdapter(new SmartLight() );
        light.turnOn();
        light.turnOff();

    }
}
