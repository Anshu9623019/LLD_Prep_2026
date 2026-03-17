package org.example.OppsByKunalKushwaha.Introduction;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Stores 5 roll no
        int []numbers = new int[5];

        // Stores 5 names of student
        String names[] = new String[5];

        //data of 5 students: {roll no, names, marks}
        int []rno = new int[5];
        String[] name = new String[5];
        float[] marks = new float[5];

        //DataTyps to store the info of students
        Student[] students = new Student[5];
        System.out.println(Arrays.toString(students));

        Student anshu = new Student();

        anshu.name = "Anshu";
        System.out.println(anshu.rno);
        System.out.println(anshu.marks);
        anshu.changeName("Kumar");
        anshu.greeting();


        Student ram = new Student(anshu);
        Student shyam = new Student(10,"Shayam",10);
        System.out.println(shyam.name);
        System.out.println(ram.name);

        Student one = new Student();
        Student two = one;
        one.name = "somethig something";
        System.out.println(two.name);

    }

}

//Create Student dataType
class Student {
    int rno;
    String name ;
    float marks;

    Student(){
        //Call constructor inside the constructor
        // internally : new Student(13,"Kumar",23);
        this(13,"Kumar",23);
    }

    Student(Student other){
        this.name = other.name;
        this.rno = other.rno;
        this.marks = other.marks;
    }

    Student(int rno,String name,float marks){
        this.rno = rno;
        this.name = name;
        this.marks = marks;
    }

    void greeting(){
        System.out.println("Hey, Greeting from :"+this.name);
    }

    void changeName(String name){
        this.name = name;
    }
}
