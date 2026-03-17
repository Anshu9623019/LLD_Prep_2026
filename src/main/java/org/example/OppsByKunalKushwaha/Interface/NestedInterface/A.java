package org.example.OppsByKunalKushwaha.Interface.NestedInterface;

public class A {

    public  interface NestedInterface{
        boolean isOdd(int num);
    }
}

class  B implements A.NestedInterface{
    @Override
    public boolean isOdd(int num) {
        return (num  & 1) == 1;
    }
}

