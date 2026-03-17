package org.example.Optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> op = Optional.of("ram"); // if we can put null value here, it will give NullPointerException
        System.out.println(op.get());
        System.out.println(op.isEmpty());
        System.out.println(op.isPresent());
        Optional<String> opt1 = Optional.ofNullable("Shyam"); // we can put null value here, it won't return NullPointerException
        op.ifPresent((n)-> System.out.println(n));

        Optional<String> opt2 = Optional.empty();
        System.out.println(opt2.isPresent());
        opt1.ifPresentOrElse((n)-> System.out.println(n),()-> System.out.println("not present"));

        Optional<String> opt3 =  Optional.ofNullable(null);
        System.out.println(opt3.orElse("Default"));
        System.out.println(opt3.orElseGet(()-> "Anshu Kumar default value"));
        System.out.println(op.map(String::length).orElse(0));
        System.out.println(op.filter((s)->s.startsWith("r")).isPresent());

//        1. What's the difference between orElse() and orElseGet() ?
//        2. Can Optional.get() throw exceptions? When? Optional.get() throws NoSuchElementException if the value is not present.
//        3. When would you prefer Optional over traditional null checks?
//        4. Can Optional replace every null ? Why or why not?
//        5. How is flatMap() different from map() in Optional?
        Optional<String> name = Optional.of("Anshu");

// map returns Optional<Optional<Integer>>
        Optional<Optional<Integer>> mapExample = name.map(n -> Optional.of(n.length()));

// flatMap returns Optional<Integer>
        Optional<Integer> flatMapExample = name.flatMap(n -> Optional.of(n.length()));
//        6. Why is Optional not Serializable?

    }
}
