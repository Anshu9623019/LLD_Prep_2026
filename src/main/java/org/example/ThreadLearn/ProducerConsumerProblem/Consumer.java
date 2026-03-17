package org.example.ThreadLearn.ProducerConsumerProblem;

public class Consumer extends Thread{

    Buffer buffer;
    Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i=1;i<=10;i++){
            try {
                buffer.consume();
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
