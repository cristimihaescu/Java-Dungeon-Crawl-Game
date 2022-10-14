package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;


public class Main extends Application {
    int lvl = 1;
    public Stage generalStage;
    public GridPane ui;
    GameMap map = MapLoader.loadMap(lvl);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label keyLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageCreator(primaryStage);
        primaryStage.setTitle("Dungeon Crawl");
        generalStage = primaryStage;
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
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
            lvl=5;
            Stage stage2 = win();
            stage2.show();
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
    }

    private Stage createStage() {
        map = MapLoader.loadMap(lvl);
        stageCreator(generalStage);
        generalStage.setTitle("Next lvl");
        return generalStage;
    }

    private void stageCreator(Stage generalStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Items: "), 0, 1);
        ui.add(healthLabel, 1, 0);
        ui.add(keyLabel, 1, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        Scene scene2 = new Scene(borderPane);
        generalStage.setScene(scene2);
        refresh();
        scene2.setOnKeyPressed(this::onKeyPressed);
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
}
