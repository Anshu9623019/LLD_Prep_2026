package org.example.FileHandling;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.InflaterInputStream;

public class StreamReader {

    public static void main(String[] args) {
        try(InputStreamReader sr = new InputStreamReader(System.in)){
            System.out.print("Please enter a word :");
            int letter = sr.read();
            while(sr.ready()){
                System.out.println((char)letter);
                letter = sr.read();
            }
            sr.close();
            System.out.println();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
