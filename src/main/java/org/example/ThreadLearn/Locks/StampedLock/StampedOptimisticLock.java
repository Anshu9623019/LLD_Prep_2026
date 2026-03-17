package org.example.ThreadLearn.Locks.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampedOptimisticLock {

    StampedLock lock = new StampedLock();
    int a  = 10;
    public void producer(){
        long stamp = lock.tryOptimisticRead();
        try {
            System.out.println("Read lock acquired :"+ Thread.currentThread().getName());
            a = 11;
            Thread.sleep(6000);
            if (lock.validate(stamp)){
                System.out.println("Updated a value successfully");
            }else {
                System.out.println("Rollback of work");
                a = 10;
            }
        }catch (Exception e){

        }

    }

    public  void  consumer(){
        long stamp = lock.writeLock();
        try {
            System.out.println("write lock acquired by :"+ Thread.currentThread().getName());
            a = 9;
        }catch (Exception e){

        }
        finally {
            lock.unlockWrite(stamp);
        }
    }
}
