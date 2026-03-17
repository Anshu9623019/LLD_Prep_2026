package org.example.OppsByKunalKushwaha.PolyMorphism;

public class Number {

    double sum(double a,int b){
        return a +b;
    }
    double sum(int a, int b){
        return a +b;
    }
    double sum(int a,int b,int c){
        return a+b +c;
    }

    public static void main(String[] args) {
        Number obj = new Number();
        obj.sum(1,2);
        obj.sum(1,2,3);

    }
}
