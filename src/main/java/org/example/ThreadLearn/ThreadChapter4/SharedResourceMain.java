package org.example.ThreadLearn.ThreadChapter4;

public class SharedResourceMain {

    public static void main(String[] args) {

        System.out.println("Main Thread Started");
        SharedResource s1 = new SharedResource();

        Thread t1 = new Thread(()->{
            System.out.println("Thread 1 calling produce method");
            s1.produce();
        });
        //Thread Priority
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.setDaemon(true);
        t1.start();
        // Join method :  current method will be blocked and waits for the specific thread to finish.
        try {
            System.out.println("Waiting thread t1  complete");
            t1.join();
        }catch (Exception e){

        }

//        Thread t2 = new Thread(()->{
//            try {
//                Thread.sleep(2000);
//            }catch(Exception e){
//
//            }
//            System.out.println("Thread 2 calling produce method");
//            s1.produce();
//        });
//        t1.start();
//        t2.start();
//        try {
//            Thread.sleep(3000);
//        }catch (Exception e){
//
//        }
//        System.out.println("Thread 1 is suspended");
//
//            t1.suspend();
//
//        try {
//            Thread.sleep(3000);
//        }catch (Exception e){
//
//        }
//        t1.resume();



        System.out.println("Main thread is finishing the work");
    }
}
