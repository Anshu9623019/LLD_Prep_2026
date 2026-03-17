package org.example.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InterviewQuestions {
    public static void main(String[] args) {
//        Find the k most frequent elements in a list
        int k = 3;
        List<Integer> li = Arrays.asList(1,3,4,1,4,3,1,1,3);
        List<String> words = Arrays.asList("apple", "ball", "ball", "cat", "cat","cat", "elephant");

        Map<Integer,List<Integer>> ansLis1 = li.stream().collect(Collectors.groupingBy(key->key));
        System.out.println(ansLis1);
        Map<String,Long> ansLis2 = words.stream().collect(Collectors.groupingBy(ele->ele,Collectors.counting()));
        System.out.println(ansLis2);
        Map<Integer,List<String>> li1 = words.stream().collect(Collectors.groupingBy(String::length));
        Map<Character,List<String>> groupByFirstLetter = words.stream().collect(Collectors.groupingBy(word->word.charAt(0)));
        System.out.println(groupByFirstLetter);

        Map<String,Long> sortedMapByValue = ansLis2.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e2,
                        LinkedHashMap::new
                )
        );
        Map<String,Long> sortedMapByKey = ansLis2.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e2,
                        LinkedHashMap::new
                )
        );
        //reversed order
        Map<String,Long> sortedMapByKeyReversed = ansLis2.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByKey().reversed()).collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e2,
                        LinkedHashMap::new
                )
        );

//        Map.Entry::getKey → use the entry’s key ("apple", "banana", etc).
//
//        Map.Entry::getValue → use the entry’s value (counts like 1, 2, 3).
//
//        (e1, e2) -> e1 → merge function. If duplicate keys appear (shouldn’t happen here), keep the first one.
//
//        LinkedHashMap::new → use a LinkedHashMap instead of normal HashMap, because HashMap does not guarantee order, but LinkedHashMap preserves insertion order.
//
//👉 This is critical: Without LinkedHashMap, your map would not stay sorted after collection.


        System.out.println(sortedMapByValue);
        System.out.println(sortedMapByKey);
        System.out.println(sortedMapByKeyReversed);


//        ✅ Example 4: Group by and then map to another value (using mapping)

             Map<Integer,Set<Character>> mpIntoSetByLength = words.stream().collect(Collectors.groupingBy(
                     String::length,
                     Collectors.mapping(word->word.charAt(0),Collectors.toSet())
             ));

        System.out.println("set group"+mpIntoSetByLength);

//        Group by and find max/min in each group
        List<String> words1 = Arrays.asList("apple", "balls", "bat", "cars", "cat","cat", "elephant");
        Map<Character,Optional<String>> result = words1.stream().collect(Collectors.groupingBy(
                word->word.charAt(0),
                Collectors.maxBy(Comparator.naturalOrder())
        ));

        System.out.println(result.get('a').get());

//        👉 If you want to remove Optional and directly store String instead, you can use collectingAndThen:
//
//        Map<Character, String> result = words1.stream()
//                .collect(Collectors.groupingBy(
//                        word -> word.charAt(0),
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.naturalOrder()),
//                                Optional::get
//                        )
//                ));


        Map<Integer, Map<Character, List<String>>> grouped =
                words.stream().collect(Collectors.groupingBy(
                        String::length,                        // 1st level: by length
                        Collectors.groupingBy(word -> word.charAt(0)) // 2nd level: by first char
                ));

        System.out.println(grouped);

//        By default, groupingBy returns a HashMap.
//        You can change it to TreeMap or LinkedHashMap:
//
        Map<Integer, List<String>> grouped1 = words.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        TreeMap::new,        // custom map type
                        Collectors.toList()
                ));

        System.out.println(grouped1);
//
//
//        Output (sorted by key because TreeMap):
//
//        {3=[bat, cat, dog], 4=[ball], 5=[apple], 8=[elephant]}

//        11. Find common elements from two lists using streams
        List<Integer> list1 = Arrays.asList(1,2,3,1,4,7);
        List<Integer> list2 = Arrays.asList(9,0,3,8,4,3);
        List<Integer> common = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());

        System.out.println(common);

//        13. Calculate the product and sum of all integers in a list
        int product = list1.stream()
                .reduce(1, (a, b) -> a * b);
        int sum = list1.stream()
                .reduce(1, Integer::sum);
        System.out.println(product+" "+ sum);

////        14. Partition students based on pass/fail using score > 40
//
//        Map<Boolean, List<Student>> result = students.stream()
//                .collect(Collectors.partitioningBy(s -> s.getScore() > 40));



        List<String> list = Arrays.asList("ABC", "DE", "XYZ");

        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        System.out.println(map);

//        ⚠️ Important: What if there are duplicates?
//                If the list contains duplicates (e.g., ["ABC", "DE", "ABC"]), then this code will throw:
//
//        java.lang.IllegalStateException: Duplicate key
//        Because toMap by default doesn’t know how to merge values.
//✅ To fix this, we can provide a merge function:


        Map<String, Integer> map1 = list.stream()
                .collect(Collectors.toMap(
                        Function.identity(),     // key = string
                        String::length,          // value = length
                        (oldVal, newVal) -> oldVal  // merge function: keep old value
                ));

//        15. Sort a list of employees by department then salary descending
//        List<Employee> sorted = employees.stream()
//                .sorted(Comparator.comparing(Employee::getDepartment)
//                        .thenComparing(Employee::getSalary, Comparator.reverseOrder()))
//                .collect(Collectors.toList());

//        16. Get all manager names reporting to a particular department
//        List<String> managers = employees.stream()
//                .filter(e -> e.getDepartment().equals("Sales") && e.getRole().equals("Manager"))
//                .map(Employee::getName)
//                .collect(Collectors.toList());

//        17. Group books by author and then by genre
//        Map<String, Map<String, List<Book>>> grouped = books.stream()
//                .collect(Collectors.groupingBy(Book::getAuthor,
//                        Collectors.groupingBy(Book::getGenre)));

//        18. Count files by file extension
//        Map<String, Long> countByExt = files.stream()
//                .collect(Collectors.groupingBy(f -> getExtension(f.getName()),
//                        Collectors.counting()));

//        19. Find customers with more than 3 purchases
//        List<Customer> loyal = purchases.stream()
//                .collect(Collectors.groupingBy(Purchase::getCustomer, Collectors.counting()))
//                .entrySet().stream()
//                .filter(e -> e.getValue() > 3)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

//        20. Convert a list of dates to strings in dd-MM-yyyy format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        List<String> formatted = dates.stream()
//                .map(d -> d.format(formatter))
//                .collect(Collectors.toList());


//        21. Filter out non-prime numbers using streams
//        List<Integer> primes = list.stream()
//                .filter(n -> IntStream.rangeClosed(2, (int)Math.sqrt(n))
//                        .allMatch(i -> n % i != 0))
//                .collect(Collectors.toList());

//        22. Find the longest string in a list
//        String longest = list.stream()
//                .max(Comparator.comparingInt(String::length))
//                .orElse("");

//        24. Find all palindromes in a list
//        List<String> palindromes = list.stream()
//                .filter(s -> s.equalsIgnoreCase(new StringBuilder(s).reverse().toString()))
//                .collect(Collectors.toList());

//        25. Group employees by city and then by team size
//        Map<String, Map<Integer, List<Employee>>> grouped = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getCity,
//                        Collectors.groupingBy(e -> e.getTeam().size())));

//        26. Normalize and deduplicate emails
//        List<String> cleanEmails = emails.stream()
//                .map(String::toLowerCase)
//                .map(e -> e.replaceAll("\s", ""))
//                .distinct()
//                .collect(Collectors.toList());

//        27. Get all students whose names start and end with vowels
//        List<String> result = students.stream()
//                .map(Student::getName)
//                .filter(n -> n.matches("(?i)^[aeiou].*[aeiou]$"))
//                .collect(Collectors.toList());

//        28. Sum the values of a nested list structure
//        int sum = nestedList.stream()
//                .flatMap(Collection::stream)
//                .mapToInt(Integer::intValue)
//                .sum();

//        29. Get the earliest and latest transaction dates
//        Optional<LocalDate> min = transactions.stream()
//                .map(Transaction::getDate)
//                .min(LocalDate::compareTo);
//        Optional<LocalDate> max = transactions.stream()
//                .map(Transaction::getDate)
//                .max(LocalDate::compareTo);

//        Convert list of objects to comma-separated values for a field
//        String csv = objects.stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));

        List<Integer> list3 = Arrays.asList(123, 456, 112, 789, 12);

        Map<Integer, Long> digitCount = list3.stream()
                // Convert each number -> String -> Stream of characters
                .flatMap(n -> String.valueOf(n)
                        .chars()                 // IntStream of char codes
                        .mapToObj(c -> c - '0') // convert char -> actual digit
                )
                // Group digits and count occurrences
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(digitCount);
//        {1=4, 2=3, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1}


//        Partition strings by length: short (<5), medium (5-10), long (>10)
        List<String> list4 = Arrays.asList("hi", "world", "openai", "chatgpt", "extraordinary");

        Map<String, List<String>> partitioned = list4.stream()
                .collect(Collectors.groupingBy(
                        s -> s.length() < 5 ? "short" : s.length() <= 10 ? "medium" : "long"
                ));

        System.out.println(partitioned);

//        34. Find repeating characters in a string
//        Map<Character, Long> freq = str.chars()
//                .mapToObj(c -> (char)c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        List<Character> repeating = freq.entrySet().stream()
//                .filter(e -> e.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

//        40. Find the top 3 longest words in a paragraph
//        List<String> top3Words = Arrays.stream(paragraph.split("\s+"))
//                .sorted(Comparator.comparingInt(String::length).reversed())
//                .limit(3)
//                .collect(Collectors.toList());

        List<String> names = Arrays.asList("Alice", "Arnold", "Bob", "Brandon", "Charlie");

        Map<Character, String> map2 = names.stream()
                .collect(Collectors.groupingBy(
                        n -> n.charAt(0),
                        Collectors.mapping(Function.identity(), Collectors.joining(", "))
                ));

        System.out.println(map2);

//        44. Find the second most frequent element
//        String secondMost = list.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .skip(1).findFirst().map(Map.Entry::getKey).orElse(null);
//
//        45. Identify anagrams in a list of strings
//        Map<String, List<String>> anagrams = list.stream()
//                .collect(Collectors.groupingBy(s -> s.chars().sorted()
//                        .collect(StringBuilder::new, StringBuilder::appendCodePoint,
//                                StringBuilder::append).toString()));

//        47. Get all possible pairs of two lists using flatMap
//        List<String> pairs = list1.stream()
//                .flatMap(a -> list2.stream().map(b -> a + "-" + b))
//                .collect(Collectors.toList());
//
//        8. Group employees by age range buckets (20-29, 30-39...)
//        Map<String, List<Employee>> grouped = employees.stream()
//                .collect(Collectors.groupingBy(e -> {
//                    int age = e.getAge();
//                    return (age / 10) * 10 + "-" + ((age / 10) * 10 + 9);
//                }));


//        49. Extract hashtags from a list of tweets
//        List<String> hashtags = tweets.stream()
//                .flatMap(tweet -> Arrays.stream(tweet.split(" ")))
//                .filter(word -> word.startsWith("#"))
//                .distinct()
//                .collect(Collectors.toList());


//        50. Remove consecutive duplicates from a list using streams
//        List<Integer> result = IntStream.range(0, list.size())
//                .filter(i -> i == 0 || !list.get(i).equals(list.get(i - 1)))
//                .mapToObj(list::get)
//                .collect(Collectors.toList());


    }
}
