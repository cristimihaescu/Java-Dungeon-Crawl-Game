package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

import static com.codecool.dungeoncrawl.Tiles.tileset;

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
                System.out.println("win");

                Stage stage = (Stage) createStage();
                stage.show();
            }
        }

        if (map.getPlayer().getHealth() <= 0) {
            System.exit(0);
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
//        context.setFill(Color.BLACK);
//        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        for (int x = 0; x < map.getWidth(); x++ ){
//            for (int y = 0; y < map.getHeight(); y++){
//                Cell cell = map.getCell(x, y);
//                if (cell.getActor() != null && cell.getActor().equals("@")){
//                    //player Tile 28, 0
//                    context.drawImage(tileset, 952,0, 32,32,y * 32,x * 32, 32,32);
//                } else{
//                    context.drawImage(tileset, 0,0, 32,32,y * 32,x * 32, 32,32);
//                }
//            }
//        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        keyLabel.setText("" + map.getPlayer().getKey());
    }

    private Stage createStage() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        stage.setTitle("Next lvl");
        return stage;
    }

}
