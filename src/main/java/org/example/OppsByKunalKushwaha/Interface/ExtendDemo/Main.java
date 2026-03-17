package org.example.OppsByKunalKushwaha.Interface.ExtendDemo;

public class Main implements  B{
    @Override
    public void greet() {
        System.out.println("B method");
    }

    @Override
    public void fun() {
        System.out.println("A Method");
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.run();
        A.greeting();
    }
}
