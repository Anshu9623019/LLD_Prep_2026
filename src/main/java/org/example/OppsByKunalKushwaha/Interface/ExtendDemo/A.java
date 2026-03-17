package org.example.OppsByKunalKushwaha.Interface.ExtendDemo;

public interface A {

    //Static interface method should always have a body
    static void greeting(){
        System.out.println("I'm static method");
    }
    void fun();

    default void run(){
        System.out.println("Default method in interface");
    }
}
