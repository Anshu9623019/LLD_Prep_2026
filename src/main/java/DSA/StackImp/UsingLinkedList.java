package DSA.StackImp;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}

public class UsingLinkedList {
    int top;
    int size;
    Node curr;
    UsingLinkedList(){
        this.top = -1;
        this.size = 0;
        this.curr = null;
    }

    public void put(int x){
        Node temp = new Node(x);
        temp.next = curr;
        curr = temp;
        size++;
    }

    public int pop(){
        int ele = -1;
        if(curr==null){
            return ele;
        }else {
            ele = curr.val;
            curr = curr.next;
            size--;
        }
        return ele;
    }
    public int peek(){
        if(curr!=null){
            return curr.val;
        }
        return -1;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        UsingLinkedList li = new UsingLinkedList();

        li.put(10);
        li.put(10);
        li.put(11);
        li.put(13);
        System.out.println(li.pop());
        System.out.println(li.pop());
    }

}
