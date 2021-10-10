package ru.croc.task1;
import java.util.Scanner;

public class Main {
    static class Point {
        double x;
        double y;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //В данной задаче использовала nextDouble, а не nextInt, потому что в классе Point
        //у нас заявлены переменные с типом double, поэтому могут вводиться не только целые
        //числа (при nextDouble программа с ними будет правильно работать), но и
        //числа с типом double (через запятую с дробной частью).
        Point a = new Point();
        System.out.print("Введите координату x вершины №1: ");
        a.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №1: ");
        a.y = scanner.nextDouble();

        Point b = new Point();
        System.out.print("Введите координату x вершины №2: ");
        b.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №2: ");
        b.y = scanner.nextDouble();

        Point c = new Point();
        System.out.print("Введите координату x вершины №3: ");
        c.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №3: ");
        c.y = scanner.nextDouble();

        //Нахождение сторон треугольника
        double lab = Math.sqrt(Math.pow((b.x-a.x), 2) + Math.pow((b.y-a.y), 2));
        double lac = Math.sqrt(Math.pow((c.x-a.x), 2) + Math.pow((c.y-a.y), 2));
        double lbc = Math.sqrt(Math.pow((c.x-b.x), 2) + Math.pow((c.y-b.y), 2));

        //Проверка, существует ли вообще треугольник. Если нет, то программа
        //не должна вообще считать площадь.
        if ((lab + lac > lbc) && (lbc + lac > lab) && (lab + lbc > lac)) {
            //Полупериметр; используется в формуле нахождения площади
            double p = (lab + lbc + lac) / 2;

            //Нахождение площади треугольника по формуле Герона
            double S = Math.sqrt(p * (p - lac) * (p - lab) * (p - lbc));

            //Приводим дробное значение к виду: a.b (точка вместо заяптой), т. к. только
            //значение с точкой (дробное, где дробная часть это 0)
            // мы сможем представить без дробной части
            String formatted = String.format("%.1f", S).replace(',', '.');

            //проверка, целое число или нет
            if (Double.parseDouble(formatted) % 1 == 0) {
                //Если if вернуло true, значит, число целое (нет дробной части),
                //можем убрать дробную часть, равную 0, т. е. округлить до целого числа
                Long value = Math.round(S);
                System.out.println("Площадь треугольника: " + value);
            } else
                System.out.println("Площадь треугольника: " + formatted);
        }
        else System.out.println("Треугольник с данными координатами точек не существует");
    }
}


