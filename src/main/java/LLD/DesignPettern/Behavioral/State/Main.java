package LLD.DesignPettern.Behavioral.State;

interface TrafficLightState{
    void next(TrafficLightContext context);
    String getColor();
}

class RedState implements TrafficLightState{
    @Override
    public void next(TrafficLightContext context) {
        context.setState(new GreenState());
    }
    @Override
    public String getColor() {
        return "RED";
    }
}

class GreenState implements TrafficLightState{
    @Override
    public void next(TrafficLightContext context) {
        context.setState(new YellowState());
    }
    @Override
    public String getColor() {
        return "GREEN";
    }
}

class YellowState implements TrafficLightState{
    @Override
    public void next(TrafficLightContext context) {
        context.setState(new RedState());
    }
    @Override
    public String getColor() {
        return "YELLOW";
    }
}

class TrafficLightContext{
    TrafficLightState currentState;
    TrafficLightContext(){
        this.currentState = new RedState();
    }

    void setState(TrafficLightState state){
        this.currentState = state;
    }

    public void next(){
        currentState.next(this);
    }

    public String getColor(){
        return currentState.getColor();
    }
}


public class Main {
    public static void main(String[] args) {
        TrafficLightContext context = new TrafficLightContext();
        context.next();
        System.out.println(context.getColor());
    }
}
