package org.example;

public class Student implements  Comparable<Student> {
    String name;
    int id;
    Student(String name,int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Student o) {
        if(id==o.id){
            return 1;
        }else if(id>o.id){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Student {" + "id=" + id + ",name =" + name + '\'' + '}';
    }
}
