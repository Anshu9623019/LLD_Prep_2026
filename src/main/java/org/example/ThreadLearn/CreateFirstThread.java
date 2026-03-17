package org.example.ThreadLearn;

public class CreateFirstThread {

    // When Process is created, initial thread will also get created and that thread is called main thread.
    // After this thread we can create multiple threads that could be used for other concurrency use case.
    public static void main(String[] args) {
        System.out.println("Thread name:" + Thread.currentThread().getName());
    }
}
