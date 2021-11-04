package ru.croc.task10;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test PeakLoadCounter:");
        String file = "src/ru/croc/task10/file.txt";
        System.out.println("Log file: " + file);
        PeakLoadCounter counter = new PeakLoadCounter(file);
        System.out.println("Peak load = " + counter.getPeakLoad());
    }
}
