package org.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperation {

    public static void main(String[] args) {
        //Intermediate operation transform one stream in to another stream.
        // They are lazy, meaning they don't execute until a terminal operation is invoked.

        //1 . Filters
        List<String> list = Arrays.asList("Akshit","Ram","Shyam","Ghanshyam");
        long cnt = list.stream().filter(x->x.startsWith("A")).count();
        System.out.println(cnt);

        //Map
        Stream<String> stringStream = list.stream().map(String::toUpperCase);

        // 3. Sorted
        Stream<String> sortedStream = list.stream().sorted();
        Stream<String> sortedStreamByComparaor = list.stream().sorted(( a, b)->a.length()- b.length());

        // 4. Distinct
        Stream<String> distinct = list.stream().filter(a->a.startsWith("A")).distinct();

        // 5. Limit

        System.out.println(Stream.iterate(1, x->x+1).limit(100));

        // 6. skip
        System.out.println(Stream.iterate(10,x->x+10).skip(10).limit(100));

        //7. peak same like but a terminal operator.
        // Perform an action on each element as it is consumed
        Stream.iterate(1,x-> x+1).skip(10).limit(100).peek(System.out::println);

        // 8 FlatMap
        // Handle Streams of collections, lists or Arrays where each element is itself a collection
        //Flatten nested structure(e.g lists within lists) so that they can be  processed as a
        // Transform and flatten element are the same  time.

        List<List<String>> listsOfLists = Arrays.asList(
                Arrays.asList("Apple","banana"),
                Arrays.asList("orange","Kiwi"),
                Arrays.asList("orange","kiwi")
        );

        System.out.println(listsOfLists.get(1).get(1));

        System.out.println(listsOfLists.stream().flatMap(x->x.stream()).map(String::toUpperCase).toList());

        List<String> sentences = Arrays.asList("Hello world","Java stream are powerful","Flatmap is useful");
        System.out.println(sentences.stream()
                .flatMap(sentence->Arrays.stream(sentence.split(" "))).
                map(String::toUpperCase)
                .toList()
        );

    }
}
