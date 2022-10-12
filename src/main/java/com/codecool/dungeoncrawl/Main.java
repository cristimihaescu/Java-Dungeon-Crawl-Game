package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
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
    GameMap map = MapLoader.loadMap();
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

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
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
        int[] listOfSkeletons = new int[]{1, 1, -1};
        ///////////////////////////////////CUM ERA////////////////////////////////////
//        Random random = new Random();
////        List<Skeleton> newEnemyList = List.copyOf(map.getEnemyList());
//        for (Skeleton enemy : map.getEnemyList()) {
//            if (enemy.getHealth() <= 0) {
//                map.getEnemyList().remove(enemy);
//            } else {
////                enemy.move(listOfSkeletons[random.nextInt(3)], listOfSkeletons[random.nextInt(3)]);
//                enemy.move(enemy.getX()+ random.nextInt(2), enemy.getY()+ random.nextInt(2));
//            }
//        }
        //////////////////////////////////////////////////////////////////////////////////////
        int[] dxList = new int[]{0, 1, -1};
        Random random = new Random();
        List<Actor> newEnemyList = List.copyOf(map.getEnemyList());
        for (Actor enemy : newEnemyList) {
            if (enemy.getHealth() <= 0) {
                int enemyX = enemy.getX();
                int enemyY = enemy.getY();
                map.getEnemyList().remove(enemy);


            } else {
                enemy.move(dxList[random.nextInt(3)], dxList[random.nextInt(3)]);
            }
        }
        if (map.getPlayer().getHealth() <= 0) {
            System.exit(0);
        }
        refresh();
    }


    private void refresh() {
        //////////////////////////ASA ERA /////////////////////////////
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
/////////////////////////////////////////////////////////////////
//        actorStatus="";
//        mapStatus="";
//        for (Cell[] cell : map.getCells()) {
//            for (Cell cell1 : cell) {
//                if (cell1.getActor() instanceof Actor){
//                    System.out.println(String.valueOf(cell1.getActor().getTileName()));
//                    actorStatus += String.valueOf(cell1.getActor().getTileName());
//                }
//                else {
//                    actorStatus+="null";
//                }
//                actorStatus += ",";
//                mapStatus += cell1.getTileName();
//                mapStatus += ",";
//            }
//        }
//        System.out.println("////////////////////////////////////////");
//
//        if (player.getId()!=0 && ediCheck){
//            int dbHp = dbManager.getDbHp(player.getId());
//            map.getPlayer().setHealth(dbHp);
//            ediCheck = false;
//        }
//        if (Player.nextLevel) {
//            map = MapLoader.loadMap("/map2.txt");
//            Player.nextLevel = false;
//            level2 = true;
//            scene.setOnKeyPressed(this::onKeyPressed);
//        }
//        context.setFill(Color.BLACK);
//        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
//                Cell cell = map.getCell(x, y);
//                if (cell.getActor() != null) {
//                    Tiles.drawTile(context, cell.getActor(), x, y);
//                } else if (cell.getItem() != null) {
//                    Tiles.drawTile(context, cell.getItem(), x, y);
//                } else {
//                    Tiles.drawTile(context, cell, x, y);
//                }
//            }
//        }
//
//        healthLabel.setText("" + map.getPlayer().getHealth());
//        damageLabel.setText("" + map.getPlayer().getDamage());
    }


}
