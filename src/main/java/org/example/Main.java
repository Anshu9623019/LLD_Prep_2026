package org.example;

import org.example.ThreadLearn.MultiThreadingLearning;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        Student s1 = new Student("Basant",108);
        Student s2 = new Student("ABC",108);
        Student s3 = new Student("Prakash",105);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        Collections.sort(students,new IdComparotor());
        System.out.println(students);

    }
}