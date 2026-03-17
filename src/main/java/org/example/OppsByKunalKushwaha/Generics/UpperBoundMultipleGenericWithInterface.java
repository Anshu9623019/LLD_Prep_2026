package org.example.OppsByKunalKushwaha.Generics;

public class UpperBoundMultipleGenericWithInterface<T extends Print & A & B >{

    T value;

    public T getPrintValue(){
        return value;
    }
    public void setValue(T value){
        this.value = value;
    }

    public static void main(String[] args) {
        C obj =  new C();
        PrintWithGeneric<C> printObj = new PrintWithGeneric<>();

    }
}
