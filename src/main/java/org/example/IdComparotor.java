package org.example;

import java.util.Comparator;

public class IdComparotor implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        if(s1.id== s2.id){
            return s1.name.compareTo(s2.name);
        }else if(s1.id> s2.id){
            return 1;
        }else{
            return -1;
        }
    }
}
