package DSA.StackImp;

public class StackImpArray {
    int top;
    int stack[];
    int size;
    StackImpArray(int size){
        this.top = -1;
        this.stack = new int[size];
        this.size = size;
    }

    public void put(int x){
        if(top+1>=size){
            System.out.println("Out of space");
        }else{
            top = top+1;
            stack[top] = x;
        }
    }

    public int peek(){
        if (top==-1){
            System.out.println("Stack is Empty");
            return -1;
        }
        return stack[top];
    }

    public void pop(){
        if(top>=0){
            top = top-1;
        }
    }
    public int size(){
        return top+1;
    }

    public static void main(String[] args) {
        StackImpArray st = new StackImpArray(10);
        st.put(1);
        st.put(3);
        st.put(13);
        st.put(14);
        System.out.println(st.peek());
        System.out.println(st.size());
    }
}
