package ru.croc.task7;

public class ChessPosition {
    private int x;
    private int y;

    public static final int SIZE_OF_BOARD = 8;

    private static final String indexOnBoard = "abcdefgh";

    public ChessPosition(int x, int y) throws IllegalPositionException {
        setX(x);
        setY(y);
    }

    public ChessPosition(String index) throws IllegalPositionException {
        setPosition(index);
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (position.length() != 2) {
            throw new IllegalPositionException("This is invalid position!");
        }
        //Проверка корректности первого параметра в позиции (буквы)
        char x = position.charAt(0);
        Integer index = indexOnBoard.indexOf(x);
        //Если index не -1, значит, такой символ есть в строке indexOnBoard.
        if (index > -1) {
            this.x = index;
        }
        else {
            throw new IllegalPositionException(xIndexOutOfBoundsErrorMessage(x));
        }

        //Проверка корректности второго параметра в позиции (цифры)
        char y = position.charAt(1);
        //getNumericValue(char ...) преобразует указанный числовой символ Юникода
        // в число двойной точности с плавающей запятой.
        //Вычитаем 1, т. к. у нас начинается отсчёт значений на доске с 0, а не с 1!
        index = Character.getNumericValue(y) - 1;
        if (Character.isDigit(y) && index < SIZE_OF_BOARD) {
            this.y = index;
        }
        else {
            throw new IllegalPositionException(xIndexOutOfBoundsErrorMessage(y));
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) throws IllegalPositionException {
        if (isIndexValid(x))
            this.x = x;
        else {
            throw new IllegalPositionException(xIndexOutOfBoundsErrorMessage(x));
        }
    }

    public void setY(int y) throws IllegalPositionException {
        if (isIndexValid(y))
            this.y = y;
        else {
            throw new IllegalPositionException(xIndexOutOfBoundsErrorMessage(y));
        }
    }

    private boolean isIndexValid(int a) {
        return a>=0 && a<=SIZE_OF_BOARD - 1;
    }

    //Метод по строковому представлению возвращает объект класса ChessPosition.
    static ChessPosition parse(String position) throws IllegalPositionException {
        char a = position.charAt(0);
        Integer b = Integer.parseInt(String.valueOf(position.charAt(1)));
        return new ChessPosition(indexOnBoard.indexOf(a), b - 1);
    }


    private String xIndexOutOfBoundsErrorMessage(int a) {
        return "Index X = " + a + " is invalid. It must be number from 0 to " + (SIZE_OF_BOARD - 1) + "!";
    }
    private String yIndexOutOfBoundsErrorMessage(int a) {
        return "Index Y = " + a + " is invalid. It must be number from 0 to " + (SIZE_OF_BOARD - 1) + "!";
    }

    @Override
    public String toString() {
        return Character.toString(indexOnBoard.charAt(x)) + (y + 1);
    }
}

