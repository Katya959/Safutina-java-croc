package ru.croc.task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");
        String s = scanner.nextLine();
        //Считываем строки (числа), записанные через пробел, из строчки
        String[] array = s.split("\\s");
        int numbers[] = new int[array.length];
        //В массив записываем числа, которые были преобразованы из строки
        for (int j = 0; j < numbers.length; j++) {
            numbers[j] = Integer.parseInt(array[j]);
        }
        int min_max = numbers[0];
        int j_min_max = 0;
        int k;
        //перемещение минимального элемента в начало массива
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min_max) {
                min_max = numbers[i];
                j_min_max = i;
            }
        }
        k = numbers[0];
        numbers[0] = numbers[j_min_max];
        numbers[j_min_max] = k;
        //перемещение максимального элемента в начало массива
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > min_max) {
                min_max = numbers[i];
                j_min_max = i;
            }
        }
        k = numbers[numbers.length-1];
        numbers[numbers.length-1] = numbers[j_min_max];
        numbers[j_min_max] = k;

        System.out.print("Результирующий массив: ");
        for (int i = 0; i < numbers.length-1; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println(numbers[numbers.length-1]);
    }
}
