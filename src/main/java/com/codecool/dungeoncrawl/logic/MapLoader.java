package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    int lvl;

    public MapLoader(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public static GameMap loadMap(int lvl) {
        InputStream is;
        if (lvl == 1) {
            is = MapLoader.class.getResourceAsStream("/map.txt");
        } else if (lvl == 2) {
            is = MapLoader.class.getResourceAsStream("/map2.txt");
        } else {
            is = MapLoader.class.getResourceAsStream("/salut.txt");

        }


        assert is != null;
        Scanner scanner = new Scanner(is);

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int countSkeletons = 0;

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> cell.setType(CellType.EMPTY);
                        case '#' -> cell.setType(CellType.WALL);
                        case '.' -> cell.setType(CellType.FLOOR);
                        case 's' -> {
                            cell.setType(CellType.FLOOR);
                            countSkeletons++;
                            map.addSkeleton(new Skeleton(cell, countSkeletons));
                        }
                        case 'k' -> {
                            cell.setType(CellType.KEY);
                            new Key(cell);
                        }
                        case '@' -> {
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                        }
                        case 'd' -> {
                            cell.setType(CellType.FLOOR);
                            map.setDoor(new Door(cell));
                        }
                        case 't' -> {
                            cell.setType(CellType.TREE);
                            map.setTree(new Tree(cell));
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
