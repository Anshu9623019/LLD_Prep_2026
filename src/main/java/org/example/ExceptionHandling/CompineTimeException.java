package org.example.ExceptionHandling;

public class CompineTimeException {

    public void method() throws ClassNotFoundException{
       throw  new ClassNotFoundException();
    }
    public void method1(){
        try{
            method();
        }catch (ClassNotFoundException e){
            //Handle this exception scenario like logging
            e.printStackTrace();
            System.out.println(e);
        }

        try{
            method3();
        }catch (ClassNotFoundException e){

        }catch (InterruptedException e) {

        }
//        }catch (FileNotFoundException e){ // this will throws error as is try block not throwing this exception
//
//        }
//        catch (Exception e){ // this will hande all type of exception. we always keep this in the end
//
//        }
//        catch (ClassNotFoundException | InterruptedException e){  // if any of given exception comes then they will be able to handle this
//
//        }

        finally {
            System.out.println("Inside finally"); // Even if we get return from the try block, even in that case it get executed. it is used to close the file.
        }

    }

    public void method3() throws  ClassNotFoundException, InterruptedException{

    }
    public static void main(String[] args) throws ClassNotFoundException {
        CompineTimeException compineTimeException = new CompineTimeException();
        compineTimeException.method1();
    }
}
