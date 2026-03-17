package org.example.ThreadLearn.ProducerConsumerProblem;

public class Producer extends Thread{

    Buffer buffer;

    Producer(Buffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            try{
                int item = (int) Math.random()*10;
                //System.out.println("Produced item :" +item);
                buffer.produce(item);
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
