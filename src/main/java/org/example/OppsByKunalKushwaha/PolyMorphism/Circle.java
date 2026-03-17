package org.example.OppsByKunalKushwaha.PolyMorphism;

public class Circle extends Shapes {


    // This will run when obj of circle is created
    // Hence it is overriding the parent class method
    @Override // This is called annotation
    void area(){
        System.out.println("Area is 0.5*r.r");
    }
}
