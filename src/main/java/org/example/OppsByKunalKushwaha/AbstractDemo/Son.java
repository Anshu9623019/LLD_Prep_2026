package org.example.OppsByKunalKushwaha.AbstractDemo;

public class Son extends  Parent{

    @Override
    void carrer(String name) {
        System.out.println("Career : "+name);
    }


    @Override
    void partner(String a,int age){
        System.out.println("Partner name : "+a+ " "+ age);
    }
}