package org.example.ThreadLearn.Locks.Semophores;


public class Main {

    public static void main(String[] args) {

        SharedResources sharedResources = new SharedResources();
        Thread th1 = new Thread(()->{
            sharedResources.producer();
        });
        Thread th2 = new Thread(()->{
            sharedResources.producer();
        });
        Thread th3 = new Thread(()->{
            sharedResources.producer();
        });
        Thread th4 = new Thread(()->{
            sharedResources.producer();
        });

        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}
