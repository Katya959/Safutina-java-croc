package ru.croc.task13;

import java.util.*;

//Необязательный класс. Для того, чтобы показать, что все работает верно!
//Вся реализация без примеров полностью в классе DeleteBlackWords.
public class Program {
    public static void main(String[] args) {
        DeleteBlackWords words1 = new DeleteBlackWords();
        //Лист с комментариями
        List<String> comments = new ArrayList<>();

        //Как пример привела, что все верно работает.
        //Набор слов - абстрактный.
        comments.add("Drink water Please");
        comments.add("please tree can could turn");

        //Добавляем в список запрещённых слов некоторые слова
        Set<String> blackList = new HashSet<>();
        blackList.add("Water");
        blackList.add("Can");

        //Вызываем метод с листом с скомментариями, а также листом с запрещёнными словами.
        words1.filterComments(comments, blackList);

        if (comments.size() == 0) {
            System.out.println("Все комментарии содержат запрещённые слова!");
        }
        else {
            System.out.println("Комментарии без запрещённых слов: ");
            for (String s: comments) {
                System.out.println(s);
            }
        }
    }
}
