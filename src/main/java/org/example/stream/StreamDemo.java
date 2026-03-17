package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        // Feature introduce in Java 8
        // Process collections of data in a functional and declarative manner
        // Simplify data Processing
        // Functional Programing
        // Improve Easy Parallelism

        //What is streams?
        // A sequence of elements supporting functional and declarative programing

        // How to use Streams?
        // Source, Intermediate operation and terminal operation

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        System.out.println(numbers.stream().filter(x->x%2==0).count());

        // Different ways to crate stream

        // 1. From Collection
        List<Integer> salaryList = Arrays.asList(1000,300,4320,5000);
        Stream<Integer> stream1 =  salaryList.stream();

        // 2. From Array
        Integer[] salaryArray = {300,400,200,1000,400};
        Stream<Integer> streamFromIntegerList = Arrays.stream(salaryArray);

        // From Static Method
        Stream<Integer> streamFromStaticMethod = Stream.of(10000,3500,400,500);

        //From Stream Builder
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(100).add(30000).add(35000);
        // Form Stream Iterate
        Stream<Integer> streamFromIterate = Stream.iterate(1000,(Integer n)-> n + 5000).limit(5);
        //infinite Stream
        Stream<Integer> generate = Stream.generate(()->1);
        List<Integer> collect = Stream.iterate(1,x->x+1).limit(100).collect(Collectors.toList());
        System.out.println(collect);
    }
}
