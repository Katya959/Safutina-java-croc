package ru.croc.task16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        //HashMap для сопоставления номера группы и её возрастных ограничений.
        //Т. е., например, первая группа (самая старшая: 1: 101, 123, к примеру).
        HashMap<Integer, ArrayList<Integer>> groupAndAge = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] numbers_string = line.split(" ");
        Integer[] numbers = new Integer[numbers_string.length];
        for (int i = 0; i < numbers_string.length; i++) {
            numbers[i] = Integer.parseInt(numbers_string[i]);
        }

        //Добавила самую возрастную группу с номером 1 в map; возраста из примера 101 123.
        //Дальше добавляем все возрастные группы: номер + границы.
        ArrayList<Integer> array1 = new ArrayList<>();
        array1.add(numbers[numbers.length-1] + 1);
        array1.add(123);
        groupAndAge.put(1, array1);
        int k = 2;
        for (int i = numbers.length-1; i > 0; i--) {
            ArrayList<Integer> array = new ArrayList<>();
            array.add(numbers[i-1] + 1);
            array.add(numbers[i]);
            groupAndAge.put(k, array);
            k = k + 1;
        }
        ArrayList<Integer> array2 = new ArrayList<>();
        array2.add(0);
        array2.add(numbers[0]);
        groupAndAge.put(numbers.length+1, array2);

        //Проверка номера группы и возрастов
        for (Map.Entry<Integer, ArrayList<Integer>> pair: groupAndAge.entrySet()) {
            Integer key = pair.getKey();
            ArrayList<Integer> array = pair.getValue();
        }

        //Map с номером группы, а также все люди, чей возраст находится в диапазоне указанной группы
        HashMap<Integer, HashMap<Integer, ArrayList<String>>> peopleOfEachGroup = new HashMap<>();
        for (int i = 0; i < numbers.length + 1; i++) {
            HashMap<Integer, ArrayList<String>> array = new HashMap<>();
            peopleOfEachGroup.put(i+1, array);
        }

        //Считывание ФИО и возраста
        String s = reader.readLine();
        while (!s.equals("END")) {
            String[] FIO_AGE = s.split(",");
            String fio = FIO_AGE[0];
            Integer age = Integer.parseInt(FIO_AGE[1]);
            for (Map.Entry<Integer, ArrayList<Integer>> pair: groupAndAge.entrySet()) {
                //Номер группы возрастов
                Integer numberOfGroup = pair.getKey();
                //ArrayList с двумя возрастами - диапазон для конкретной группы
                ArrayList<Integer> array = pair.getValue();
                if (array.get(0) <= age && array.get(1) >= age) {
                    //hashMap с возрастом и всеми ФИО людей из конктретной группы
                    HashMap<Integer, ArrayList<String>> hashMap = peopleOfEachGroup.get(numberOfGroup);
                    if (hashMap.containsKey(age)) {
                        ArrayList<String> array_hashMap_Age = hashMap.get(age);
                        array_hashMap_Age.add(fio);
                        hashMap.put(age, array_hashMap_Age);
                        peopleOfEachGroup.put(numberOfGroup, hashMap);
                    }
                    else {
                        ArrayList<String> arrayMapDoesNotContainsAge = new ArrayList<>();
                        arrayMapDoesNotContainsAge.add(fio);
                        hashMap.put(age, arrayMapDoesNotContainsAge);
                        peopleOfEachGroup.put(numberOfGroup, hashMap);
                    }
                }
            }
            s = reader.readLine();
        }

        //Для каждой группы из нашего списка групп по каждому диапазону возрастов
        for (Integer number: peopleOfEachGroup.keySet()) {
            Integer numberOfGroup = number;
            //Взяли из группы все возраста и всех людей по каждому возрасту.
            HashMap<Integer, ArrayList<String>> map = peopleOfEachGroup.get(numberOfGroup);
            Set<Integer> set = new HashSet<>();
            set = map.keySet();
            List list = new ArrayList(set);
            Collections.sort(list, Collections.reverseOrder());
            //list - arrayList с отсортированными в порядке убывания ключами

            HashMap<Integer, ArrayList<String>> map_change = new HashMap<>();

            for (int i = 0; i < list.size(); i++) {
                map_change.put((Integer) list.get(i), map.get(list.get(i)));
            }
            peopleOfEachGroup.put(numberOfGroup, map_change);
        }

        for (Map.Entry<Integer, HashMap<Integer, ArrayList<String>>> pair: peopleOfEachGroup.entrySet()) {
            Integer key = pair.getKey();
            //hashMap1 со значением возраста и всех людей этого возраста
            HashMap<Integer, ArrayList<String>> hashMap1 = pair.getValue();
            for (Integer key1: hashMap1.keySet()) {
                ArrayList<String> array = hashMap1.get(key1);
                Collections.sort(array);
                hashMap1.put(key1, array);
            }
        }

        //Вывод всех данных
        for (Integer group: peopleOfEachGroup.keySet()) {
            //Границы значений для группы возраста
            ArrayList<Integer> ages = groupAndAge.get(group);
            //map со значением возраста и список всех людей
            HashMap<Integer, ArrayList<String>> map = peopleOfEachGroup.get(group);
            if (ages.get(1) == 123 && map.size()!=0) {
                System.out.print("" + ages.get(0) + "+: ");
            }
            if (ages.get(1) != 123 && map.size()!=0) {
                System.out.print("" + ages.get(0) + "-" + ages.get(1) + ": ");
            }

            //map со значениями возраста и всех людей, которым столько лет
            Set<Integer> set = new HashSet<>();
            //set со значениями возраста людей из map
            set = map.keySet();
            List list = new ArrayList(set);
            //System.out.println(list);
            //В list будут храниться значения ключей в обратном порядке
            Collections.sort(list, Collections.reverseOrder());

            //Берем значение возраста
            for (int i = 0; i < list.size(); i++) {
                Integer age = (Integer) list.get(i);
                ArrayList<String> people = map.get(age);

                //Чтобы выводилось в определенном формате, я прописала все возможные случаи.
                if (people.size() == 1 && i == list.size() - 1) {
                    System.out.println(people.get(0) + " (" + age + ")");
                }
                if (people.size() == 1 && i != list.size() - 1) {
                    System.out.print(people.get(0) + " (" + age + "), ");
                }
                if (people.size() != 1) {
                    for (int j = 0; j < people.size(); j++) {
                        System.out.print(people.get(j) + " (" + age + "), ");
                    }
                }
            }
        }
    }
}
