package org.example.ExceptionHandling;

public class Exception1 {

    public void method3(){
        method2();
    }
    public void method2(){
            method1();
    }
    //Runtime exceptions
    public void method1() {
        int a = 3/0;
        // throw new ArithmeticException();
    }

    public static void main(String[] args) {
        Exception1 obj = new Exception1();
//        obj.method3();
        System.out.println("Hello");

        //Class cast exceptions
        Object val = 0;
        System.out.println((String)val);

        //Array Index out of Box exception
        int []arr  = new int[10];
        System.out.println(arr[11]);

        //String Index out of bound Exception
        String s = "ram";
        System.out.println(s.charAt(4));

        //Null pointer Exception
        String val1 = null;
        System.out.println(val1.charAt(0));

        //NumberFormat exceptions
        int val2 = Integer.parseInt("abc"); // "abc" would have number then, I would have run fine.


    }
}
