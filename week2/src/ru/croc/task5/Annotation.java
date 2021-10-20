package ru.croc.task5;

//класс для представления данных разметки: подпись + фигура
public class Annotation {

    //подпись для фигуры на аннотированном изображении
    public String label;

    //фигура
    Figure figure;

    public Annotation(Figure figure, String label) {
        this.figure = figure;
        this.label = label;
    }

    public Figure getFigure() {
        return figure;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return (figure.toString() + ": " + label);
    }

    public static Annotation findByPoint(Annotation[] annotations, int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().containsPoint(new Point(x, y))) {
                return annotation;
            }
        }
        return null;
    }

    public static Annotation findByLabel(Annotation[] annotations, String label1) {
        for (Annotation annotation : annotations) {
            String label = annotation.getLabel();
            if (label.contains(label1))
                return annotation;
        }
        return null;
    }
}

