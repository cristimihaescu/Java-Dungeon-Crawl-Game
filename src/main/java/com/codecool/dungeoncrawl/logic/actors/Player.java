package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {

    public Player(Cell cell) {
        super(cell);
        damage = 50;
    }
    public String getTileName() {
        return "player";
    }

    public void move(int dx, int dy) {

        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (!isCellType(nextCell, CellType.WALL) && (isActorNull(nextCell) || isDeadSkeleton(nextCell) || isCellType(nextCell, CellType.KEY))) {
            if (isCellType(nextCell, CellType.KEY)) {
                nextCell.setType(CellType.FLOOR);
                addKey();
            }
            if (isCellType(nextCell, CellType.TREE)) {
                getCell().setActor(null);
                nextCell.setActor(this);
            }
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
        if (!isCellType(nextCell, CellType.WALL) && !isActorNull(nextCell)) {
            setHealth(getHealth() - nextCell.getActor().damage);
            nextCell.getActor().setHealth(nextCell.getActor().getHealth() - damage);
        }
    }

    private static boolean isDeadSkeleton(Cell nextCell) {
        return nextCell.getActor().getTileName().equals("deadSkeleton");
    }

    private static boolean isCellType(Cell nextCell, CellType cellType) {
        return nextCell.getType() == cellType;
    }

    private static boolean isActorNull(Cell nextCell) {
        return nextCell.getActor() == null;
    }
}
