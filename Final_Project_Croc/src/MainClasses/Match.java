package MainClasses;

import BasicImplementionClasses.Point;
import MainClasses.Bot;
import MainClasses.MapOfMatch;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Match {
    String MATCH_ID;                    //Идентификатор матча
    String MATCH_MODE;                  //режим матча
    Integer NUM_ROUNDS;                 //количество раундов
    Long MATCH_TIME;                    //длительность матча
    Long RANDOM_SEED;                //значение, ненужное для статистики
    Integer MOVE_TIME_LIMIT;            //ограниченное время на выполнение хода в миллисекундах, >= 500.
    Integer COIN_SPAWN_PERIOD;          //период генерации монет
    Integer COIN_SPAWN_VOLUME;          //объём генерации монет за раунд
    Integer NUM_BOTS;                   //количество ботов, участвующих в матче
    Integer TOTAL_NUMBER_OF_COINS;      //общее количество монет, сгенерированных в конкретном матче
    MapOfMatch MAP;                            //карта, которая используется в данном матче
    ArrayList<Bot> bots = new ArrayList<>();   //список ботов, участвующих в матче
    ArrayList<Bot> whoWillLeaveTheMatchFaster = new ArrayList<>();     //Список ботов по очереди, кто первый и тд покинули матч.

    public ArrayList<Bot> getBots() {
        return this.bots;
    }

    public ArrayList<Bot> getWhoWillLeaveTheMatchFaster() {
        return this.whoWillLeaveTheMatchFaster;
    }

    public String getMATCH_ID() {
        return this.MATCH_ID;
    }

    public String getMATCH_MODE() {
        return this.MATCH_MODE;
    }

    public Integer getNUM_ROUNDS() {
        return this.NUM_ROUNDS;
    }

    public Long getMATCH_TIME() {
        return this.MATCH_TIME;
    }

    public Long getRANDOM_SEED() {
        return this.RANDOM_SEED;
    }

    public Integer getMOVE_TIME_LIMIT() {
        return this.MOVE_TIME_LIMIT;
    }

    public Integer getCOIN_SPAWN_PERIOD() {
        return this.COIN_SPAWN_PERIOD;
    }

    public Integer getCOIN_SPAWN_VOLUME() {
        return this.COIN_SPAWN_VOLUME;
    }

    public Integer getNUM_BOTS() {
        return this.NUM_BOTS;
    }

    public Integer getTOTAL_NUMBER_OF_COINS() {
        return this.TOTAL_NUMBER_OF_COINS;
    }

    public MapOfMatch getMAP() {
        return this.MAP;
    }

    public Match(MapOfMatch map) {
        this.MAP = map;
    }
    public Match() {
    }

    public void readMatchConfig(String file1) throws IOException {
        File file = new File(file1);
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        if (file.length() == 0) {
            System.out.println(file1 + " - пустой!");
        }
        else {
            boolean k = true;
            while (k) {
                String line = reader.readLine();
                String[] values = line.split(" ");
                String enumValues = values[0];   //это идентификатор значения
                switch (enumValues) {
                    case "match":
                        break;
                    case "match_id":
                        this.MATCH_ID = values[1];
                        break;
                    case "match_time":
                        this.MATCH_TIME = Long.parseLong(values[1]);
                        break;
                    case "num_bots":
                        this.NUM_BOTS = Integer.parseInt(values[1]);
                        break;
                    case "mode":
                        this.MATCH_MODE = values[1];
                        break;
                    case "num_rounds":
                        this.NUM_ROUNDS = Integer.parseInt(values[1]);
                        break;
                    case "random_seed":
                        this.RANDOM_SEED = Long.parseLong(values[1]);
                        break;
                    case "move_time_limit":
                        this.MOVE_TIME_LIMIT = Integer.parseInt(values[1]);
                        break;
                    case "coin_spawn_period":
                        this.COIN_SPAWN_PERIOD = Integer.parseInt(values[1]);
                        break;
                    case "coin_spawn_volume":
                        this.COIN_SPAWN_VOLUME = Integer.parseInt(values[1]);
                        break;
                    case "map_size":
                        k = false;
                        break;
                }
            }
        }
    }

    public void readBotsAndCoinsInfo(String file1) throws IOException {
        File file = new File(file1);
        if (!file.exists()) System.out.println(file1 + " - этот файл пустой!");
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        String modeOfMatch = "";
        String info;
        Integer round = 0;
        if (file.length() == 0) {
            System.out.println(file1 + " - пустой!");
        }
        else {
            info = reader.readLine();
            //Проходимся по всем строкам файла, пока не встретим нужную информацию
            while (info != null) {
                String[] infoList = info.split(" ");
                String title = infoList[0];
                switch (title) {
                    case "mode" -> {
                        modeOfMatch = infoList[1];
                    }
                    case "bot_name" -> {
                        Integer id = Integer.parseInt(infoList[1]);   //id бота
                        String name = infoList[2];     //имя бота
                        Bot bot = new Bot(id, name);
                        bot.setIsAlive(true);
                        bot.setHighestNumberOfCoins(this.COIN_SPAWN_VOLUME);
                        bot.setCOIN_COLLECTED(0);
                        bot.setNumberOfAttacks(0);
                        bot.setNumberOfWinAttacks(0);
                        bot.setProcentageOfResearchMap(0);
                        bot.setEficiencyCoinCollection(0);
                        bot.setProcentageVictoryOfAttacks(0);
                        bot.setRoundOfDeath(0);
                        if (modeOfMatch.equals("DEATHMATCH"))
                            bot.setNumberOfDeathMatches(bot.getNumberOfDeathMatches() + 1);
                        else if (modeOfMatch.equals("FRIENDLY"))
                            bot.setNumberOfFriendlyMatches(bot.getNumberOfDeathMatches() + 1);
                        this.getBots().add(bot);
                    }
                    case "bot" -> {
                        Integer id1 = Integer.parseInt(infoList[1]);
                        int x = Integer.parseInt(infoList[2]);  //кооррдината x клетки, в которой находится бот.
                        int y = Integer.parseInt(infoList[3]);   //кооррдината x клетки, в которой находится бот.

                        //Получаем бота из списка всех ботов
                        for (int i = 0; i < this.getBots().size(); i++) {
                            Bot bot2 = this.getBots().get(i);
                            if (Objects.equals(bot2.getBOT_ID(), id1)) {
                                bot2.getKages().add(new Point(x, y));
                                bot2.getAllKagesWithRepetitions().add(new Point(x, y));
                            }
                        }
                    }
                    case "bot_coins" -> {
                        Integer id2 = Integer.parseInt(infoList[1]);   //id бота
                        Integer numberOfCoins = Integer.parseInt(infoList[2]);  //количество монет у него в данный момент

                        //Получаем бота из списка всех ботов
                        //Если количество монет, на которое нужно заменить занчение, равно 0, это значит, что бота съели,
                        //но все его монеты, которые он собрал, никуда не делись, он будет участвовать в рейтинге.
                        if (numberOfCoins != 0) {
                            for (int i = 0; i < this.getBots().size(); i++) {
                                Bot botI = this.getBots().get(i);
                                if (Objects.equals(botI.getBOT_ID(), id2)) {
                                    botI.setCOIN_COLLECTED(numberOfCoins);
                                }
                            }
                        }
                    }
                    case "coin" -> {
                        int x1 = Integer.parseInt(infoList[1]);
                        int y1 = Integer.parseInt(infoList[2]);
                        this.getMAP().getCoins().add(new Point(x1, y1));
                    }
                    case "round" -> {
                        round = round + 1;
                        //Для всех ботов в начале раунда добавляем количество монет, которое бот чисто теоретически мог получить в игре.
                        for (Bot item : this.getBots()) {
                            //Если наш робот жив, его еще не съели, количество монет добавляется.
                            if (item.getIsAlive()) {
                                item.setHighestNumberOfCoins(item.getHighestNumberOfCoins() + this.COIN_SPAWN_VOLUME);
                            }
                        }
                    }
                    case "attack" -> {
                        Integer id_bot1 = Integer.parseInt(infoList[1]);
                        Integer id_bot2 = Integer.parseInt(infoList[2]);
                        Bot bt1 = new Bot();
                        Bot bt2 = new Bot();
                        for (int i = 0; i < this.getBots().size(); i++) {
                            if (Objects.equals(this.getBots().get(i).getBOT_ID(), id_bot1)) {
                                bt1 = this.getBots().get(i);
                            }
                            if (Objects.equals(this.getBots().get(i).getBOT_ID(), id_bot2)) {
                                bt2 = this.getBots().get(i);
                            }
                        }
                        if (bt1.getCOIN_COLLECTED() > bt2.getCOIN_COLLECTED()) {
                            bt1.setNumberOfAttacks(bt1.getNumberOfAttacks() + 1);
                            bt2.setNumberOfAttacks(bt2.getNumberOfAttacks() + 1);
                            bt1.setNumberOfWinAttacks(bt1.getNumberOfWinAttacks() + 1);
                        } else if (bt1.getCOIN_COLLECTED() < bt2.getCOIN_COLLECTED()) {
                            bt1.setNumberOfAttacks(bt1.getNumberOfAttacks() + 1);
                            bt2.setNumberOfAttacks(bt2.getNumberOfAttacks() + 1);
                            bt2.setNumberOfWinAttacks(bt2.getNumberOfWinAttacks() + 1);
                        } else if (Objects.equals(bt1.getCOIN_COLLECTED(), bt2.getCOIN_COLLECTED())) {
                            bt1.setNumberOfAttacks(bt1.getNumberOfAttacks() + 1);
                            bt2.setNumberOfAttacks(bt2.getNumberOfAttacks() + 1);
                        }
                    }
                    //Нужно нам для того, чтобы записать точку, в которой побывал бот, собирая монеты
                    case "coin_collected" -> {
                        int x_coin = Integer.parseInt(infoList[1]);
                        int y_coin = Integer.parseInt(infoList[2]);
                        Integer botId = Integer.parseInt(infoList[3]);
                        Bot bot2 = new Bot(0, "name");
                        for (Bot item : this.getBots()) {
                            if (Objects.equals(item.getBOT_ID(), botId)) {
                                //Во множество клеток, в которых побывал бот, добавляем еще одну клетку, в которой он побывал, забрав монету.
                                item.addKage(new Point(x_coin, y_coin));
                                item.addAllKagesWithRepetitions(new Point(x_coin, y_coin));
                            }
                        }
                    }
                    case "match_over" -> {
                        //Когда матч для бота закончен, пришло время подсчитать некоторые его характеристики!
                        Integer ID = Integer.parseInt(infoList[1]);
                        Bot item = new Bot();
                        for (Bot item1 : this.getBots()) {
                            if (Objects.equals(item1.getBOT_ID(), ID)) {
                                item = item1;
                            }
                        }
                        //раунд смерти, когда бот выбыл из игры.
                        item.setRoundOfDeath(round);
                        item.setEficiencyCoinCollection(this.getMAP(), this.COIN_SPAWN_VOLUME, this.COIN_SPAWN_PERIOD, this.getMAP().getMINING_RADIUS());
                        item.setIsAlive(false);
                        if (this.getMATCH_MODE().equals("DEATHMATCH")) {
                            item.setProcentageVictoryOfAttacks();
                        }

                        //Нахождение процента исследования карты: количество клеток,  в которых побывал робот, делим на общее количество клеток на карте.
                        item.setProcentageOfResearchMap(this.getMAP());
                        this.getWhoWillLeaveTheMatchFaster().add(item);
                    }
                }
                info = reader.readLine();
            }
        }
    }
}
