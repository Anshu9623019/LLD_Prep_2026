package DSA.QueueImp;

import java.util.Stack;

public class UsingStack {
    Stack<Integer> sc1;
    Stack<Integer> sc2;
    UsingStack(){
        this.sc1 = new Stack<>();
        this.sc2 = new Stack<>();
    }

    public void add(int x){
        while(!sc1.isEmpty()){
            sc2.add(sc1.pop());
        }
        sc1.add(x);
        while (!sc2.isEmpty()){
            sc1.add(sc2.pop());
        }
    }
    public int pop(){
        return sc1.pop();
    }

    public int peek(){
       return sc1.peek();
    }
    public int size(){
       return sc1.size();
    }

    public static void main(String[] args) {
        UsingStack sc = new UsingStack();
        sc.add(12);
        sc.add(13);
        sc.add(11);
        sc.add(10);
        System.out.println(sc.pop());
        System.out.println(sc.peek());
    }
}
