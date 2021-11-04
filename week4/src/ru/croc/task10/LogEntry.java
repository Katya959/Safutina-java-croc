package ru.croc.task10;

public class LogEntry {
    private final long startTime;
    private final long endTime;

    public LogEntry(String line) {
        if (line == null || line.length() == 0) {
            throw new IllegalArgumentException("Line is empty or null.");
        }
        String[] lineComponents = line.trim().split(",",2);
        this.startTime = Long.parseLong(lineComponents[0]);
        this.endTime = Long.parseLong(lineComponents[1]);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
