package org.example.practika;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import org.example.practika.DepartamentHR.Controller.AngajatController;
import org.example.practika.DepartamentHR.Entity.Angajat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.List;

public class MyJavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = createLabel("Departament HR");
        Button button = createButton("Start !");

        button.setOnAction(event -> switchToForm2(primaryStage));


        VBox root = new VBox();
        root.getChildren().addAll(label, button);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("HR Manager");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }


    private void switchToForm2(Stage primaryStage) {
        Label label = createLabel("Welcome to DepartamentHR");
        Button backButton = createButton("Înapoi");
        Button exitButton = createButton("Ieșire");
        Button inputButton = createButton("Introduceti un angajat nou");
        Button afisareButton = createButton("Afisare angajati");




        AngajatController angajatController = new AngajatController();
        afisareButton.setOnAction(event -> switchToShowForm(primaryStage,angajatController));
        inputButton.setOnAction(event -> switchToInputForm(primaryStage));
        exitButton.setOnAction(event -> primaryStage.close());
        backButton.setOnAction(event -> start(primaryStage));




        VBox root = new VBox();
        root.getChildren().addAll(label, backButton, inputButton,afisareButton, exitButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));




        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }


    private void switchToInputForm(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("angajat-input.fxml"));
            VBox root = fxmlLoader.load();
            Button backButton = createButton("Back");
            backButton.setMaxHeight(100);
            backButton.setMaxWidth(250);
            backButton.setOnAction(event -> switchToForm2(primaryStage));
            root.getChildren().addAll(backButton);
            root.setAlignment(Pos.CENTER);


            Scene scene = new Scene(root, 500, 500);




            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
        } catch (IOException e) {
            System.out.println("Eroare la citire/scriere");
            e.printStackTrace();
        }
    }

    private void switchToShowForm(Stage primaryStage, AngajatController angajatController) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneBuilder.fxml"));
            AnchorPane root = fxmlLoader.load();;
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.show();
        }catch (IOException e){
            System.out.println("Error 404");
            e.printStackTrace();
        }
//

//
//        ListView<String> listView = new ListView<>();
//        listView.setStyle("-fx-font-size: 20px");
//        listView.setMinWidth(500);
//        listView.setMaxWidth(1000);
//        listView.setMinHeight(500);
//
//        angajatController.loadAngajatiFromFile("angajati.txt");
//        List<Angajat> angajatList = angajatController.getAngajatList();
//
//        ObservableList<String> items = FXCollections.observableArrayList();
//        for (Angajat angajat : angajatList) {
//            items.add("Nume: "+ angajat.getNume() + " Prenume: " + angajat.getPrenume() + " IDNP: "+ angajat.getIdnp() + " Adresa: "
//            + angajat.getAdresa() + " Telefon: "+ angajat.getTelefon() + " Companie: "+ angajat.getCompanie()+ " Functie: "
//            + angajat.getFunctie()+ " Subdiviziune: "+ angajat.getSubdiviziunea());
//        }
//        listView.setItems(items);


    }



    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20px;");
        return label;
    }


    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

