package ru.croc.task9;

public class LogEntry {
    public final long time;
    public final String message;

    LogEntry(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Empty line");
        }
        String[] lineComponents = line.trim().split(" ",2);
        time = Long.parseLong(lineComponents[0]);
        message = lineComponents[1];
    }

    @Override
    public String toString() {
        return time + " " + message;
    }
}

