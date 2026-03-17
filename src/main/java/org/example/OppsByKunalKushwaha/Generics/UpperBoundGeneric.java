package org.example.OppsByKunalKushwaha.Generics;

public class UpperBoundGeneric<T extends Number> {

    T value;
    public  T getPrintValue(){
        return value;
    }
    public void setPrintValue(T value){
        this.value = value;
    }

    public static void main(String[] args) {
        UpperBoundGeneric<Integer> obj = new UpperBoundGeneric<>();
       // UpperBoundGeneric<String> obj1 = new UpperBoundGeneric<>(); // Incorrect as string is not child of number

    }
}
