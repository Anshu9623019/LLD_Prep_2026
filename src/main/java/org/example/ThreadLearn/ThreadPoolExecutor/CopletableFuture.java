package org.example.ThreadLearn.ThreadPoolExecutor;

import java.util.concurrent.*;
;
public class CopletableFuture {

    public static void main(String[] args) {


            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 2,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(2),
                    Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                //This is the task which need to be completed by thread
                return "Task completed";
            },poolExecutor);

            System.out.println(asyncTask1.get());

        }catch (Exception e){

        }


        try {
            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
                //This is the task which need to be completed by thread
                return "Task completed";
            },poolExecutor).thenApply((String val)->{
                // Functionality which works on the result of previous async task.
                return  val + "Coding";
            });
            System.out.println(asyncTask2.get());
        }catch (Exception e){
        }
        try {
            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
                //This is the task which need to be completed by thread
                System.out.println("Thread name which runs 'SupplyAsync': " + Thread.currentThread().getName());
                return "Task completed and";
            },poolExecutor).thenApplyAsync((String val)->{
                // Functionality which works on the result of previous async task.
                System.out.println("Thread name which runs 'thenApply': "+ Thread.currentThread().getName());
                return  val + "Coding";
            });
            System.out.println(asyncTask2.get());
            System.out.println("Thread name for :" + Thread.currentThread().getName());
        }catch (Exception e){

        }
    }
}
