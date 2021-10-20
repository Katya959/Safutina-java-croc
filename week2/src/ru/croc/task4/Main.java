package ru.croc.task4;

public class Main {
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(new Point(0,0), new Point(100,50));
        Circle circle = new Circle(new Point(25, 10), 10);
        Annotation annotatedBox = new Annotation(rec, "Картина");
        Annotation annotatedCircle = new Annotation(circle, "Круг");
        Annotation[] annotations = new Annotation[2];
        annotations[0] = annotatedBox;
        annotations[1] = annotatedCircle;

        for (Annotation annotation : annotations) {
            System.out.println(annotation.getFigure().toString() + ": " + annotation.getLabel());
        }

    }
}
