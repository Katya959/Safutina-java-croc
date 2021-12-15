package Statistics;

import MainClasses.Bot;
import MainClasses.Match;

import java.io.Serializable;
import java.util.*;

public class Statistics implements Serializable {

    //Данная функция находит статистику по всем ботам, объединяя информацию по всем матчам.
    public static ArrayList<Bot> findStatisticsOfEveryBot(ArrayList<Match> matches) {
        //Я запишу всех ботов с одним id в HashMap, ориентир - Id бота. Тогда удобно будет находить конечные значения этого бота по всем матчам.
        HashMap<Integer, ArrayList<Bot>> idBotAndAllBots = new HashMap<>();
        //Боты со всех матчей
        ArrayList<Bot> excellent = new ArrayList<>();   //Боты с разными id, но пересчитанными значениями.
        //Будет осортирован по убыванию количество монет у ботов.

        Match ar1 = matches.get(0);
        for (int i = 0; i < ar1.getNUM_BOTS(); i++) {
            Bot bot = ar1.getBots().get(i);
            ArrayList<Bot> ar = new ArrayList<>();
            ar.add(bot);
            idBotAndAllBots.put(bot.getBOT_ID(), ar);
        }

        //Проходим по всем матчам и берем оттуда ботов.
        for (int k = 1; k < matches.size(); k++) {
            //Боты определенного матча
            ArrayList<Bot> bots = matches.get(k).getBots();
            for (Bot bot : bots) {
                Integer id = bot.getBOT_ID();
                if (idBotAndAllBots.containsKey(id)) {
                    ArrayList<Bot> jBots = idBotAndAllBots.get(id);
                    jBots.add(bot);
                    idBotAndAllBots.put(id, jBots);
                }
            }
        }
        //Теперь в idBotAndAllBots хранятся все боты со всех матчей с определенным id.
        //Беру первого бота, у него id это ключ. Теперь мне нуджно в Set добавить такого робота с этим же Id, чтобы она
        //заменял всех предыдущих роботов с таким же id, соответственно, с пересчитанными его характеристиками.
        for (Map.Entry<Integer, ArrayList<Bot>> entry: idBotAndAllBots.entrySet()) {
            Integer id = entry.getKey();
            ArrayList<Bot> botS = entry.getValue();
            Bot botExcellent = new Bot();
            //Это количество ботов с таким id (name у ботов одинаковые, если id одинаковые) во всех матчах.
            int numberOfBots = botS.size();
            int COIN_COLLECTED = 0; int numberOfAttacks = 0;
            int numberOfWinAttacks = 0; int procentageVictoryOfAttacks = 0;
            int efficiencyCoinCollection = 0; int procentageOfResearchMap = 0;
            int numberOfDeathMatches = 0; int numberOfFriendlyMatches = 0;
            for (Bot bot : botS) {
                COIN_COLLECTED = COIN_COLLECTED + bot.getCOIN_COLLECTED();
                numberOfAttacks = numberOfAttacks + bot.getNumberOfAttacks();
                numberOfWinAttacks = numberOfWinAttacks + bot.getNumberOfWinAttacks();
                procentageVictoryOfAttacks = procentageVictoryOfAttacks + bot.getProcentageVictoryOfAttacks();
                efficiencyCoinCollection = efficiencyCoinCollection + bot.getEficiencyCoinCollection();
                procentageOfResearchMap = procentageOfResearchMap + bot.getProcentageOfResearchMap();
                numberOfDeathMatches = numberOfDeathMatches + bot.getNumberOfDeathMatches();
                numberOfFriendlyMatches = numberOfFriendlyMatches + bot.getNumberOfFriendlyMatches();
            }
            procentageVictoryOfAttacks = procentageVictoryOfAttacks / numberOfBots;
            efficiencyCoinCollection = efficiencyCoinCollection / numberOfBots;
            procentageOfResearchMap = procentageOfResearchMap / numberOfBots;

            botExcellent.setBOT_ID(id);
            botExcellent.setBOT_NAME(botS.get(0).getBOT_NAME());
            botExcellent.setCOIN_COLLECTED(COIN_COLLECTED);
            botExcellent.setNumberOfAttacks(numberOfAttacks);
            botExcellent.setNumberOfWinAttacks(numberOfWinAttacks);
            botExcellent.setProcentageVictoryOfAttacks(procentageVictoryOfAttacks);
            botExcellent.setEficiencyCoinCollection(efficiencyCoinCollection);
            botExcellent.setProcentageOfResearchMap(procentageOfResearchMap);
            botExcellent.setNumberOfFriendlyMatches(numberOfFriendlyMatches);
            botExcellent.setNumberOfDeathMatches(numberOfDeathMatches);
            excellent.add(botExcellent);
        }
        excellent.sort(Collections.reverseOrder(Bot.COMPARE_BY_COIN_COLLECTED));
        return excellent;
    }

    //Построила общий рейтинг всех ботов, участвующих в матчах.
    //Параметр: количество собранных монет.
    public static void getGeneralRatingOfAllBots(ArrayList<Match> matches) {
        ArrayList<Bot> excellent = findStatisticsOfEveryBot(matches);
        System.out.println("Общий рейтинг всех ботов по итогам всех матчей.");
        for (int i = 0; i < excellent.size(); i++) {
            Bot bot = excellent.get(i);
            System.out.printf("Идентификатор бота: %d   Имя бота: %s   Количество собранных монет: %d ", bot.getBOT_ID(), bot.getBOT_NAME(), bot.getCOIN_COLLECTED());
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    //Построила статистику по каждому боту, хотя бы раз играющему в матчах.
    //Параметр: количество собранных монет.
    public static void getStatisticsOfAllBots(ArrayList<Match> matches) {
        ArrayList<Bot> excellent = findStatisticsOfEveryBot(matches);
        System.out.println("Статистика с метриками по каждому боту.");
        for (int i = 0; i < excellent.size(); i++) {
            Bot bot = excellent.get(i);
            System.out.printf("Идентификатор бота: %d   Имя бота: %s   Количество собранных монет: %d ", bot.getBOT_ID(), bot.getBOT_NAME(), bot.getCOIN_COLLECTED());
            System.out.println();
            System.out.println("Эффективность сбора монет: " + bot.getEficiencyCoinCollection() + "%   " + "Средний процент исследования карты: "  + bot.getProcentageOfResearchMap() + "%");
            System.out.println("Количество сыгранных матчей в режиме FRIENDLY: " + bot.getNumberOfFriendlyMatches());
            if (bot.getNumberOfDeathMatches() != 0) {
                System.out.println("Количество сыгранных матчей в режиме DEATHMATCH: " + bot.getNumberOfDeathMatches() + " Процент победы в атаках: " + bot.getProcentageVictoryOfAttacks() + "%");
            }
            else {
                System.out.println("Количество сыгранных матчей в режиме DEATHMATCH: " + bot.getNumberOfDeathMatches());
            }
            System.out.println();
        }
    }


    //Построила по каждому матчу: информация о матче + боты с их результатами.
    //Параметр: количество собранных монет.
    public static void getResultsOfAllBotsOfMatch(Match match) {
        //Боты с данного матча
        //Я составляла рейтинг ботов по общему количеству собранных монет.
        ArrayList<Bot> allBots;
        allBots = match.getBots();

        allBots.sort(Collections.reverseOrder(Bot.COMPARE_BY_COIN_COLLECTED));
        System.out.println("Результаты матча.");
        System.out.println("Описание матча: Id матча: " + match.getMATCH_ID() + "   Режим матча: " + match.getMATCH_MODE());
        System.out.println("Количество раундов: " + match.getNUM_ROUNDS() + "   Длительность матча: " + match.getMATCH_TIME());
        System.out.println();
        //Под эффективностью сбора монет я подразумеваю то количество монет, которое собрал робот, к тому количеству, которое появилось на карте.
        for (Bot bot : allBots) {
            System.out.println("Id бота: " + bot.getBOT_ID() + " Имя бота: " + bot.getBOT_NAME());
            System.out.println("Количество собранных монет: " + bot.getCOIN_COLLECTED() + "   Процент исследования карты: " + bot.getProcentageOfResearchMap() + "%   Эффективность сбора монет: " + bot.getEficiencyCoinCollection() + "%");
            if (match.getMATCH_MODE().equals("DEATHMATCH")) {
                System.out.println("Процент победы в атаках: " + bot.getProcentageVictoryOfAttacks() + "%");
            }
        }
        System.out.println();
    }
}
