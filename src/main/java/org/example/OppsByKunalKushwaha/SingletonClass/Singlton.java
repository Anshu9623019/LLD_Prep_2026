package org.example.OppsByKunalKushwaha.SingletonClass;

public class Singlton {

    //Eager initialization
    public  static Singlton obj1 = new Singlton();
    public  static Singlton obj;

    private static volatile Singlton obj2 ;

    private Singlton(){
    }

    //Lazy initialization
    public static Singlton getInstance(){
        //Check whether 1 obj only is created or not
        if(obj==null){
            return new Singlton();
        }else {
            return obj;
        }
    }

    //Eager initialization
    public static Singlton getInstance1(){
        return obj1;
    }

    //Synchronization Block
    synchronized public static Singlton getInstance2(){
        if(obj==null){
            return new Singlton();
        }
        return obj;
    }

    //Syncronized Block, this is used primarily
    public static Singlton getInstance3(){
        if(obj2==null){
            synchronized(Singlton.class){
                if(obj2==null){
                    obj2 = new Singlton();
                }
            }
        }
        return obj2;
    }
}


