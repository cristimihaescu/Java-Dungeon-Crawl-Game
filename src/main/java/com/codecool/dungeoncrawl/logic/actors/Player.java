package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {

    public void move(int dx, int dy) {

        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType()!= CellType.WALL&&
                (nextCell.getActor()==null||
                        nextCell.getActor().getTileName().equals("deadSkeleton")||
                        nextCell.getType() == CellType.KEY
                        )){
            if (nextCell.getType() == CellType.KEY) {
                nextCell.setType(CellType.FLOOR);
                addKey();
            }
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
        else if (nextCell.getType()!= CellType.WALL&& nextCell.getActor()!=null) {
            setHealth(getHealth() - nextCell.getActor().damage);
            nextCell.getActor().setHealth(nextCell.getActor().getHealth() - damage);
        }
    }

    public Player(Cell cell) {
        super(cell);
        damage = 50;
    }

    public String getTileName() {
        return "player";
    }
}
