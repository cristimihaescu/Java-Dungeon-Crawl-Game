package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tree extends Actor {
    public Tree(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "tree";
    }
}
