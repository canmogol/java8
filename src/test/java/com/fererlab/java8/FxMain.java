package com.fererlab.java8;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class FxMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768);

//        Media pick = new Media("file:///home/canm/Desktop/plane2.mp4");
        Media pick = new Media(new File("/home/canm/Desktop/plane2.mp4").toURI().toString());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(player);
        ((Group) scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}