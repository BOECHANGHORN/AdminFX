package com.app.main;

import Initializer.Initialization;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>Main Class</h1>
 * The Main class is is a class stores the data fields of Main
 * and acts as driver class and responsible for initialization of the app
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */

public class Main extends Application {
    private static Stage primaryStage;

    /**
     * Initialize the stage object, app's backend file loading and Login scene initially
     *
     * @param stage the Stage object
     */
    @Override
    public void start(Stage stage) throws IOException {
        Initialization.initialization();
        primaryStage = stage;
        Parent root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Scene loaded");
    }

    /**
     * Load the FXML file to initialize the scene
     *
     * @param fxml the filename of FXML file
     */
    public static void switchScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Initialize ManageRole scene
     */
    public static void goToManageRolePage() throws IOException {
        switchScene("ManageRole.fxml");
    }

    /**
     * Initialize Login scene
     */
    public static void goToLoginPage() throws IOException {
        switchScene("Login.fxml");
    }

    /**
     * Initialize ViewBoard scene
     */
    public static void goToViewBoardPage() throws IOException {
        switchScene("ViewBoard.fxml");
    }

    /**
     * Initialize EditProfile scene
     */
    public static void goToEditProfilePage() throws IOException {
        switchScene("EditProfile.fxml");
    }

    /**
     * Initialize AddProperty scene
     */
    public static void goToAddPropertyPage() throws IOException {
        switchScene("AddProperty.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}