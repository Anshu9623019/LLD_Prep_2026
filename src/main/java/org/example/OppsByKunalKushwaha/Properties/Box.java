package org.example.OppsByKunalKushwaha.Properties;

public class Box {

    double l;
    double h;
    double w;
    // double weight
    Box() {
        super(); // call the constructor directly above it
        this.h = -1;
        this.l = -1;
        this.w = -1;
    }
    Box(double side){
         this.l = side;
         this.h = side;
         this.w = side;
    }
    Box(double l, double h, double w) {
        this.l = l;
        this.h = h;
        this.w = w;
    }

    //Copy Constructor
    Box(Box old){
         this.l  = old.l;
         this.h = old.h;
         this.w = old.w;
    }

    public static void greeting(){
        System.out.println("Greeting from class Box");
    }
}
