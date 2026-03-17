package org.example.ThreadLearn;

public class MonitorLock {

    public synchronized void task1(){
        try {
            System.out.println("inside task 1");
            Thread.sleep(10000);
        } catch (Exception e) {
            //Print e
        }
    }

    public void task2(){
        System.out.println("task2, But before synchronized :");
        synchronized (this){
            System.out.println("task 2 inside synchronized");
        }
    }
    public void task3(){
        System.out.println("Task3");
    }
}
