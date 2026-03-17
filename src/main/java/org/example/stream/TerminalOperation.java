package org.example.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperation {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(100,200,300,200,400,33);

        // 1. Collect (Both are termination operations);
        list.stream().skip(1).collect(Collectors.toList());
        list.stream().skip(1).toList();

        //2 2. For each
        list.stream().forEach(x-> System.out.println(x));

        // reduce : Combines elements to produce a single result
        Optional<Integer> optionalInteger = list.stream().reduce((x,y)->x+y);
        System.out.println(optionalInteger.get());

        // 4 . count

        // 5. anyMatch, allMatch and nonMatch
        boolean b = list.stream().anyMatch(x->x%2==0);

        System.out.println(b);
        System.out.println(list.stream().allMatch(x->x>0));
        System.out.println(list.stream().noneMatch(x->x<0));

        //6. FindFirst, FindAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());


        //7. toArray()

        Object[] array = Stream.of(1,2,3).toArray();

        //8. min/max
        System.out.println("max :" + Stream.of(2,4,69).max(Comparator.naturalOrder()));
        System.out.println("max :" + Stream.of(2,4,69).min(Comparator.naturalOrder()));

        //9. foreachOrdered
        list.parallelStream().forEachOrdered(System.out::println);


        //Example
        List<String> names = Arrays.asList("anna","bon","Charile","david");
        System.out.println(names.stream().filter(x->x.length()>3).toList());

        //Example : Squaring and sorting Names
        List<Integer> numbers = Arrays.asList(10,2,5,6,1);
        System.out.println(numbers.stream().map(x->x*x).sorted().toList());

        //Exam : Summing values
        System.out.println(numbers.stream().reduce(Integer :: sum).get());


        //Example : counting occurrence of character

        String sentence = "Hello World";
        System.out.println(sentence.chars().filter(x->x=='l').count());

        //State full & stateless. map is stateless operation and sorted is state full operations

        // intermediate step runs only after the termination operation has been added/executed.

        List<String> names1 = Arrays.asList("Alicre","Bob","Charlie","David");

        Stream<String> stream = names1.stream().filter(
                n->{
                    System.out.println("Filtering : " + n);
                    return n.length()>3;
                }
        );

        System.out.println("Before terminal operations");
        List<String> result = stream.collect(Collectors.toList());
        System.out.println("After Terminal operation");
        System.out.println(result);

        //Example
        Stream<String> stream1 = names1.stream();
        stream1.forEach(System.out::println);

        // stream has already been terminated and again it is being used.
        List<String> list2 = stream1.map(String::toUpperCase).toList();

    }
}
