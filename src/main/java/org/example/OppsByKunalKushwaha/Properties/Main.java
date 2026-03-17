package org.example.OppsByKunalKushwaha.Properties;

public class Main {
    public static void main(String[] args) {
        Box obj = new Box();
        Box obj2 = new Box(10);
        Box obj1 = new Box(10,23,23);
        Box obj3 = new Box(obj1);
        System.out.println(obj.l +" "+obj.h+" "+obj.w);

        BoxWeight obj4 = new BoxWeight();
        BoxWeight obj5 = new BoxWeight(10,12,13,14);
        System.out.println(obj4.h +" "+obj4.weight);

        //There are many variables in both parent and child classes
        // we are given access to variable
        Box obj6 = new BoxWeight(10,4,5,6);
       // System.out.println(obj6.weight); we can't access weight through Box class i.e super class of BoxWeight;

        //There are many variables in both parent and child classes
        // we are given access to variables that are in the ref type i.e BoxWeight
        // Hence, we should have access to weight variable
        // this also means, that the ones we are trying to access should be initialized
        // But here, when the obj itself is of type parent class,how will you call the constructor of parent class
        //that is why error
        // BoxWeight box7 = new Box(2,3,4) // throw error
        //System.out.println(box7);

        BoxPrice boxPrice = new BoxPrice();

        BoxWeight obj8 = new BoxWeight();
        obj8.greeting(); // Static method are inherited but, not override
    }
}
