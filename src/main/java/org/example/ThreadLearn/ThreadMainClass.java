package org.example.ThreadLearn;

public class ThreadMainClass {

    public static void main(String[] args) {
        // Most of the time this method is used.(create thread with the help of Runnable interface )
        System.out.println("going inside main method:"+ Thread.currentThread().getName());
        MultiThreadingLearning runnableObj = new MultiThreadingLearning();
        Thread  thread = new Thread(runnableObj);
        thread.start();
        System.out.println("Finish main method :"+ Thread.currentThread().getName());


        //Crete Thread with the help of thread class
        System.out.println("going inside main method :"+Thread.currentThread().getName());
        MultiThreading1 thread1 = new MultiThreading1();
        thread1.start();
        System.out.println("Finish main method :"+ Thread.currentThread().getName());

    }
}
