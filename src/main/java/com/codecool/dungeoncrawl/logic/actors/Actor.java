package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.control.Alert;

import java.awt.event.KeyEvent;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 100;
    private int key = 0;
    protected int id;
    protected int damage;

    private int countSkeleton = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Alert a = new Alert(Alert.AlertType.NONE);
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        if (nextCell.getType() == CellType.KEY) {
            System.out.println("Bye!");
            nextCell.setType(CellType.FLOOR);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            key++;
        }
        if(nextCell.getType() == CellType.SKELETON){
            nextCell.setType(CellType.FLOOR);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            countSkeleton --;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getKey() {
        return key;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
