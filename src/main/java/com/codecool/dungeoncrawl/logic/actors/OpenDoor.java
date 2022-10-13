package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class OpenDoor extends Actor {
    public OpenDoor(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "openDoor";
    }
}