package org.example.OppsByKunalKushwaha.Generics;

public class MultipleGeneric<T,K> {
    private K key;
    private T value;
    public void setValue(K key,T value){
        this.key = key;
        this.value = value;
    }
    public T getValue(){
        return this.value;
    }
    public  K getKey(){
        return this.key;
    }

    public static void main(String[] args) {
        MultipleGeneric<String,Integer> obj = new MultipleGeneric<>();
        obj.setValue(10,"Ram");
        System.out.println(obj.getKey());
    }
}
