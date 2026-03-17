package org.example.ThreadLearn.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.Future;

public class Callable {
    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3,1, TimeUnit.HOURS,new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        //Use Case1 with runnable
        Future<?> futureObj1 = poolExecutor.submit(()->{
            System.out.println("Task1 is completed");
        });

        try {
            Object object = futureObj1.get();
            System.out.println(object==null);
        }catch (Exception e){

        }

        //UseCase2 with runnable + output

        List<Integer> output = new ArrayList<>();

        Future<List<Integer>> futureObj2 = poolExecutor.submit(()->{
            output.add(10);
            System.out.println("Task2 with runnable and return object");
        },output);

        try {
            List<Integer> outputFromFutureObj2 = futureObj2.get();
            System.out.println(outputFromFutureObj2.get(0));
        }catch (Exception e){

        }

        // Use Case 3 with Callable
        Future<List<Integer>> futureObj3 = poolExecutor.submit(()->{
            System.out.println("Task3 with callable");
            List<Integer> listObj = new ArrayList<>();
            listObj.add(200);
            return listObj;
        });

        try {
            List<Integer> outputFutureObj3 = futureObj3.get();
            System.out.println(outputFutureObj3.get(0));
        }catch (Exception e){

        }

    }
}
