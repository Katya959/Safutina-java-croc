package ru.croc.task13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DeleteBlackWords implements BlackListFilter {
    public void filterComments(List<String> comments0, Set<String> blackList0) {
        List<String> comments = new ArrayList<>();
        for (String comment: comments0) {
            String comment1 = comment.toLowerCase();
            comments.add(comment1);
        }

        Set<String> blackList = new HashSet<>();
        for (String black: blackList0) {
            String black1 = black.toLowerCase();
            blackList.add(black1);
        }

        //В данный arrayList будут добавляться все комментарии без запрещённых слов
        List<String> comments1 = new ArrayList<>();
        //Берем все слова-комментарии по очереди
        for (String s: comments) {
            int k = 0;
            //Берём все запрещённые слвоа из списка по одному
            for (String s1 : blackList) {
                if (s.contains(s1)) {
                    //если запрещённое слово содержится в комментарии, значит, этот комментарий должен быть удален
                    k = 1;
                    break;
                }
            }
            //Если к осталось равным 0, значит, не одно запренённое слово
            //не было найдено, данный комментарий нам подходит
            if (k == 0)
                comments1.add(s);
        }
        //После проверки всех комментарием, исходный arrayList обнуляется.
        comments0.clear();
        //В arrayList, который был передан, записываем все данные, которые есть в comments.
        comments0.addAll(comments1);
    }
}
