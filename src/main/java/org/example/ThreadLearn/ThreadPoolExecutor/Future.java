package org.example.ThreadLearn.ThreadPoolExecutor;

import java.util.concurrent.*;

public class Future {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,2,1,TimeUnit.HOURS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        // New Thread will be created and it will perform the task
        java.util.concurrent.Future<?> futureObj =  poolExecutor.submit(()->{
            try {
                Thread.sleep(7000);
                System.out.println("this is the task, which thread will execute");
            }catch (Exception e){

            }

        });

        // caller is checking the status of thread it is created
        System.out.println(futureObj.isDone());
        //
        try {
            futureObj.get(2,TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println("Time out exception happened");
        }

        System.out.println("is  done :"+ futureObj.isDone());
        System.out.println("is Cancelled :"+futureObj.isCancelled());
//        futureObj.cancel(true);
    }
}
