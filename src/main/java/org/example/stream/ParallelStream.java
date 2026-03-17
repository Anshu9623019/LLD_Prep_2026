package org.example.stream;

import com.sun.source.doctree.EscapeTree;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        //A type of stream that enables parallel processing of elements
        //Allowing multiple thread to process parts of the stream simultaneously
        //This can significantly improve performance for large dataset
        //workload is distributed across multiple thread.
        long startTime = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1,x->x+1).limit(2000).toList();
        List<Long> factorialsList = list.stream().map(ParallelStream::factorial).toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with sequential stream :"+(endTime-startTime)+"ms");


        startTime = System.currentTimeMillis();
        factorialsList = list.parallelStream().map(ParallelStream::factorial).sequential().toList();
        factorialsList = list.parallelStream().map(ParallelStream::factorial).toList();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken with parallel stream :" + (endTime-startTime) + "ms");

        // Parallel streams are most effective for CPU-intensive or large datasets where tasks are independent
        // They may add overhead for single tasks or small datasets

        // Cumulative Sum
        //[1,2,3,4,5] --> [1,3,6,10,15]

        List<Integer> number = Arrays.asList(1,2,3,4,5);
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> cumulativeSum = number.stream().map(sum::addAndGet).toList();
        System.out.println("Expected cumulative sum : [1,3,6,10,15)");
        System.out.println("Actual result with parallel stream :"+cumulativeSum);

    }
    private static long factorial(int n){
        long res = 1;
        while(n>0){
            res *=n;
            n--;
        }
        return res;
    }
}
