package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CustomArrayList extends ArrayList {

    @Override
    public boolean add(Object o) {

        if(this.contains(o)){
            return true;
        }else{
            return super.add(o);
        }
    }

    public static void main(String []args){
        CustomArrayList list1 = new CustomArrayList();

        list1.add(1);
        list1.add(1);
        list1.add(2);
        list1.add(2);

        System.out.println(list1);

        Set<String> set1 = new HashSet<>();
        set1.add("abc");
        set1.add("abc");
        System.out.println(set1);

        //Set implementation internally uses map to store key object;
        //Set implementation not all follow this rule to understand this follow example below

        Set<Student> set = new HashSet<>();
        Student s1 = new Student("abc",1);
        Student s2 = new Student("abc",2);
        Student s3 = new Student("abc",1);
        Student s4 = new Student("abc",3);

        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);

        for(Student st2 : set){
            System.out.println(st2.name);
        }

    }

}
