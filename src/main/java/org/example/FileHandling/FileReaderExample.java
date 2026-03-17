package org.example.FileHandling;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import org.example.FileHandling.*;
public class FileReaderExample {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("abcd.txt")) {
            int letter = fr.read();
            while (fr.ready()){
                System.out.println((char)letter );
                letter = fr.read();
            }
            fr.close();
            System.out.println();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //byte to char stream the reading char stream
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ){
            System.out.println("you types :" + br.readLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try(BufferedReader br = new BufferedReader(new FileReader("abc.txt"))){
            while (br.ready()){
                System.out.println(br.readLine());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
