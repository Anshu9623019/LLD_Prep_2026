package org.example.stream;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> stream = li.stream();

        System.out.println(stream.filter((n) -> n % 2 == 0).collect(Collectors.toList()));

        List<String> list2 = Arrays.asList("Ram", "shyam", "Kumar", "hari");

        Stream<String> stream1 = list2.stream().sorted((b, a) -> a.length() - b.length());
        System.out.println(stream1.toList());

        Collections.sort(list2, (a, b) -> a.compareTo(b));

        // Chain Operation

        Function<Integer, Integer> add = (a) -> a + a;

        Function<Integer, Integer> mul = (a) -> a * a;

        Function<Integer, Integer> chained = add.andThen(mul);
        int ans = chained.apply(2);
        System.out.println(ans);

        Map<Integer, List<String>> group = list2.stream().collect(Collectors.groupingBy(s -> s.length()));

        System.out.println(group);


        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("ram", 10000));
        employeeList.add(new Employee("ram", 103300));
        employeeList.add(new Employee("Rahul", 10330));
        employeeList.add(new Employee("Akshay", 100120));

        Function<Employee, String> ans1 = (emp) ->{
        if (emp.salary > 50000) {
            return emp.name;
        }
        return  "Salary less than 5ok";
    };
        String name = ans1.apply(new Employee("ram",100000));
        System.out.println(name);

        BiPredicate<String,String> checkEmail = (em1,em2)-> em1.equals(em2.toLowerCase());

        Boolean ans3 = checkEmail.test("abc@123","abc@163");
        System.out.println(ans3);

        List<Integer> collect1 = li.stream().map(n->n*2).toList();
        System.out.println(collect1);

        String name1 = "anshu kumar";

        Stream<Character> stream2 ;

        BinaryOperator<Integer> bi = (a,b) -> a*b;

        int ans4  = li.stream().reduce(10,bi);
        System.out.println(ans4);

        Function<String,String> tream = (s)-> s.trim();
        Function<String,String>  upercase = (s)-> s.toUpperCase();

        String ans5 = tream.andThen(upercase).apply("   anshu kumar");
        System.out.println(ans5);


        List<String> names = Arrays.asList("Anshu", "Kumar");
        List<Integer> mapped = names.stream()
                .map(String::length)          // Stream<Integer>
                .collect(Collectors.toList());

        List<Character> flatMapped = names.stream()
                .flatMap(n -> n.chars().mapToObj(c -> (char)c))
                .collect(Collectors.toList());
        System.out.println(flatMapped);

        String abc = "anshb kumar";

        System.out.println(Arrays.stream(abc.split(" ")).map(String::toUpperCase).toList());

        // Find the second highest salary
         List<Integer> salaries = Arrays.asList(100,200,123,111,211);

         Optional<Integer> op =   salaries.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        System.out.println(op.get());


        //GroupBy employee by department


    }

}
