package com.codecool.dungeoncrawl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Win {
    public class ImageViewExample extends Application {
        public void start(Stage stage) throws IOException {
            //creating the image object
            InputStream stream = new FileInputStream("C:/Users/crist/Desktop/Win.jpg");
            Image image = new Image(stream);
            //Creating the image view
            ImageView imageView = new ImageView();
            //Setting image to the image view
            imageView.setImage(image);
            //Setting the image view parameters
            imageView.setX(10);
            imageView.setY(10);
            imageView.setFitWidth(575);
            imageView.setPreserveRatio(true);
            //Setting the Scene object
            Group root = new Group(imageView);
            Scene scene = new Scene(root, 595, 370);
            stage.setTitle("You won !");
            stage.setScene(scene);
            stage.show();
        }
}}
