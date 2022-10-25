package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Actor {
    public Key(Cell cell) {
        super(cell);
        damage = 0;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
