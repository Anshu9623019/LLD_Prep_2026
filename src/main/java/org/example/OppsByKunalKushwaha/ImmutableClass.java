package org.example.OppsByKunalKushwaha;

import org.example.OppsByKunalKushwaha.Enum.MyInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ImmutableClass {
    private final String name;
    private final List<Object> petNameList;

    ImmutableClass(String name,List<Object> petNameList){
        this.name = name;
        this.petNameList = petNameList;
    }

    public String getName(){
        return name;
    }
    public List<Object> getPetNameList(){
        //this is required,cause making list final,
        // means you can not now point it to new list,but still can add, delete values in it.
        // so that why we send the copy of it
        return new ArrayList<>(petNameList);
    }

    public static void main(String[] args) {
        List<Object> petNames = new ArrayList<>();
        petNames.add("Dog");
        petNames.add("Cat");

        ImmutableClass obj = new ImmutableClass("Myname",petNames);
        obj.getPetNameList().add("hello");
        System.out.println(obj.getPetNameList());

    }

}
