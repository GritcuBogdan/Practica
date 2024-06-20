package org.example.practika.DepartamentHR.Controller;

import org.example.practika.DepartamentHR.Entity.Filiala;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ViewFilialaController {


    @FXML
    private ImageView homeButton;


    @FXML
    private TableView<Filiala> filialaTable;


    @FXML
    private TableColumn<Filiala, String> numeColumn;


    @FXML
    private TableColumn<Filiala, String> adresaColumn;


    @FXML
    private TableColumn<Filiala, String> telefonColumn;


    @FXML
    private ImageView addButton;


    @FXML
    private Label numeLabel;


    @FXML
    private TextField deleteTextField;


    @FXML
    private Button submitButton;


    @FXML
    private Label editLabel;


    private Stage primaryStage;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    private void initialize() {

        filialaTable.setEditable(true);



        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));



        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numeColumn.setOnEditCommit(this::handleEditCommit);


        adresaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adresaColumn.setOnEditCommit(this::handleEditCommit);


        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonColumn.setOnEditCommit(this::handleEditCommit);


        // Hide editLabel initially
        editLabel.setVisible(false);


        // Load data from file and populate TableView
        List<Filiala> filialeList = readFilialeFromFile("filiale.txt");
        filialaTable.getItems().addAll(filialeList);
    }


    @FXML
    private void handleDeleteEntry() {
        String numeToDelete = deleteTextField.getText().trim();


        Iterator<Filiala> iterator = filialaTable.getItems().iterator();
        while (iterator.hasNext()) {
            Filiala filiala = iterator.next();
            if (filiala.getNume().equals(numeToDelete)) {
                iterator.remove();
            }
        }


        saveFilialeToFile("filiale.txt");


        deleteTextField.clear();
        numeLabel.setVisible(false);
        deleteTextField.setVisible(false);
        submitButton.setVisible(false);
    }


    private void handleEditCommit(TableColumn.CellEditEvent<Filiala, String> event) {
        Filiala filiala = event.getRowValue();
        if (event.getTableColumn() == numeColumn) {
            filiala.setNume(event.getNewValue());
        } else if (event.getTableColumn() == adresaColumn) {
            filiala.setAdresa(event.getNewValue());
        } else if (event.getTableColumn() == telefonColumn) {
            filiala.setTelefon(event.getNewValue());
        }


        // Save changes to file
        saveFilialeToFile("filiale.txt");
        editLabel.setVisible(true);
    }


    @FXML
    private void switchToAddFiliala() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/AddFiliala.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDeleteImageClick(MouseEvent event) {
        numeLabel.setVisible(!numeLabel.isVisible());
        deleteTextField.setVisible(!deleteTextField.isVisible());
        submitButton.setVisible(!submitButton.isVisible());
    }


    @FXML
    private void handleUpdate(MouseEvent event) {

        List<Filiala> updatedFiliale = readFilialeFromFile("filiale.txt");
        filialaTable.getItems().clear();
        filialaTable.getItems().addAll(updatedFiliale);


        editLabel.setVisible(true);
    }


    @FXML
    private void backToHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/Start.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Filiala> readFilialeFromFile(String filePath) {
        List<Filiala> filialeList = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nume = parts[0].substring(parts[0].indexOf(":") + 1).trim();
                    String adresa = parts[1].substring(parts[1].indexOf(":") + 1).trim();
                    String telefon = parts[2].substring(parts[2].indexOf(":") + 1).trim();



                    Filiala filiala = new Filiala(nume, adresa, telefon);
                    filialeList.add(filiala);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return filialeList;
    }


    private void saveFilialeToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Filiala filiala : filialaTable.getItems()) {

                String line = "Nume: " + filiala.getNume() + ", Adresa: " + filiala.getAdresa() + ", Telefon: " + filiala.getTelefon();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
