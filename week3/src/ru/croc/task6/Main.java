package ru.croc.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String source = "/*\n" + "*My first ever program in Java!\n" + "*/\n" +
                    "class Hello { //class body starts here" + "\n" +
                    "/* main method */\n" +
                    "  public static void main(String[] args/* we put command line arguments here*/) {\n" +
                    "    // this line prints my first greeting to the screen\n" +
                    "    System.out.println(\"Hi!\"); // :)\n" +
                    "  }\n" +
                    "} // the end\n" +
                    "// to be continued...\n";
        //Чтобы можно было в скобках написать "Hi" в кавычках, мы ставим перед кавычками одинарный слэш.
        //\n ставим, чтобы обозначить, что на этом символе заканчивается строка, записываем следующую строку.

        String noComments = removeJavaComments(source);
        System.out.println("Получившийся результат:");
        System.out.print(noComments);
    }

    public static String removeJavaComments(String s) {
        String s1 = s;
        s1 = s1.replaceAll("/\\*(.|[\\r\\n])*?\\*/", ""); //соответствует многострочным комментариям
        s1 = s1.replaceAll("//.*?\n","\n");     //соответствует строкам, начинающимся с // (regex: //.*?\n)
        //Удаление всех пустых строк до первой строки, имеющей символы в noComments (чтобы не было пустых строк в начале).
        //Таких строк может быть несколько пустых (один многострочный комментарий, который зхаменяется, даст одну пустую строку).
        //С начала строки убираем все пробелы, заменяя их на пустую строку.
        s1 = s1.replaceAll("^[\\s]+", "");
        return s1;
    }
}
