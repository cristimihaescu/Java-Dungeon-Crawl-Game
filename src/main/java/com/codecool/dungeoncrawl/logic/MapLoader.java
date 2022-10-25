package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
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
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            countSkeletons++;
                            map.addSkeleton(new Skeleton(cell, countSkeletons));
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            map.setDoor(new Door(cell));
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            map.setTree(new Tree(cell));

                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
