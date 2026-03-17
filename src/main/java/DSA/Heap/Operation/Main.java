package DSA.Heap.Operation;

import java.util.ArrayList;

class Heap<T extends Comparable<T>> {
    private ArrayList<T> list;

    public  Heap(){
        list = new ArrayList<>();
    }
    public void swap(int first,int second){
        T temp = list.get(first);
        list.set(first,list.get(second));
        list.set(second,temp);
    }

    int left(int i){
        return 2*i+1;
    }
    int right(int i){
        return 2*i+2;
    }

    public void insert(T ele){
        list.add(ele);
        upheap(list.size()-1);
    }

    private void upheap(int i){
        if(i==0){
            return;
        }
        int parent = (i-1)/2;
        if(list.get(i).compareTo(list.get(parent))<0) {
            swap(i, parent);
            upheap(parent);
        }
    }

    public  T remove() throws  Exception{
        if(list.isEmpty()){
            throw  new Exception("Removing from an Empty heap");
        }
        T temp = list.get(0);
        T last = list.remove(list.size()-1);
        if(!list.isEmpty()){
            list.set(0,last);
            downHeap(0);
        }
        return temp;
    }

    private void downHeap(int index){
        int min = index;
        int left = left(index);
        int right = right(index);
        if(left<list.size() && list.get(min).compareTo(list.get(left))>0){
            min = left ;
        }
        if(right<list.size() && list.get(min).compareTo(list.get(right))>0){
            min = right;
        }
        if(min!=index){
            swap(min,index);
            downHeap(min);
        }
    }
    // HeapSort
    public  ArrayList<T> heapSort() throws Exception{
        ArrayList<T> data = new ArrayList<>();
        while(!list.isEmpty()){
            data.add(this.remove());
        }
        return data;
//
    }
}


public class Main {

    public static void main(String[] args) throws Exception{
        Heap<Integer> hp = new Heap<>();
        hp.insert(34);
        hp.insert(12);
        hp.insert(32);
        hp.insert(14);
        hp.insert(2);
        hp.insert(4);
            int temp = hp.remove();
            System.out.println(temp);
          ArrayList<Integer> ans = hp.heapSort();
            System.out.println(ans);

    }


}

//package DSA.Heap.Operation;
//
//import java.util.ArrayList;
//
//class Heap<T extends Comparable<T>> {
//    private ArrayList<T> list;
//
//    public Heap() {
//        list = new ArrayList<>();
//    }
//
//    private void swap(int first, int second) {
//        T temp = list.get(first);
//        list.set(first, list.get(second));
//        list.set(second, temp);
//    }
//
//    private int left(int i) {
//        return 2 * i + 1;
//    }
//
//    private int right(int i) {
//        return 2 * i + 2;
//    }
//
//    public void insert(T ele) {
//        list.add(ele);
//        upheap(list.size() - 1);
//    }
//
//    private void upheap(int i) {
//        if (i == 0) return;
//        int parent = (i - 1) / 2;
//        if (list.get(i).compareTo(list.get(parent)) < 0) {
//            swap(i, parent);
//            upheap(parent);
//        }
//    }
//
//    public T remove() throws Exception {
//        if (list.isEmpty()) {
//            throw new Exception("Removing from an Empty heap");
//        }
//        T root = list.get(0);
//        T last = list.remove(list.size() - 1); // Remove the last element
//        if (!list.isEmpty()) {
//            list.set(0, last);  // Replace root with last
//            downHeap(0);        // Restore heap property
//        }
//        return root;
//    }
//
//    private void downHeap(int index) {
//        int min = index;
//        int left = left(index);
//        int right = right(index);
//
//        if (left < list.size() && list.get(min).compareTo(list.get(left)) > 0) {
//            min = left;
//        }
//
//        if (right < list.size() && list.get(min).compareTo(list.get(right)) > 0) {
//            min = right;  // Corrected from `right = min` to `min = right`
//        }
//
//        if (min != index) {
//            swap(index, min);
//            downHeap(min);
//        }
//    }
//
//    public ArrayList<T> heapSort() throws Exception {
//        ArrayList<T> sorted = new ArrayList<>();
//        while (!list.isEmpty()) {
//            sorted.add(this.remove());
//        }
//        return sorted;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        Heap<Integer> hp = new Heap<>();
//        hp.insert(34);
//        hp.insert(12);
//        hp.insert(32);
//        hp.insert(14);
//        hp.insert(2);
//        hp.insert(4);
//
//        System.out.println("Removed root: " + hp.remove());
//
//        ArrayList<Integer> sorted = hp.heapSort();
//        System.out.println("Heap Sorted: " + sorted);
//    }
//}
//
