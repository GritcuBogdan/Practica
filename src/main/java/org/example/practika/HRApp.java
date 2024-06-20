package org.example.practika;

import org.example.practika.DepartamentHR.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;




public class HRApp extends Application {




    private static Stage primaryStage;
    @FXML
    private Button addFiliale;


    @FXML
    private Button addAngajat;


    @FXML
    private  Button showAngajati;


    public static void setPrimaryStage(Stage primaryStage) {
        HRApp.primaryStage = primaryStage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/practika/Start.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    @FXML
    private void switchToAddAngajat() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/AddAngajat.fxml"));
        Parent root = loader.load();
        AngajatController controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");


        }
    }


    @FXML
    private void switchToShowAngajat() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/ShowAngajati.fxml"));
        Parent root = loader.load();
        ViewAngajatiController controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");


        }
    }


    @FXML
    private void switchToShowFiliala() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/ShowFiliala.fxml"));
        Parent root = loader.load();
        ViewFilialaController controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");


        }
    }


    @FXML
    private void switchToAddFiliala() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/AddFiliala.fxml"));
        Parent root = loader.load();
        FilialaController controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");
        }
    }


    @FXML
    private void switchToAddCurs() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/AddCursuri.fxml"));
        Parent root = loader.load();
        DepartamentHR controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");
        }
    }




    @FXML
    private void closeApp(){
        primaryStage.close();
    }


    @FXML
    private void onMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Posturi vacante")) {
            button.setStyle("-fx-background-color: #364F6B; -fx-border-color: #364F6B; -fx-border-width: 3 3 3 3;");
        } else {
            button.setStyle("-fx-background-color: #364F6B; -fx-border-color:  #364F6B; -fx-border-width: 3 3 0 3;");
        }
    }


    @FXML
    private void onMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Posturi vacante")) {
            button.setStyle("-fx-background-color: #1B262C; -fx-border-color: #364F6B; -fx-border-width:3 3 3 3;");
        } else {
            button.setStyle("-fx-background-color:  #1B262C; -fx-border-color: #364F6B; -fx-border-width: 3 3 0 3;");
        }
    }


    @FXML
    private void onMousePressed(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Posturi vacante")) {
            button.setStyle("-fx-background-color: #2C74B3; -fx-border-color: #364F6B; -fx-border-width: 3 3 3 3;");
        } else {
            button.setStyle("-fx-background-color: #2C74B3; -fx-border-color: #364F6B; -fx-border-width: 3 3 0 3;");
        }
    }


    @FXML
    private void onMouseReleased(MouseEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Afișează contracte")) {
            button.setStyle("-fx-background-color: #1B262C; -fx-border-color: #364F6B; -fx-border-width: 3 3 3 3;");
        } else {
            button.setStyle("-fx-background-color:  #1B262C; -fx-border-color: #364F6B; -fx-border-width: 3 3 0 3;");
        }
    }






    public static void main(String[] args) {
        launch(args);
    }
}
