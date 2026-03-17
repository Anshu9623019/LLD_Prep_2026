package org.example.ExceptionHandling;

public class CustomException extends Exception{

    CustomException(String message){
        super(message);
    }

    public void method(){
        try{
           throw  new CustomException("Custome Exception");
        }catch (CustomException e){
            System.out.println(e);
        }
    }

        public static void main(String[] args) throws CustomException {
            CustomException obj = new CustomException("some Issue");
            obj.method();
            new CustomException("new issue");
        }
}
