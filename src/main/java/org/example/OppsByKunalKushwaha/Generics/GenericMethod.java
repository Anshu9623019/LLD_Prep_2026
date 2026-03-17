package org.example.OppsByKunalKushwaha.Generics;
public class GenericMethod {
    public <T,K> void setValue(MultipleGeneric<T,K> pair1,MultipleGeneric<T,K> pair2){
            if(pair1.getKey().equals(pair2.getKey())){
                System.out.println("Both have same key");
            }else{
                System.out.println("Not have same key");
            }
    }

    //Generic method with single generic parameter
    public <T> void setValue1(T busObject){

    }

    public static void main(String[] args) {
        GenericMethod obj = new GenericMethod();
        MultipleGeneric<String,Integer> pair1 = new MultipleGeneric<>();
        MultipleGeneric<String,Integer> pair2 = new MultipleGeneric<>();
        pair1.setValue(100,"Kumar");
        pair2.setValue(200,"Anshu");
        obj.setValue(pair1,pair2);

        //Generic method with single generic parameter
        GenericMethod obj1 = new GenericMethod();
        obj1.setValue1(new Print());

    }
}
