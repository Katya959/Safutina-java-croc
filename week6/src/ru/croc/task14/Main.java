package ru.croc.task14;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //!!!Я нахожу суммарное количество просмотров только по историям тех пользователей, которых отобрала в пункте 1
        //Вместо null я вывожу фразу: В сервисе для онлайн-просмотра фильмов кинотеатра "Места для поцелуев" вы посмотрели все фильмы!
        //Чтобы не выводить null, для пользователя так понятнее будет.
        //Проверяла работу программы с помощью данных из файла test_filtered.txt.
        //Объявляем коснатнты, отвечающие за файл с названиями фильмов и их номерами в списке,
        //а также списке просмотренных фильмов всех пользователей
        final String FILMS = "/Users/ekaterinasafutina/Desktop/Lab4/Croc_week6/src/ru/croc/task14/Films.txt";
        final String PROSMOTRS = "/Users/ekaterinasafutina/Desktop/Lab4/Croc_week6/src/ru/croc/task14/Spisok prosmotrennych filmov.txt";
        File film_s = new File(FILMS);
        File spisok_prosmotrov = new File(PROSMOTRS);

        //Считываем все из файла (все фильмы: номер и название)
        BufferedReader reader = new BufferedReader(new FileReader(film_s));
        HashMap<Integer, String> mapOfFilms = new HashMap<>();     //Map с идентификатором фильма и его названием
        String line = reader.readLine();
        while (line != null) {
            //считываются номер фильма и название фильма, записанные через пробел
            String[] numberAndFilm = line.trim().split(",", 2);
            mapOfFilms.put(Integer.parseInt(numberAndFilm[0]), numberAndFilm[1]);
            //Как только мы считываем строчку и проверяем, что она не ноль, в том же цикле считываем следующую строчку.
            //Если она будет нулевой, while не выполнится, цикл закончит свою работу.
            line = reader.readLine();
        }

        //Считывание просмотров фильма определенного пользователя
        ArrayList<ArrayList<Integer>> prosmotrsOfPeople = new ArrayList<>();   //ArrayList со всеми просмотрами пользователей
        BufferedReader reader1 = new BufferedReader(new FileReader(spisok_prosmotrov));
        String line1 = reader1.readLine();
        while (line1 != null) {
            //Все нецифровые символы мы заменяем пробелами, а потом в массив строк
            // array добавляем все, что написано между пробелами
            //Т. е. в нашем случае мы заменяем запятые пробелами.
            String[] array = (line1.replaceAll("\\D+", " ").trim()).split(" ");
            //Все строчки, которые являются на самом деле числами, мы преобразуем строки
            // к числам и записываем их в list2.
            ArrayList<Integer> list2 = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                list2.add(Integer.parseInt(array[i]));
            }
            prosmotrsOfPeople.add(list2);
            line1 = reader1.readLine();
        }

        System.out.println("[in]");
        ArrayList<Integer> prosmotrsOfOnePerson = new ArrayList<>();   //ArrayList со всеми просмотрами пользователя
        BufferedReader readerOne = new BufferedReader(new InputStreamReader(System.in));
        String lineOne = readerOne.readLine();
        //Все нецифровые символы мы заменяем пробелами, а потом в массив строк array
        // добавляем все, что написано между пробелами
        String[] array = (lineOne.replaceAll("\\D+", " ").trim()).split(" ");


        //Все строчки, которые являются на самом деле числами, мы преобразуем
        // строки к числам и записываем их в prosmotrsOfOnePerson.
        for (int i = 0; i < array.length; i++) {
            prosmotrsOfOnePerson.add(Integer.parseInt(array[i]));
        }

        //Для начала мы выберем только тех пользователей, которые посмотрели
        // как минимум половину фильмов пользователя, для которого мы формируем рекомендацию
        //k - количество просмотров
        int k = 0;
        if (prosmotrsOfOnePerson.size() % 2 == 0)
            k = prosmotrsOfOnePerson.size() /2;
        else
            k = prosmotrsOfOnePerson.size() / 2 + 1;

        //ArrayList со всеми просмотрами пользователей, которые уже прошли
        //проверку на просмотр как минимум половины фильмов пользователя для рекомендации
        ArrayList<ArrayList<Integer>> prosmotrsOfPeopleAfterDelete = new ArrayList<>();
        for (int i = 0; i < prosmotrsOfPeople.size(); i++) {
            ArrayList<Integer> arrayOfOnePeopleInListNew = new ArrayList<>();
            Set<Integer> set2 = new HashSet<>(prosmotrsOfPeople.get(i));
            arrayOfOnePeopleInListNew.addAll(set2);
            int n = 0;
            for (int j = 0; j < prosmotrsOfOnePerson.size(); j++) {
                if (arrayOfOnePeopleInListNew.contains(prosmotrsOfOnePerson.get(j)))
                    n = n + 1;
            }
            if (n >= k)
                prosmotrsOfPeopleAfterDelete.add(prosmotrsOfPeople.get(i));
        }

        //Удаляем повторяющиеся фильмы у человека, для которого составляем рекомендацию
        ArrayList<Integer> prosmotrsOfOneForRecommendation = new ArrayList<>();
        Set<Integer> set = new HashSet<>(prosmotrsOfOnePerson);
        prosmotrsOfOneForRecommendation.addAll(set);

        //В списке оставшихся пользователей удаляем все те фильмы,
        //которые смотрел человек, для которого мы составляем рекомендацию
        ArrayList<ArrayList<Integer>> newProsmotrsOfPeopleAfterDeleteFilmsOfAlone = new ArrayList<>();
        for (int i = 0; i < prosmotrsOfPeopleAfterDelete.size(); i++) {
            ArrayList<Integer> newProsmotrsOfPeoplei = new ArrayList<>(prosmotrsOfPeopleAfterDelete.get(i));
            //Массив с числами, которые точно не совпадают с элементами массива prosmotrsOfOneAlone
            ArrayList<Integer> newArrayOfNumbers = new ArrayList<>();
            for (int j = 0; j < newProsmotrsOfPeoplei.size(); j++) {
                int p = 0;
                for (int u = 0; u < prosmotrsOfOneForRecommendation.size(); u++) {
                    if (newProsmotrsOfPeoplei.get(j) == (prosmotrsOfOneForRecommendation.get(u)))
                        p = p + 1;
                }
                if (p == 0)
                    newArrayOfNumbers.add(newProsmotrsOfPeoplei.get(j));;
            }
            if (newArrayOfNumbers.size() != 0)
                newProsmotrsOfPeopleAfterDeleteFilmsOfAlone.add(newArrayOfNumbers);
        }

        //Map для записи фильма и количества просмотров этого фильма
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < newProsmotrsOfPeopleAfterDeleteFilmsOfAlone.size(); i++) {
            ArrayList<Integer> fimlsOfOnePeople = new ArrayList<>(newProsmotrsOfPeopleAfterDeleteFilmsOfAlone.get(i));
            for (int j = 0; j < fimlsOfOnePeople.size(); j++) {
                Integer key = fimlsOfOnePeople.get(j);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                }
                else {
                    map.put(key, 1);
                }
            }
        }

        int value_max = 0;
        int key_max = 0;
        for (Map.Entry<Integer, Integer> pair: map.entrySet()) {
            //Нахождение фильма с максимальным количеством просмотров
            Integer value = pair.getValue();
            if (value > value_max) {
                value_max = value;
                key_max = pair.getKey();
            }
        }

        System.out.println("[out]");
        if (key_max == 0) {
            System.out.println("В сервисе для онлайн-просмотра фильмов " +
                    "кинотеатра \"Места для поцелуев\" вы посмотрели все фильмы!");
        }
        else {
            System.out.println(mapOfFilms.get(key_max));
        }
    }
}

