package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, int countSkeletons) {
        super(cell);
        damage = 1;
        id = countSkeletons;
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }
}
