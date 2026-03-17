package org.example.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {

    public static void main(String[] args) {
        // Collectors is a utility class
        // Provides a set of methods to create common collectors
        // 1.
        List<String> names = Arrays.asList("Alice","Bob","Charlie");
        List<String> res = names.stream()
                .filter(name->name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(res);

        //2. Collecting to set
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7);
        Set<Integer> set = nums.stream().collect(Collectors.toSet());
        System.out.println(set);

        //3. Collecting to a Specific collection
        ArrayDeque<String> collect = names.stream().collect(Collectors.toCollection(()->new ArrayDeque<>()));

        // 4. Joining Strings
        // Concatenates stream into single string
        String concatenatesNames = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(concatenatesNames);

        //Summarizing Data
        // Generates statistical summary (count, sum,min,average,max)

        List<Integer> numbers = Arrays.asList(2,3,4,6,7,3);
        IntSummaryStatistics collect1 = numbers.stream().collect(Collectors.summarizingInt(x -> x));

        System.out.println("Count :" +collect1.getCount());
        System.out.println("Sum :" +collect1.getSum());
        System.out.println("Min :" +collect1.getMin());
        System.out.println("Average :" +collect1.getAverage());
        System.out.println("Max :" +collect1.getMax());

        // 6. Calculating Averages
        Double average = numbers.stream().collect(Collectors.averagingInt(x->x));
        System.out.println(average);

        //7. Counting

        Long collect2 = numbers.stream().collect(Collectors.counting());
        System.out.println(collect2);

        //8. Grouping Elements
        List<String> words = Arrays.asList("hello","world","java","stream","collecting");
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length,Collectors.joining(" ,"))));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length,Collectors.counting())));
        TreeMap<Integer,Long> treeMap = words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println(treeMap);

        // 9. Partitioning Elements
        // Partitions elements into two group(true and false) based on a predicate

        System.out.println(words.stream().collect(Collectors.partitioningBy(x->x.length()>5)));

        //10. Mapping and collecting
        //Applies a mapping function before collecting
        System.out.println(words.stream().collect(Collectors.mapping(x->x.toUpperCase(),Collectors.toList())));



        //Example 1 : Collecting words occuranace
        String sentences = "Hello world hello java world";
        System.out.println(Arrays.stream(sentences.split(" ")).collect(Collectors.groupingBy(String::length,Collectors.counting())));

        //Example 3 : Partitioning Even and Odd Numbers
        List<Integer> l2 = Arrays.asList(1,2,3,4,6);
        l2.stream().collect(Collectors.partitioningBy(x->x%2==0));

        //Example : summing values in a map
        Map<String,Integer> items = new HashMap<>();
        items.put("Apple",10);
        items.put("Banana",20);
        items.put("Oranges",30);

        System.out.println(items.values().stream().reduce(Integer::sum));
        System.out.println(items.values().stream().collect(Collectors.summingInt(x->x)));


        //Eaxmple 5 : Creating a map from Stream Element
        List<String> fruits = Arrays.asList("apple","banana","cherry");
        System.out.println(fruits.stream().collect(Collectors.toMap(x->x.toUpperCase(),x->x.length())));

        //Example 6.
        List<String> words1 = Arrays.asList("apple","banana","apple","orange","banana","apple");

        System.out.println(words1.stream().collect(Collectors.toMap(k->k,v->1,(x,y)->x+y)));

    }
}
