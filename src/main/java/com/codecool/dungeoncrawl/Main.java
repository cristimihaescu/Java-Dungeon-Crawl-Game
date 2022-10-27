package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;


public class Main extends Application {
    GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();
    int lvl = 1;
    PlayerModel player;
    public Stage generalStage;
    public GridPane ui=new GridPane();
    public String savedAt;
    GameMap map = MapLoader.loadMap(lvl);
    GameState gameState = new GameState(Integer.toString(map.getMapLvl()), savedAt, player);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button saveGame = new Button("Save Game");
    Button loadGame = new Button("Load Game");
    Label keyLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(saveGame, 2, 2);
        ui.add(loadGame, 2, 4);
        buttonAction();
        loadGame.setFocusTraversable(false);

        setLoadGame();
        saveGame.setFocusTraversable(false);
        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Items: "), 0, 1);
        ui.add(healthLabel, 1, 0);
        ui.add(keyLabel, 1, 1);


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        generalStage = primaryStage;
        gameDatabaseManager.setup();
        primaryStage.show();
        map.setMapLvl(lvl);
    }


    public boolean buttonAction() {
        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                gameDatabaseManager.savePlayer(map.getPlayer());
                gameDatabaseManager.saveGameState(gameState.getCurrentMap(), savedAt, gameDatabaseManager.savePlayer(map.getPlayer()));
//                gameDatabaseManager.s
                System.exit(0);
            }
        });
        return false;
    }

    public void setLoadGame() {

        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<GameState> dataFrom = gameDatabaseManager.getAll();
                for(int i=20; i<dataFrom.size()+20; i++) {
                    String column1 = dataFrom.get(i - 20).getPlayer().getPlayerName();
                    Button playerNameButton=new Button(column1);
                    ui.add(playerNameButton, 2, i);
                    playerNameButton.setFocusTraversable(false);
                    playerNameButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
//                            gameDatabaseManager.updateGameState(Integer.toString(lvl),,player);
                        }
                    });

//                    savedGameList.add(column1, 1, i);
                    System.out.println("sa;it");
                }
                }
        });
    }

    public void setPlayerOnPreviousSave(){

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                map.getPlayer().move(0, -1);
                refresh();
            }
            case DOWN -> {
                map.getPlayer().move(0, 1);
                refresh();
            }
            case LEFT -> {
                map.getPlayer().move(-1, 0);
                refresh();
            }
            case RIGHT -> {
                map.getPlayer().move(1, 0);
                refresh();
            }
        }
        int[] dxList = new int[]{0, 1, -1};
        Random random = new Random();
        List<Actor> newEnemyList = List.copyOf(map.getEnemyList());
        for (Actor enemy : newEnemyList) {
            if (enemy.getHealth() <= 0) {
                map.getEnemyList().remove(enemy);
                enemy.getCell().setType(CellType.FLOOR);
                enemy.getCell().setActor(null);
            } else {
                enemy.move(dxList[random.nextInt(3)], dxList[random.nextInt(3)]);
            }

        }

        if (map.getPlayer().getKey() == 1 && map.getEnemyList().size() == 0) {
            if ((map.getPlayer().getCell().getX() == map.getDoor().getCell().getX() + 1) &&
                    (map.getPlayer().getCell().getY() == map.getDoor().getCell().getY())) {
                map.getDoor().getCell().setType(CellType.OPEN_DOOR);
                map.getDoor().getCell().setActor(null);
                lvl++;
                Stage stage = createStage();
                stage.show();
            }

        }

        if (map.getPlayer().getHealth() <= 50) {
            lvl = 5;
            Stage stage2 = win();
            stage2.show();
//            System.exit(0);
        }
        refresh();
    }


    private void refresh() {

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        keyLabel.setText("" + map.getPlayer().getKey());
        map.setMapLvl(lvl);
        gameState.setCurrentMap(Integer.toString(map.setMapLvl(lvl)));
    }

    private Stage createStage() {
        map = MapLoader.loadMap(lvl);

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Items: "), 0, 1);
        ui.add(healthLabel, 1, 0);
        ui.add(keyLabel, 1, 1);
        ui.add(saveGame, 2, 2);
        buttonAction();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene2 = new Scene(borderPane);

        generalStage.setScene(scene2);
        refresh();
        scene2.setOnKeyPressed(this::onKeyPressed);
        generalStage.setTitle("Next lvl");
        return generalStage;
    }

    private Stage win() {
        map = MapLoader.loadMap(lvl);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);

        Scene scene3 = new Scene(borderPane);

        generalStage.setScene(scene3);
        refresh();
        scene3.setOnKeyPressed(this::onKeyPressed);


        generalStage.setTitle("win lvl");
        return generalStage;
    }

    public int getLvl() {
        return lvl;
    }
}
