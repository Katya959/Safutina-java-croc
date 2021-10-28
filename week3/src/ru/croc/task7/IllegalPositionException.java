package ru.croc.task7;

public class IllegalPositionException extends Exception {

    //String s;

    public IllegalPositionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "This is invalid position!";
    }

}
