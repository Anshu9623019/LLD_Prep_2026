package org.example.OppsByKunalKushwaha.Generics;

import java.util.List;

public class Print {

    Object value;

    public Object getPrintValue(){
        return value;
    }
    public void setPrintValue(Object object){
        this.value = object;
    }

    public void setPrintValue1(List<? extends Vehicle> vehicleList){

    }

    public  void setPrintValues2(List<? super Vehicle> vehicleList){

    }

    //diff bet generic and wild card method
    public void computeList(List<? extends Number> source,List<? extends Number> destination){

    }

    //Generic type method
    public <T extends Number> void computeList1(List<T> source,List<T> destination){

    }
}

