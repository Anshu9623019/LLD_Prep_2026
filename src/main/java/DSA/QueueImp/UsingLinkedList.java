package DSA.QueueImp;


class Node{
    int val;
    Node next ;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}
public class UsingLinkedList {
    Node start;
    Node end;
    int size;
    UsingLinkedList(){
        this.size = 0;
        this.end = null;
        this.start = null;
    }

    public void add(int x){
        Node temp = new Node(x);
        if(start==null && end==null){
            start = temp;
            end = temp;
        }else {
            end.next = temp;
            end = temp;
        }
        size++;
    }

    public int pop(){
        int ele = -1;
        if(start==null){
            end = null;
            return ele;
        }else {
            ele = start.val;
            start = start.next;
            size--;
        }
        return ele;
    }

    public int peek(){
        return start.val;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        UsingLinkedList que = new UsingLinkedList();
        que.add(10);
        que.add(13);
        que.add(12);
        que.add(1);
        System.out.println(que.peek());
        System.out.println(que.pop());
        System.out.println(que.pop());
    }
}
