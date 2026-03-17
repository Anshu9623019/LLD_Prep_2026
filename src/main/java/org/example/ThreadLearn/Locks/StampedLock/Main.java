package org.example.ThreadLearn.Locks.StampedLock;

public class Main {
    public static void main(String[] args) {

        SimpleStampedLock simpleStampedLock = new SimpleStampedLock();

        Thread th1 = new Thread(()->{
            simpleStampedLock.producer();
        });
        Thread th2 = new Thread(()->{
            simpleStampedLock.producer();
        });
        Thread th3 = new Thread(()->{
            simpleStampedLock.consumer();
        });
        th1.start();
        th2.start();
        th3.start();


        //Optimistic Lock
        StampedOptimisticLock stampedOptimisticLock = new StampedOptimisticLock();
        Thread t3 = new Thread(()->{
            simpleStampedLock.producer();
        });
        Thread t4 = new Thread(()->{
            simpleStampedLock.producer();
        });
        t3.start();
        t4.start();
    }
}
