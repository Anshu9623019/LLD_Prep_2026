package org.example.OppsByKunalKushwaha.Generics;

import org.example.OppsByKunalKushwaha.AbstractDemo.Parent;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        print.setPrintValue(1);
        Object value = print.getPrintValue();
        // Here we have to typecast the object value into int value;
        if((int)value==1){
            System.out.println("We need tpyecast the return value");
        }
        //Generic Class with any dataType, here we don't need to typecast the Object;
        PrintWithGeneric<String> printWithGeneric = new PrintWithGeneric<>();
        printWithGeneric.setValue("Ram");
        String res = printWithGeneric.printValue();
        System.out.println(res);

        PrintWithGeneric<Integer> obj2 = new PrintWithGeneric<>();
        obj2.setValue(10);
        Integer res1 = obj2.printValue();
        System.out.println(res1);

        //Internally it passes Object as parametrize type
        Print rawTypePrintObject =  new Print();
        rawTypePrintObject.setPrintValue(1);
        rawTypePrintObject.setPrintValue("Ram");

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Bus());
        vehicles.add(new Car());

        List<Bus> busList = new ArrayList<>();
        // vehicles = busList;  not possible
        // busList = vehicles not possible

        Vehicle vehicleobj = new Vehicle();
        Bus busObj = new Bus();

        vehicleobj = busObj; // runfine

        //UpperBound wildCard Generic
        Print print2 = new Print();
        print2.setPrintValue1(busList); // Bus list as Bus extend Vehicle
        print2.setPrintValue1(vehicles); // VehicleList

        //LowerBound wild card generic
        List<Object> objList = new ArrayList<>();
        print2.setPrintValues2(objList);

        //Dif betn wildcard method and generic method creation

        List<Integer> wildCardInteger = new ArrayList<>();
        List<Float> wildCardList2float = new ArrayList<>();

        Print obj3 = new Print();
        //obj3.computeList1(wildCardInteger,wildCardList2float); // I will give error cause both parameter are of different dataType of Number
        obj3.computeList(wildCardInteger,wildCardList2float);




    }
}
