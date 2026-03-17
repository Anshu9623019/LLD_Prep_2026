package org.example.ThreadLearn.ProducerConsumerProblem;

public class ProducerConsumerMain {

    public static void main(String[] args) {
            Buffer buffer = new Buffer();
            Producer producer = new Producer(buffer);
            Consumer consumer = new Consumer(buffer);

            producer.start();
            consumer.start();
    }
}
