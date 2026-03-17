package org.example.ThreadLearn.ThreadChapter4;

public class SharedResource {

    boolean isAvailable = false;

    public synchronized  void produce(){
        System.out.println("Lock acquired ");
        isAvailable = true;
        try {
            Thread.sleep(8000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Lock release");
    }
}
