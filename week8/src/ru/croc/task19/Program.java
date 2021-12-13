package ru.croc.task19;

import java.io.FileWriter;
import java.io.IOException;

public class Program {
    //В данном случае строка будет печататься/выводиться в файле
    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("//Users//ekaterinasafutina//Desktop//Lab4//Croc_week8//src//ru//croc//task19//test.txt", false))
        {
            // запись всей строки
            String helloWord = "Hello, world!";
            writer.write(helloWord);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
