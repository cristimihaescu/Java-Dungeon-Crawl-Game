package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
//        GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();
//        gameDatabaseManager.setup();
//        GameMap gameMap=new GameMap(2,2,CellType.FLOOR);
//        Cell cell=new Cell(gameMap,2,2,CellType.TREE);
//        Player player=new Player(cell);
////        PlayerDao playerDao = null;
////        PlayerModel playerModel=new PlayerModel("s",2,2);
////        playerDao.add(playerModel);
//        gameDatabaseManager.savePlayer(player);
        Main.main(args);
    }
}
