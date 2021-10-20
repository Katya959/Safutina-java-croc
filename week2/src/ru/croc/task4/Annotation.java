package ru.croc.task4;

//класс для представления данных разметки: подпись + фигура
public class Annotation {

    //подпись для фигуры на аннотированном изображении
    public String label;

    //фигура
    Figure figure;

    public Annotation() {}

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

}
