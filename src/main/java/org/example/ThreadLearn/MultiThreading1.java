package org.example.ThreadLearn;

public class MultiThreading1 extends Thread {

    @Override
    public void  run(){
        System.out.println("Code executed by thread :" + Thread.currentThread().getName());
    }

}
