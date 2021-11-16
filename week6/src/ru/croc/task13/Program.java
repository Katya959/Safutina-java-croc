package ru.croc.task13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Необязательный класс. Для того, чтобы показать, что все работает верно!
//Вся реализация без примеров полностью в классе DeleteBlackWords.
public class Program {
    public static void main(String[] args) {
        DeleteBlackWords words1 = new DeleteBlackWords();
        //Лист с комментариями
        List<String> comments = new ArrayList<>();
        //Как пример привела, что все верно работает.
        //Набор слов - абстрактный.
        comments.add("drink water please");
        comments.add("please tree can could turn");
        System.out.println(comments);
        //Добавляем в список запрещённых слов некоторые слова
        Set<String> blackList = new HashSet<>();
        blackList.add("water");
        blackList.add("tune");
        //Вызываем метод с листом с скомментариями, а также листом с запрещёнными словами.
        words1.filterComments(comments, blackList);
        //Вывожу все комментарии
        for (String s: comments) {
            System.out.println(s);
        }
    }
}


