package ru.croc.task5;
import java.lang.Math;

public class Circle extends Figure {
    private Point center;
    private int radius;

    Circle(Point center, int r) {
        this.center = center;
        this.radius = r;
    }

    @Override
    public String toString() {
        return "C (" + center.getX() + ", " + center.getY() + ") " + radius;
    }

    @Override
    boolean containsPoint(Point point) {
        return (Math.pow((point.getX() - center.getX()), 2) + Math.pow((point.getY() - center.getY()), 2)) <= Math.pow(radius, 2);
    }

    @Override
    public void move(int dx, int dy) {
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }
}
