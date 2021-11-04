package ru.croc.task8;

import java.io.*;
import java.util.StringTokenizer;

public class CountWords {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length != 1) {
             throw new IllegalArgumentException("Usage: program expect 1 file path argument");
        }
        String file = args[0];
        System.out.println("[in]");
        System.out.println(outputFromFile(file));
        System.out.println("[out]");
        System.out.println(countWordsInFile(file));
    }

    //Метод для вывода содержимого файла
    public static String outputFromFile(String file) {
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                s += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    //Здесь непосредственно подсчитываем количество слов в файле
    public static long countWordsInFile(String file) {
        //Количество слов в файле
        long numberOfWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line = reader.readLine()) != null) {
                //Из строчки с помощью регулярного выражения получаем слова, записываем их в массив
                String[] word=line.split("\\s+");
                //Количество слов в массиве - количество слов в строке
                numberOfWords += word.length;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File \"" + file + "\" not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfWords;
    }

}
