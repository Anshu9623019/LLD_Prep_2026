package org.example.ThreadLearn.ProducerConsumerProblem;

import java.util.LinkedList;

public class Buffer {
    LinkedList<Integer> buffer = new LinkedList<>();
    final int capacity = 5;

    public synchronized void produce(int item) throws InterruptedException {
        while(buffer.size()>=capacity){
            System.out.println("Buffer is fulled waiting for consumer to consume");
            wait();
        }
        buffer.add(item);
        System.out.println("Produced :"+item);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while(buffer.isEmpty()){
            System.out.println("Buffer is empty, Consumer is waiting for element to produced :");
            wait();
        }
        int item = buffer.removeFirst();
        System.out.println("Consumed Item :"+item);
        notify();
        return item;
    }
}
