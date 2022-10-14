package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, int countSkeletons) {
        super(cell);
        damage = 5;
        id = countSkeletons;
    }
    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
    }
}
