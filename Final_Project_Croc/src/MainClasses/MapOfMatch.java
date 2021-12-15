package MainClasses;

import BasicImplementionClasses.Point;

import java.io.*;
import java.util.ArrayList;

public class MapOfMatch {
    Integer MAP_WIDTH;
    Integer MAP_HEIGHT;
    Integer VIEW_RADIUS;
    Integer MINING_RADIUS;
    Integer ATTACK_RADIUS;
    Long MAP_AREA;
    ArrayList<Point> blocks = new ArrayList<>();  //arraylist со всеми препятствиями на карте
    ArrayList<Point> coins = new ArrayList<>();   //arraylist со всеми монетами на карте

    public MapOfMatch() {}

    public ArrayList<Point> getCoins() {
        return coins;
    }

    public Integer getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public Integer getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public Integer getMAP_AREA() {
        return this.getMAP_HEIGHT() * this.getMAP_WIDTH();
    }

    public Integer getVIEW_RADIUS() {
        return VIEW_RADIUS;
    }

    public Integer getMINING_RADIUS() {
        return MINING_RADIUS;
    }

    public Integer getATTACK_RADIUS() {
        return ATTACK_RADIUS;
    }

    public ArrayList<Point> getBlocks() {
        return blocks;
    }


    public void readMapConfig(String file1) throws IOException {
        File file = new File(file1);
        if (!file.exists()) System.out.println(file1 + " - этот файл пустой!");
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        if (file.length() == 0) {
            System.out.println(file1 + " - пустой!");
        }
        else {
            boolean k = true;
            while (k) {
                String line = reader.readLine();
                String[] values = line.split(" ");
                String title = values[0];
                switch (title) {
                    case "map_size" -> {
                        this.MAP_WIDTH = Integer.parseInt(values[1]);
                        this.MAP_HEIGHT = Integer.parseInt(values[2]);
                    }
                    case "view_radius" -> this.VIEW_RADIUS = Integer.parseInt(values[1]);
                    case "mining_radius" -> this.MINING_RADIUS = Integer.parseInt(values[1]);
                    case "attack_radius" -> this.ATTACK_RADIUS = Integer.parseInt(values[1]);
                    case "block" -> {
                        Point block = new Point(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                        blocks.add(block);
                    }
                    case "bot_name" -> k = false;
                }
            }
        }
    }
}
