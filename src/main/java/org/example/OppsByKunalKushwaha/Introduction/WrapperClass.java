package org.example.OppsByKunalKushwaha.Introduction;

public class WrapperClass {
    public static void main(String[] args) {

//        int a = 10;
//        int b = 20;

        //Wrapper class create object
        Integer num = 40;
        Integer a = 10;
        Integer b = 20;
        System.out.println("a :"+a+"b :" +b);

        // we can change instance variable value but, can't assign to some other object;
        final A anshu = new A("Anshu Kumar");
        anshu.name = "Kumar";
        // anshu = new A("Suraj"); (it will show error)


        A obj ;
        for(int i=0;i<1000000;i++){
            obj = new A("Kumar");
        }
    }
    public void swap(Integer a,Integer b){
        Integer temp = a;
        a = b;
        b = temp;
    }

}

class  A{
    final int val = 10;
    String name;
    A(String name){
        this.name = name;
    }

    @Override
    protected void finalize() throws  Throwable{
        System.out.println("Object is destroyed");
    }
}
