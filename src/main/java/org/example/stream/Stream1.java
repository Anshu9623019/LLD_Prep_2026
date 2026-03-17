package org.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;


public class Stream1 {

    public static void main(String args[]){

        //Basic of Stream
        List<Integer> li = new ArrayList<>();
        li.add(1);
        li.add(2);
        li.add(3);
        li.add(4);
        li.add(5);
        li.add(6);

        long count = li.stream().filter((Integer a)->(a>2)).count();

        System.out.println(count);

        //Java 8 --> Minimal code, functional programing main features of JAVA 8.
        // Java 8 -> Lambda expression, Streams, Date and time API;

        // Overview of lambda expression
        // lambda expression is an anonymous functions (no name, no return type, no access modifier)
        // Lambda expression is used to implement functional interface;(only one abstract method will be there);
        //

        Thread t1 = new Thread(()->{
            System.out.println("Hello");
        });
        MathOperation sumOperation = (a,b) -> a + b;
        MathOperation subtractOperation = (a,b) -> a-b;
        int res = sumOperation.operate(1,2);
        System.out.println(res);


        // Predicate :-> Functional Interface is Boolean value function

        Predicate<Integer> isEven = (x) -> x % 2 == 0;
        System.out.println(isEven.test(4));

        Predicate<String> isWordStartWithA = x -> x.toLowerCase().startsWith("A");
        Predicate<String> isWordEndWithT = x -> x.toLowerCase().endsWith("T");

        Predicate<String> and = isWordStartWithA.and(isWordEndWithT);
        System.out.println(and.test("Akshay"));


        //Function :-> work for me , andThen and compose methods are default methods. identity method are static method.
        Function<Integer,Integer> doubleIt = x -> 2 *x;
        Function<Integer,Integer> tripleIt = x -> 3 *x;
        System.out.println(doubleIt.andThen(tripleIt).apply(20));
        System.out.println(doubleIt.compose(tripleIt).apply(20));
        System.out.println(doubleIt.apply(100));

        Function<Integer,Integer> identity = Function.identity();
        Integer res2 = identity.apply(5);
        System.out.println(res2);

        //Consumer :-> Functional Interface
        Consumer<Integer> consumer = (x) -> System.out.println(x);
        consumer.accept(5);

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Consumer<List<Integer>> printList = (x) -> {
            for (int i : x){
                System.out.println(i);
            }
        };
        printList.accept(list);

        //Supplier :- Functional interface used to supply;

        Supplier<String> giveHelloWorld = ()-> "Hello World";
        System.out.println(giveHelloWorld.get());


        // Combine Example

        Predicate<Integer>  predicate = x -> x%2==0;
        Function<Integer,Integer> function = x -> x*x;
        Consumer<Integer> consumer1 = x-> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if(predicate.test(supplier.get())){
            consumer1.accept(function.apply(supplier.get()));
        }

        // BiPredicate, BiConsumer, BiFunction

        BiPredicate<Integer,Integer> isSumEven = (x,y) -> (x+y) %2==0;
        System.out.println(isSumEven.test(5,5));
        BiConsumer<Integer,String> biConsumer = (x,y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        BiFunction<String,String,Integer> biFunction = (x,y) -> (x+y).length();
        System.out.println(biFunction.apply("a","bc"));

        UnaryOperator<Integer> a = x -> 2*x;
        BinaryOperator<Integer> b = (x,y) -> x+y;


        //Method Reference --> use method without invoking & in place of lambda expression;
        List<String>  students = Arrays.asList("Ram","Shyam","Ghanshyam");
        students.forEach(x-> System.out.println(x));
        students.forEach(System.out::println);

        //Constructor Reference
        List<String> names = Arrays.asList("A","B","c");
        List<MobilePhone> mobilePhoneList = names.stream().map(MobilePhone::new).collect(
                Collectors.toList()
        );


    }

}


class MobilePhone{
    String name;

    public MobilePhone(String name){
        this.name = name;
    }
}

//// Funtion interface
@FunctionalInterface
interface MathOperation {
    int operate(int ab,int b);
}
