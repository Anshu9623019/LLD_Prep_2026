package org.example.OppsByKunalKushwaha.Generics;

public class NonGenericSubclass extends PrintWithGeneric<String>{

    public static void main(String[] args) {
        NonGenericSubclass obj = new NonGenericSubclass();
        // We have to define while extending the generic class to non-generic class
        obj.setValue("Anshu");
        String res = obj.printValue();
        System.out.println(res);
    }
}
