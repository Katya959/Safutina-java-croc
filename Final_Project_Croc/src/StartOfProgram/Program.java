package StartOfProgram;
import BasicImplementionClasses.MyFileNameFilter;
import MainClasses.MapOfMatch;
import MainClasses.Match;
import Statistics.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        String dir = args[0];
        String ext = ".log";
        //В массив fileWithLog мы записываем все файла с расширением .log, найденные в этой директории.
        //Я предусмотрела вариант, что в этой директории не все файла с расширением .log, поэтому провела фильтрацию файлов.
        File[] fileWithLog = findLogesInFilesWithExstension(dir, ext);
        ArrayList<Match> matches = new ArrayList<>();

        for (File file: fileWithLog) {
            MapOfMatch map = new MapOfMatch();
            map.readMapConfig(file.getAbsolutePath());
            Match match = new Match(map);
            match.readMatchConfig(file.getAbsolutePath());
            match.readBotsAndCoinsInfo(file.getAbsolutePath());
            matches.add(match);
        }

        //Результаты каждого матча
        for (Match match: matches) {
            Statistics.getResultsOfAllBotsOfMatch(match);
            System.out.println();
        }

        //Таблица общего рейтинга ботов
        Statistics.getGeneralRatingOfAllBots(matches);

        //Статистика по каждому боту.
        Statistics.getStatisticsOfAllBots(matches);
    }

    public static File[] findLogesInFilesWithExstension(String dir, String ext) throws IOException {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        //Массив с файлами определенного расширения
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if(listFiles.length == 0) {
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        }
        return listFiles;
    }
}
