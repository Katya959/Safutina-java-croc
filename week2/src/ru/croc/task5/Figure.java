package ru.croc.task5;

abstract class Figure implements Moveable {
    @Override
    public void move(int dx, int dy) {
    }

    abstract boolean containsPoint(Point point);
}
