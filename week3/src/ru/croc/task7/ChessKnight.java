package ru.croc.task7;

import java.util.Arrays;

public class ChessKnight {
    //Движение корректно, если изменение одной координаты равно 2, а другой - 1.
    public static boolean isMoveCorrect(ChessPosition start, ChessPosition end) {
        return Math.abs(end.getX() - start.getX()) == 2 && Math.abs(end.getY() - start.getY()) == 1
        || Math.abs(end.getX() - start.getX()) == 1 && Math.abs(end.getY() - start.getY()) == 2;
    }

    public static void doSomeMoves(ChessPosition[] positions) throws IllegalMoveException {
        for (int i = 0; i < positions.length-1; i++) {
            ChessPosition position1 = positions[i];
            ChessPosition position2 = positions[i+1];
            if (!isMoveCorrect(position1, position2))
                throw new IllegalMoveException(position1, position2);
        }
    }

    public static void isKnightMoveIsPossible (ChessPosition[] stringPositions) throws IllegalPositionException {
        System.out.println("[in]  " + Arrays.toString(stringPositions));

        System.out.print("[out]  ");
        try {
            ChessKnight.doSomeMoves(stringPositions);
            System.out.println("OK");
        } catch (IllegalMoveException e) {
            System.out.println(e);
        }
    }

}
