package org.example.OppsByKunalKushwaha.Generics;

public class PrintWithGeneric<T> {
    T value;
    public T printValue(){
        return value;
    }
    void setValue(T value){
        this.value = value;
    }
}
