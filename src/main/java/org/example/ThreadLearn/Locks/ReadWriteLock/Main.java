package org.example.ThreadLearn.Locks.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Thread th1 = new Thread(()->{
            sharedResource.producer(lock);
        });
        Thread th2 = new Thread(()->{
            sharedResource.producer(lock);
        });
        Thread th3 = new Thread(()->{
            sharedResource.consume(lock);
        });

        th1.start();
        th2.start();
        th3.start();
    }
}
