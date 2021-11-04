package ru.croc.task10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class PeakLoadCounter {
    private TimeValue[] timeValues;
    private int peakLoad;

    public PeakLoadCounter(String file) {
        timeValues = getTimeValues(file);
        peakLoad = countPeakLoad(timeValues);
    }

    public int getPeakLoad() {
        return peakLoad;
    }

    private int countPeakLoad(TimeValue[] timeValues) {
        Arrays.sort(this.timeValues, Comparator.comparing(TimeValue::getTime));
        int maxLoad = 0;
        int currentLoad = 0;
        for (TimeValue value : timeValues) {
            currentLoad += value.getType() == LogTimeType.START ? +1 : -1;
            if (currentLoad > maxLoad) {
                maxLoad = currentLoad;
            }
        }
        return maxLoad;
    }

    private TimeValue[] getTimeValues(String file) {
        TimeValue[] timeValues = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Path path = Paths.get(file);
            int numberOfLines = (int)Files.lines(path).count();
            int timeValuesNumber = numberOfLines * 2;
            timeValues = new TimeValue[timeValuesNumber];
            for (int i = 0; i < timeValuesNumber; i += 2) {
                String line = reader.readLine();
                LogEntry entry = new LogEntry(line);
                timeValues[i] = new TimeValue(entry.getStartTime(), LogTimeType.START);
                timeValues[i + 1] = new TimeValue(entry.getEndTime(), LogTimeType.END);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File \""+ file + "\" not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return timeValues;
    }
}
