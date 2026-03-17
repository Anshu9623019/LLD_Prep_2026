package org.example.ThreadLearn.ThreadPoolExecutor;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,1, TimeUnit.HOURS,new ArrayBlockingQueue<>(2),
                new CustomThreadFactory(),new CustomomRejectedHandler());

        //submit task
        for(int i=0;i<4;i++){
            poolExecutor.submit(()->{
                try {
                    Thread.sleep(5000);
                    System.out.println("Thread name :" + Thread.currentThread().getName());
                }catch (Exception e){
                    //
                }
            });
        }
        poolExecutor.shutdown();
    }
}


class CustomomRejectedHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // Task Denied
        System.out.println("Task denied :" + r.toString());
    }
}

class  CustomThreadFactory implements ThreadFactory{


    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        return th;
    }
}