package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.util.Objects;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int key = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Alert a=new Alert(Alert.AlertType.NONE);
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        if(nextCell.getType()==CellType.KEY){
            System.out.println("Bye!");
            nextCell.setType(CellType.FLOOR);
            cell.setActor(null);
            nextCell.setActor(this);
            cell=nextCell;
//            CellType.KEY=CellType.FLOOR;
            key++;

        }
    }

    public int getHealth() {
        return health;
    }
    public int getKey(){
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
