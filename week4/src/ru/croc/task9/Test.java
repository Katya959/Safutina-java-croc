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

        //Сортировка ключей в map1(где файлы с расширением .log и .trace)
        Object[] keys = map1.keySet().toArray();
        Arrays.sort(keys);

        //При выводе сначала вывожу значение ключа, а потом через пробел все сообщения, который содержаться в ArrayList, полученном по данному ключу.
        for (Object key: keys) {
            System.out.print(key);
            for (String line: map1.get(key)) {
                System.out.print(" " + line);
            }
            System.out.println();
        }
    }

    //Эта функция ищет в указанной директории файла с определенным расширением, которое передается вторым аргументом в функцию, а потом анализирует данные
    //в этих файлах
    public static HashMap<Long, ArrayList<String>> findLogesInFilesWithExstension(String dir, String ext) throws IOException {
        //В данный map я помещаю время сообщения и само сообщения.
        //Я понимаю, что может быть одно и то же время у нескольких сообщений в файлах, поэтому так как я считываю все файла одновременно, у меня может 
        //получиться, что время одно, а сообщений несколько. Поэтому я для записи сообщений создаю ArrayList, в который при повторяющемся времени будут
        //записываться сообщения.
        //Делаю это из-за того, что в map все ключи уникальные, поэтому пара с одинаковым ключом создать нельзя.
        //Например: 10 message 10, 10 message15, 20 message20. Когда я считала данные строки, я получаю: 10 {message 10, message15}, 20 {message20}.
        //Т. е. при повторяющеймся времени все сообщения будут сохранены!
        HashMap<Long, ArrayList<String>> map = new HashMap<>();
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        //Массив с файлами определенного расширения
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        }else{
            //Прохожусь по всем найденным файлам данного расширения
            for (int i = 0; i < listFiles.length; i++) {
                BufferedReader reader = new BufferedReader(new FileReader(listFiles[i]));
                //Считываю по строчкам из каждого файла
                String line = reader.readLine();
                //Если она не пустая
                while(line!=null){
                    //Создаю log, в котором записано время сообщения и само сообщение типа String.
                    LogEntry log = new LogEntry(line);
                    //Создается массив ArrayList строк, в который при наличии помещаются все сообщения из map с ключом log.time (массив сообщений).
                    ArrayList<String> itemsList = map.get(log.time);

                    // Если массив сообщений пустой, значит, с данным временем (ключом) сообщения еще не были найдены.
                    //В данном случае я создаю ArrayList для значений данного ключа, добавляю в него сообщение log.message.
                    if(itemsList == null) {
                        itemsList = new ArrayList<String>();
                        itemsList.add(log.message);
                        //В map добавляю пару: ключ (значение времени log.time), а также значения (itemsList).
                        map.put(log.time, itemsList);
                    } else {
                        // Если же ArrayList с сообщениями по данному ключу не пустой, тогда я в данный ArrayList добавляю к уже имеющимся сообщениям еще одно.
                        if(!itemsList.contains(log.message)) itemsList.add(log.message);
                    }
                    line = reader.readLine();
                }
            }
        }
        return map;
    }
}
