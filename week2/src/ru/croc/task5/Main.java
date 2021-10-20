package ru.croc.task5;

public class Main {
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(new Point(0,0), new Point(100,50));
        Circle circle = new Circle(new Point(25, 10), 10);
        Annotation annotatedBox = new Annotation(rec, "Картина");
        Annotation annotatedCircle = new Annotation(circle, "Круг");
        Annotation[] annotations = new Annotation[2];
        annotations[0] = annotatedBox;
        annotations[1] = annotatedCircle;

        System.out.println("Составляющие массива annotations: ");
        for (Annotation annotation : annotations) {
            System.out.println(annotation.getFigure().toString() + ": " + annotation.getLabel());
        }
        System.out.println();

        System.out.println("Проверка корректности работы метода findByPoint");
        Annotation an = Annotation.findByPoint(annotations, 0,0);
        if (an != null) {
            System.out.println(an.getFigure().toString() + ": " + an.getLabel());
        }
        else {
            System.out.println("Нет аннотации, в фигуре которой есть указанная точка");
        }
        System.out.println();

        System.out.println("Проверка корректности работы метода findByLabel");
        Annotation ann = Annotation.findByLabel(annotations, "Карт");
        if (ann != null) {
            System.out.println(an.getFigure().toString() + ": " + an.getLabel());
        }
        else {
            System.out.println("Нет аннотации, в подписи которой есть данный отрывок строки");
        }
        System.out.println();

        System.out.println("Проверка корректности работы метода по передвижению фигуры: ");
        System.out.println("Начальные координаты прямоугольника: ");
        System.out.println(rec.toString());
        rec.move(5, 10);
        System.out.println("Координаты прямоугольника после смещения: ");
        System.out.println(rec.toString());

        System.out.println("Начальные координаты круга: ");
        System.out.println(circle.toString());
        circle.move(-10, 25);
        System.out.println("Координаты круга после смещения: ");
        System.out.println(circle.toString());
    }
}
