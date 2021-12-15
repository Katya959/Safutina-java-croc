package MainClasses;

import BasicImplementionClasses.Point;

import java.util.*;

public class Bot {
    Integer BOT_ID;
    String BOT_NAME;
    Integer COIN_COLLECTED = 0;
    Integer numberOfAttacks = 0;  //количество атак, в которых он участвует
    Integer numberOfWinAttacks = 0;   //количество успешных атак, в которых он побеждает
    Integer procentageVictoryOfAttacks = 0;   //Процент побед в атаках (%)
    Integer eficiencyCoinCollection = 0;     //Эффективность сбора монет (%)
    Integer procentageOfResearchMap = 0;   //Процент исследования карты (%)
    Integer highestNumberOfCoins = 0;  //наибольшее количество монет, которое мог собрать робот.
    Boolean isAlive = true;
    Integer roundOfDeath;   //Раунд, когда данный бот покинул игру (необходимо для подсчета эффективности сбора монет).
    // Каждый раунд к этому числу прибавляется coin_spawn_volume.
    Set<Point> kages = new HashSet();  //Посещенные клетки ботом; set используется для того, чтобы клетки были неповторяющиеся.
    //Количество клеток в этом множестве нужно для подсчета процента исследования карты.
    ArrayList<Point> allKagesWithRepetitions = new ArrayList<>();  //это все клетки, которые посещал бот, с повторениями; нужны для нахождения
    //эффективности сбора монет.
    Integer numberOfFriendlyMatches = 0;
    Integer numberOfDeathMatches = 0;

    public Bot() {
    }

    public Bot(Integer BOT_ID, String BOT_NAME) {
        this.BOT_ID = BOT_ID;
        this.BOT_NAME = BOT_NAME;
    }

    public Integer getNumberOfFriendlyMatches() {
        return numberOfFriendlyMatches;
    }

    public Integer getNumberOfDeathMatches() {
        return numberOfDeathMatches;
    }

    public void setNumberOfFriendlyMatches(Integer numberOfFriendlyMatches) {
        this.numberOfFriendlyMatches = numberOfFriendlyMatches;
    }

    public void setNumberOfDeathMatches(Integer numberOfDeathMatches) {
        this.numberOfDeathMatches = numberOfDeathMatches;
    }

    public Integer getRoundOfDeath() {
        return this.roundOfDeath;
    }

    public ArrayList<Point> getAllKagesWithRepetitions() {
        return this.allKagesWithRepetitions;
    }

    public void setRoundOfDeath(Integer roundOfDeath) {
        this.roundOfDeath = roundOfDeath;
    }

    public void addAllKagesWithRepetitions(Point point) {
        this.allKagesWithRepetitions.add(point);
    }

    public Integer getHighestNumberOfCoins() {
        return this.highestNumberOfCoins;
    }

    public Boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(Boolean aliveOrDead) {
        this.isAlive = aliveOrDead;
    }

    public void addKage(Point point) {
        this.kages.add(point);
    }

    public void setHighestNumberOfCoins(Integer coins) {
        this.highestNumberOfCoins = coins;
    }

    public Set<Point> getKages() {
        return this.kages;
    }

    public void setBOT_ID(Integer BOT_ID) {
        this.BOT_ID = BOT_ID;
    }

    public void setBOT_NAME(String BOT_NAME) {
        this.BOT_NAME = BOT_NAME;
    }

    public void setCOIN_COLLECTED(Integer COIN_COLLECTED) {
        this.COIN_COLLECTED = COIN_COLLECTED;
    }

    public void setProcentageVictoryOfAttacks() {
        this.procentageVictoryOfAttacks = this.getNumberOfWinAttacks() * 100 / this.getNumberOfAttacks();
    }

    public void setProcentageVictoryOfAttacks(Integer pr) {
        this.procentageVictoryOfAttacks = pr;
    }

    public void setEficiencyCoinCollection(Integer eficiencyCoinCollection) {
        this.eficiencyCoinCollection = eficiencyCoinCollection;
    }

    public void setEficiencyCoinCollection(MapOfMatch map, Integer coin_volume, Integer coin_period, Integer radius) {
        //Каким образом я рассчитывала: я брала то количество монет, которое собрал робот, и то количество монет, которое появлялось, учитывая mining_radius,
        //а также объем генерации монет и какое количество раундов - разница между повялениями новой порции монет.
        this.eficiencyCoinCollection = this.getCOIN_COLLECTED() * 100 / (coin_volume + (this.getRoundOfDeath() / (radius * coin_period)));
    }

    public void setNumberOfAttacks(Integer numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }

    public void setNumberOfWinAttacks(Integer numberOfWinAttacks) {
        this.numberOfWinAttacks = numberOfWinAttacks;
    }

    public Integer getBOT_ID() {
        return this.BOT_ID;
    }

    public String getBOT_NAME() {
        return this.BOT_NAME;
    }

    public Integer getCOIN_COLLECTED() {
        return this.COIN_COLLECTED;
    }

    public Integer getNumberOfAttacks() {
        return this.numberOfAttacks;
    }

    public Integer getNumberOfWinAttacks() {
        return this.numberOfWinAttacks;
    }

    public Integer getProcentageVictoryOfAttacks() {
        return this.procentageVictoryOfAttacks;
    }

    public Integer getEficiencyCoinCollection() {
        return this.eficiencyCoinCollection;
    }

    public void setProcentageOfResearchMap(Integer procentageOfResearchMap) {
        this.procentageOfResearchMap = procentageOfResearchMap;
    }

    public void setProcentageOfResearchMap(MapOfMatch map) {
        if ((this.getKages().size() % (map.getMAP_AREA() - map.getBlocks().size()) == 0)) {
            this.setProcentageOfResearchMap(this.getKages().size() * 100 / (map.getMAP_AREA() - map.getBlocks().size()));
        } else {
            this.setProcentageOfResearchMap(this.getKages().size() * 100 / (map.getMAP_AREA() - map.getBlocks().size()) + 1);
        }
    }

    public Integer getProcentageOfResearchMap() {
        return this.procentageOfResearchMap;
    }

    public static final Comparator<Bot> COMPARE_BY_COIN_COLLECTED = new Comparator<Bot>() {
        @Override
        public int compare(Bot b1, Bot b2) {
            return b1.getCOIN_COLLECTED() - b2.getCOIN_COLLECTED();
        }
    };

    //Бота сравниваются по Id; ведь если id равны, значит, боты тоже одинаковые (с одинаковым id двух разных ботов быть не может).
    @Override
    public boolean equals(Object o) {
        return (o instanceof Bot) && Objects.equals(((Bot) o).getBOT_ID(), this.getBOT_ID());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(BOT_ID);
        result = (int) (temp ^ (temp >>> 32));
        return result;
    }
}
