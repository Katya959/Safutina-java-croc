package ru.croc.task7;

import ru.croc.task7.ChessKnight;
import ru.croc.task7.ChessPosition;
import ru.croc.task7.IllegalPositionException;

public class Main {
    public static void main(String[] args) throws IllegalPositionException {
        try {
            ChessPosition p1 = new ChessPosition(1, 1);
            System.out.println(p1);
            //Строковое представление фигуры p1
            System.out.println("Объект строкового представления фигуры: " + p1);
            ChessPosition.parse(p1.toString());
            //Получаем такой же вывод, что и при System.out.println(p1), так как
            //мы получаем в методе parse объект класса ChessPosition, а он выводится в строковом представлении!

            ChessPosition p2 = new ChessPosition("g8");

            ChessPosition p3 = new ChessPosition("e7");

            ChessPosition p4 = new ChessPosition("e6");

            ChessPosition p5 = new ChessPosition("c8");

            //Корректное поведение фигуры конь
            ChessPosition[] knight1 = {p2, p3, p4};
            ChessKnight.isKnightMoveIsPossible(knight1);

            //Некорректное поведение фигуры конь
            ChessPosition[] knight2 = {p2, p3, p5};
            ChessKnight.isKnightMoveIsPossible(knight2);

            ChessPosition p6 = new ChessPosition(-1, 8);
            System.out.println(p6);    //Будет выведено This is invalid position! Программа закончит свою работу.

            //ChessPosition p7 = new ChessPosition("w9_");
            //System.out.println(p7);   //Это уже не выполнится. Но если бы не было предыдущего p3, то также вывелось бы
            //This is invalid position! И программа закончила бы свою работу.
        }
        catch (IllegalPositionException e) {
            System.out.println(e.toString());
        }
    }

}

