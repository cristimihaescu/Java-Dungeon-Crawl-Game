package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Door;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Tree;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;
    private Door door;
    private List<Skeleton> enemyList = new ArrayList<>();
    private Tree tree;
    private int mapLvl;


    public GameMap(int width, int height, Cell[][] cells, Player player, Door door, List<Skeleton> enemyList, Tree tree, GameMap map) {
        this.width = width;
        this.height = height;
        this.cells = cells;
        this.player = player;
        this.door = door;
        this.enemyList = enemyList;
        this.tree = tree;
        this.mapLvl = map.getMapLvl(); ;
    }

    public int getMapLvl() {
        return mapLvl;
    }

    public void setMapLvl(int mapLvl) {
        this.mapLvl = mapLvl;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Player getPlayer() {
        return player;
    }


    public void addSkeleton(Skeleton skeleton) {
        enemyList.add(skeleton);
    }

    public List<Skeleton> getEnemyList() {
        return enemyList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
