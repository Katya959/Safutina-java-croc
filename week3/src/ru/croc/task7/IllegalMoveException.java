package ru.croc.task7;

import ru.croc.task7.ChessPosition;

public class IllegalMoveException extends Exception {
    public final ChessPosition start;
    public final ChessPosition end;

    public IllegalMoveException(ChessPosition start, ChessPosition end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Incorrect behavior for chess-knight: " + start + " -> " + end;
    }
}
