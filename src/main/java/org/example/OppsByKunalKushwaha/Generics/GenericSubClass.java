package org.example.OppsByKunalKushwaha.Generics;

public class GenericSubClass<T> extends PrintWithGeneric<T>{

    public static void main(String[] args) {
        GenericSubClass<Integer> obj = new GenericSubClass<>();
        obj.setValue(100);
        int res = obj.printValue();
        System.out.println(res);
    }
}
