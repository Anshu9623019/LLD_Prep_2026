package DSA.StackImp;

import java.util.LinkedList;
import java.util.Queue;

public class UsingQueue {
    Queue<Integer> queue;
    UsingQueue(){
        queue = new LinkedList<>();
    }

    public void push(int x){
        int size = queue.size();
        queue.add(x);
        while(size>0){
            queue.add(queue.poll());
            size--;
        }
    }
    public int peek(){
        return queue.peek();
    }
    public int pop(){
        return queue.poll();
    }
    public int size(){
        return queue.size();
    }

    public static void main(String[] args) {
        UsingQueue q = new UsingQueue();
        q.push(19);
        q.push(12);
        System.out.println(q.peek());
    }
}
