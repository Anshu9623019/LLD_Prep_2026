package org.example.ThreadLearn;

public class SharedResource {

    boolean isAvailable = false;

    public synchronized void addItem(){
        isAvailable = true;
        System.out.println("Item Produced by :"+Thread.currentThread().getName());
        notifyAll();
    }
    public synchronized void  consumeItem(){
        while (!isAvailable){
            try {
                System.out.println("Consumer is waiting");
                wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("Item consumed by :"+Thread.currentThread().getName());
        isAvailable = false;
    }
}
