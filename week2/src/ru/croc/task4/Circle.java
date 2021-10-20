package ru.croc.task4;

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
}
