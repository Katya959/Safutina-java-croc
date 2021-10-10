package ru.croc.task2;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите количество байт: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.print("Результат: ");
        printBytes(s);
    }

    private static void printBytes(String s) {
        Double s_d = Double.parseDouble(s);
        int n = 1;
        while (s_d >= 1024) {
            n = n + 1;
            s_d = s_d / 1024;
        }
        switch (n) {
            case 1:
                String formatted = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted + " B");
                break;
            case 2:
                String formatted2 = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted2 + " KB");
                break;
            case 3:
                String formatted3 = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted3 + " MB");
                break;
            case 4:
                String formatted4 = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted4 + " GB");
                break;
            case 5:
                String formatted5 = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted5 + " TB");
                break;
            case 6:
                String formatted6 = String.format("%.1f", s_d).replace(',', '.');
                System.out.println(formatted6 + " PB");
                break;
        }
    }
}
