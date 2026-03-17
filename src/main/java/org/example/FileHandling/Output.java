package org.example.FileHandling;

import java.io.*;

public class Output {
        static void output(){
            OutputStream os = System.out;

            try(OutputStreamWriter osw = new OutputStreamWriter(System.out)){
                osw.write("Hello world");
                osw.write(97);
                osw.write(10);
                osw.write('A');
                osw.write('\n');
                char[] arr = "hello world".toCharArray();
                osw.write(arr);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            try(FileWriter fw = new FileWriter("abcd.txt",true)){
                fw.write("this should be appended");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("abcd.txt"))){
                bw.write("Hare Krishna");
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
}
