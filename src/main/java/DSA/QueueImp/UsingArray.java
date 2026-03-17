package DSA.QueueImp;

public class UsingArray {
    int size;
    int start ;
    int end ;
    int currSize;
    int queue[];

    UsingArray(int size){
        start = -1;
        this.size = size;
        end = -1;
        currSize = 0;
        queue = new int[size];
    }

    public void add(int x){
        if(currSize<size){
            if (start==-1 && end==-1){
                start = start+1;
                end = end +1;
            }else {
                end = (end + 1) % size;
            }
            queue[end]=x;
            currSize = currSize+1;
        }
    }

    public int poll(){
      if(currSize>0){
          int ele = queue[start];
          if(currSize==1){
              start = -1;
              end = -1;
          }else {
              start = (start +1)%size;
          }
          currSize = currSize-1;
          return ele;
      }
      return -1;
    }

    public int peek(){
        if(currSize>0){
            return queue[start];
        }
        return -1;
    }
    public int size(){
        return currSize;
    }

    public static void main(String[] args) {
        UsingArray q = new UsingArray(4);
        q.add(10);
        q.add(10);
        q.add(10);
        q.poll();
        q.add(10);

    }
}
