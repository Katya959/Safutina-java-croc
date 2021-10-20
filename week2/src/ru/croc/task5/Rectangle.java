package ru.croc.task5;

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

    @Override
    boolean containsPoint(Point point) {
        return point.getX() >= lowerLeftPoint.getX() && point.getX() <= upperRightPoint.getX()
                && point.getY() >= lowerLeftPoint.getY() && point.getY() <= lowerLeftPoint.getY();
    }

    @Override
    public void move(int dx, int dy) {
        lowerLeftPoint.setX(lowerLeftPoint.getX() + dx);
        lowerLeftPoint.setY(lowerLeftPoint.getY() + dy);

        upperRightPoint.setX(upperRightPoint.getX() + dx);
        upperRightPoint.setY(upperRightPoint.getY() + dy);
    }
}
