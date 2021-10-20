package ru.croc.task4;

public class Rectangle extends Figure {  //класс для задания данных для прямоугольника
    private Point lowerLeftPoint;
    private Point upperRightPoint;
    String label;

    Rectangle(Point leftPoint, Point rightPoint) {
        this.lowerLeftPoint = leftPoint;
        this.upperRightPoint = rightPoint;
    }

    @Override
    public String toString() {
        return "R (" + lowerLeftPoint.getX() + ", " + lowerLeftPoint.getY() + "), (" + upperRightPoint.getX() + ", " + upperRightPoint.getY() + ")";
    }
}
