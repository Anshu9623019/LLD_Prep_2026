package org.example.ThreadLearn;


public class MonitorLockMain {

    public static void main(String[] args) {

        MonitorLock monitorLock = new MonitorLock();
        Thread t1 = new Thread(()->{monitorLock.task1();});
        Thread t2 = new Thread(()->{monitorLock.task2();});
        Thread t3 = new Thread(()->{monitorLock.task3();});
        t1.start();
        t2.start();
        t3.start();

        // Producer and consumer Thread
        SharedResource sharedResource = new SharedResource();
        Thread producerThread = new Thread(()-> {
            System.out.println("Producer Thread :"+Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            sharedResource.addItem();
        });
        Thread consumerThread = new Thread(()->{
            System.out.println("Consumer Thread :" + Thread.currentThread().getName());
            sharedResource.consumeItem();
        });


        producerThread.start();
        consumerThread.start();
    }


}
