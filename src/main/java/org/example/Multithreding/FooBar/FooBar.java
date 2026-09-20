package org.example.Multithreding.FooBar;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    Semaphore fooSemaphore = new Semaphore(1);
    Semaphore barSemaphore = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for(int i=0;i<n;i++){
            fooSemaphore.acquire();
            printFoo.run();
            barSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for(int i=0;i<n;i++){
            barSemaphore.acquire();
            printBar.run();
            fooSemaphore.release();
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(()->{
            System.out.println("Foo");
        });
        Thread thread2 = new Thread(()->{
            System.out.println("Baar");
        });

        thread1.start();
        thread2.start();
    }
}
