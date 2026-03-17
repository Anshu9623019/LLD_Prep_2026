package LLD.DesignPettern.Structural.Decorator;

interface  Coffee{
    String getDescriptions();
    double getCost();
}

class Expresso implements  Coffee{
    @Override
    public String getDescriptions() {
        return " Expresso Coffee";
    }

    @Override
    public double getCost() {
        return 10;
    }
}

class  Cappuccino implements  Coffee{

    @Override
    public String getDescriptions() {
        return "Cappuccino";
    }

    @Override
    public double getCost() {
        return 15;
    }
}

abstract class CoffeeDecorator implements Coffee{

    Coffee coffee;
    CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    @Override
    public String getDescriptions() {
        return coffee.getDescriptions();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator{
    MilkDecorator(Coffee coffee){
        super(coffee);
    }
    @Override
    public String getDescriptions(){
        return coffee.getDescriptions() + "Milk";
    }
    @Override
    public double getCost(){
        return coffee.getCost() + 0.50;
    }

}

class SugarDecorator extends CoffeeDecorator{
    SugarDecorator(Coffee coffee){
        super(coffee);
    }
    @Override
    public String getDescriptions(){
        return coffee.getDescriptions() + "Sugar";
    }
    @Override
    public double getCost(){
        return coffee.getCost() + 0.50;
    }

}

public class Main {

    public static void main(String[] args) {
        Coffee coffee = new Expresso();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println("Order : "+ coffee.getDescriptions());
        System.out.println("Total Cost : "+ coffee.getCost());
    }
}

