package com.app.main;

import Initializer.Initialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        Initialization.initialization();
        this.primaryStage = stage;
        Parent root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Scene loaded");
    }

    public static void switchScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public  static void goToLoginPage() throws IOException {
        switchScene("Login.fxml");
    }

    public  static void goToViewBoardPage() throws IOException {
        switchScene("ViewBoard.fxml");
    }

    public  static void goToEditProfilePage() throws IOException {
        switchScene("EditProfile.fxml");
    }

    public static void goToAddMenuPage() throws IOException {
        switchScene("AddMenu.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}