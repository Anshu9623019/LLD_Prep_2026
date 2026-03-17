package org.example.FileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    static void input(){
        try(InputStreamReader isr = new InputStreamReader(System.in)){
            System.out.println("Enter some letter");
            int letter = isr.read();

            while(isr.ready()){
                System.out.println((char)letter);
                letter = isr.read();
            }
            // isr.close() //  close the resources, handle by try with resource(AutoClosable interface)
            System.out.println();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        try(FileReader fr = new FileReader("abc.txt")){
            int letter = fr.read();
            while (fr.ready()){
                System.out.println((char)letter);
                letter = fr.read();
            }
            System.out.println();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        //byte to char stream and then reading char stream
        try(BufferedReader br =  new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("you typed :"+ br.readLine());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
