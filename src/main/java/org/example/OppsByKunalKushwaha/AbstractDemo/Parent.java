package org.example.OppsByKunalKushwaha.AbstractDemo;

public abstract class Parent {
    int age;
    final int value;

    public Parent() {
        this.age = age;
        this. value = 10;
    }

    static  void hello(){
        System.out.println("Hello abstract static method");
    }

    void normal(){
        System.out.println("Normal method");
    }

    abstract void carrer(String name);
    abstract void partner(String name,int age);
}
