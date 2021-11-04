package ru.croc.task9;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

//Может быть с точки зрения расположения файлов, классов не очень красиво, но я сделала так, чтобы работала программа и работала верно.
public class Test {
    public static void main(String[] args) throws IOException {
        // будем искать в папке tmp
        String dir = args[0];
        //String dir = "/Users/ekaterinasafutina/Desktop/Lab4/Croc_week4/src/ru/croc/task9";
        // в этой папке будем искать файлы с расширением .log
        String ext = ".log";
        // вызываем метод поиска файлов с расширением .log в папке task9
        HashMap<Long, ArrayList<String>> map1 = findLogesInFilesWithExstension(dir, ext);
        System.out.println(map1);

        // в этой папке будем искать файлы с расширением .trace
        String ext1 = ".trace";
        // вызываем метод поиска файлов с расширением .trace в папке task9
        HashMap<Long, ArrayList<String>> map2 = findLogesInFilesWithExstension(dir, ext1);
        System.out.println(map2);

        //Теперь в map1 добавим все, что есть в map2; если есть повторения по ключу,
        // то добавляем только сообщение в map2 по соответствующему ключу в ArrayList с сообщениями
        Set<Long> set1 = map1.keySet();
        for (Long key: map2.keySet()) {
            if (set1.contains(key)) {
                ArrayList<String> value1 = map1.get(key);
                ArrayList<String> value2 = map2.get(key);
                value1.addAll(value2);
            }
            else {
                map1.put(key, map2.get(key));
            }
        }
        System.out.println(map1);

        Object[] keys = map1.keySet().toArray();
        Arrays.sort(keys);

        for (Object key: keys) {
            System.out.print(key);
            for (String line: map1.get(key)) {
                System.out.print(" " + line);
            }
            System.out.println();
        }
    }

    public static HashMap<Long, ArrayList<String>> findLogesInFilesWithExstension(String dir, String ext) throws IOException {
        HashMap<Long, ArrayList<String>> map = new HashMap<>();
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        }else{
            for (int i = 0; i < listFiles.length; i++) {
                BufferedReader reader = new BufferedReader(new FileReader(listFiles[i]));
                String line = reader.readLine();
                while(line!=null){
                    LogEntry log = new LogEntry(line);
                    ArrayList<String> itemsList = map.get(log.time);

                    // if list does not exist create it
                    if(itemsList == null) {
                        itemsList = new ArrayList<String>();
                        itemsList.add(log.message);
                        map.put(log.time, itemsList);
                    } else {
                        // add if item is not already in list
                        if(!itemsList.contains(log.message)) itemsList.add(log.message);
                    }
                    line = reader.readLine();
                }
            }
        }
        return map;
    }
}
