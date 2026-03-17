package org.example.OppsByKunalKushwaha.Properties;

public class BoxWeight extends Box{
    double weight;
    BoxWeight(){
        this.weight = -1;
    }

    public BoxWeight(double l, double h, double w, double weight) {
        super(l, h, w); // Used to initialize to value in parent constructor
        this.weight = weight;
        //System.out.println(super.weight); // can be access through this as well
    }

    BoxWeight(BoxWeight other){
        super(other);
        this.weight = 10;
    }
}
