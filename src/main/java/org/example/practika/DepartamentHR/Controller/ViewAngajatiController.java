package org.example.practika.DepartamentHR.Controller;

import org.example.practika.DepartamentHR.Entity.Angajat;
import org.example.practika.HRApp;
import javafx.event.ActionEvent;
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


public class ViewAngajatiController {
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    private Stage primaryStage;


    @FXML
    private ImageView homeButton;




    @FXML
    private TableView<Angajat> angajatTable;


    @FXML
    private TableColumn<Angajat, String> numeColumn;


    @FXML
    private TableColumn<Angajat, String> prenumeColumn;


    @FXML
    private TableColumn<Angajat, String> idnpColumn;


    @FXML
    private TableColumn<Angajat, String> adresaColumn;


    @FXML
    private TableColumn<Angajat, String> telefonColumn;


    @FXML
    private TableColumn<Angajat, String> functieColumn;


    @FXML
    private TableColumn<Angajat, String> companieColumn;


    @FXML
    private TableColumn<Angajat, String> subdiviziuneColumn;


    @FXML
    private ImageView addButton;




    @FXML
    private Label idnpLabel;


    @FXML
    private TextField deleteTextField;


    @FXML
    private Button submitButton;


    private void switchToAddAngajat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/AddAngajat.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    private void initialize() {

        angajatTable.setEditable(true);


        addButton.setOnMouseClicked(e -> switchToAddAngajat());
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        idnpColumn.setCellValueFactory(new PropertyValueFactory<>("idnp"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        functieColumn.setCellValueFactory(new PropertyValueFactory<>("functie"));
        companieColumn.setCellValueFactory(new PropertyValueFactory<>("companie"));
        subdiviziuneColumn.setCellValueFactory(new PropertyValueFactory<>("subdiviziunea"));


        // Make columns editable
        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numeColumn.setOnEditCommit(this::handleEditCommit);


        prenumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenumeColumn.setOnEditCommit(this::handleEditCommit);


        idnpColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idnpColumn.setOnEditCommit(this::handleEditCommit);


        adresaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adresaColumn.setOnEditCommit(this::handleEditCommit);


        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonColumn.setOnEditCommit(this::handleEditCommit);


        functieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        functieColumn.setOnEditCommit(this::handleEditCommit);


        companieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        companieColumn.setOnEditCommit(this::handleEditCommit);


        subdiviziuneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subdiviziuneColumn.setOnEditCommit(this::handleEditCommit);


        // Initially hide editLabel
        editLabel.setVisible(false);


        // Load data from file and populate table
        List<Angajat> angajatiList = readAngajatiFromFile("angajati.txt");
        angajatTable.getItems().addAll(angajatiList);
    }


    @FXML
    private void handleDeleteEntry(ActionEvent event) {
        String idnpToDelete = deleteTextField.getText().trim();


        Iterator<Angajat> iterator = angajatTable.getItems().iterator();
        while (iterator.hasNext()) {
            Angajat angajat = iterator.next();
            if (angajat.getIdnp().equals(idnpToDelete)) {
                iterator.remove();
            }
        }

        saveAngajatiToFile("angajati.txt");



        deleteTextField.clear();
        idnpLabel.setVisible(false);
        deleteTextField.setVisible(false);
        submitButton.setVisible(false);
    }




    private void handleEditCommit(TableColumn.CellEditEvent<Angajat, String> event) {
        Angajat angajat = event.getRowValue();

        if (event.getTableColumn() == numeColumn) {
            angajat.setNume(event.getNewValue());
        } else if (event.getTableColumn() == prenumeColumn) {
            angajat.setPrenume(event.getNewValue());
        } else if (event.getTableColumn() == idnpColumn) {
            angajat.setIdnp(event.getNewValue());
        } else if (event.getTableColumn() == adresaColumn) {
            angajat.setAdresa(event.getNewValue());
        } else if (event.getTableColumn() == telefonColumn) {
            angajat.setTelefon(event.getNewValue());
        } else if (event.getTableColumn() == functieColumn) {
            angajat.setFunctie(event.getNewValue());
        } else if (event.getTableColumn() == companieColumn) {
            angajat.setCompanie(event.getNewValue());
        } else if (event.getTableColumn() == subdiviziuneColumn) {
            angajat.setSubdiviziunea(event.getNewValue());
        }
        saveAngajatiToFile("angajati.txt");
        editLabel.setVisible(true);
    }




    private void saveAngajatiToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Angajat angajat : angajatTable.getItems()) {
                String line = angajat.getNume() + ";" +
                        angajat.getPrenume() + ";" +
                        angajat.getIdnp() + ";" +
                        angajat.getAdresa() + ";" +
                        angajat.getTelefon() + ";" +
                        angajat.getFunctie() + ";" +
                        angajat.getCompanie() + ";" +
                        angajat.getSubdiviziunea();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    private ImageView updateButton;


    @FXML
    private Label editLabel;


    @FXML
    private void handleDeleteImageClick(MouseEvent event) {
        idnpLabel.setVisible(!idnpLabel.isVisible());
        deleteTextField.setVisible(!deleteTextField.isVisible());
        submitButton.setVisible(!submitButton.isVisible());
    }


    @FXML
    private void handleUpdate(MouseEvent event) {
        // Handle update logic here
        List<Angajat> updatedAngajati = readAngajatiFromFile("angajati.txt");
        angajatTable.getItems().clear();
        angajatTable.getItems().addAll(updatedAngajati);

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


    private List<Angajat> readAngajatiFromFile(String filePath) {
        List<Angajat> angajatiList = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 8) {
                    Angajat angajat = new Angajat(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            parts[6].trim(),
                            parts[7].trim()
                    );
                    angajatiList.add(angajat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return angajatiList;
    }
}

