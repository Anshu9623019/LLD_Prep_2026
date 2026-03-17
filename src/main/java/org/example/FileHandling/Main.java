package org.example.FileHandling;

import org.example.ThreadLearn.ProducerConsumerProblem.Buffer;

import java.io.*;

public class Main {
    public static void main(String[] args) {


        //File create
        try{
            File fo = new File("new-file.txt");
            fo.createNewFile();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        // Write in the file
        try{
            FileWriter fw = new FileWriter("new-file.txt");
            fw.write("I'm a good boy");
            fw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        //Reading from a file
        try(BufferedReader br = new BufferedReader(new FileReader("new-file.txt"))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //delete file
        try{
            File fo = new File("rondom");
            fo.createNewFile();
            if(fo.delete());
        }catch (IOException e){
            System.out.println(e);
        }


    }
}
