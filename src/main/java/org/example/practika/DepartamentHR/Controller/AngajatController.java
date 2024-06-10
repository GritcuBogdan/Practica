package org.example.practika.DepartamentHR.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import org.example.practika.DepartamentHR.API.Controller;
import org.example.practika.DepartamentHR.Entity.Angajat;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;


public class AngajatController implements Controller {
    private final int MAX_TEXT_FIELD_LIMIT=35;


    @FXML
    private TextField numeInput;
    @FXML
    private TextField prenumeInput;
    @FXML
    private TextField idnpInput;
    @FXML
    private TextField adresaInput;
    @FXML
    private TextField telefonInput;
    @FXML
    private TextField functieInput;
    @FXML
    private TextField companieInput;
    @FXML
    private TextField subdiviziuneInput;


    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;


    @FXML
    private Label showLabel;


    private List<Angajat> angajatList = new ArrayList<>();


    public List<Angajat> getAngajatList() {
        return angajatList;
    }




    @FXML
    private void angajatInputSubmit() {
        String nume = numeInput.getText();
        String prenume = prenumeInput.getText();
        String idnp = idnpInput.getText();
        String adresa = adresaInput.getText();
        String telefon = telefonInput.getText();
        String functie = functieInput.getText();
        String companie = companieInput.getText();
        String subdiviziune = subdiviziuneInput.getText();




        Angajat newAngajat = new Angajat(nume, prenume, idnp, adresa, telefon, functie, companie, subdiviziune);
        angajatList.add(newAngajat);




        // Clear the input fields after submission
        clearInputFields();




        try(BufferedWriter writer = new BufferedWriter(new FileWriter("angajati.txt",true));){
            writer.write(newAngajat.toString());
            writer.newLine();
        }
        catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul angajati.txt");
        }




        for (Angajat angajat : angajatList) {
            System.out.println("Angajat: " + angajat.getNume() + " " + angajat.getPrenume());
        }
    }


    private void clearInputFields() {
        numeInput.clear();
        prenumeInput.clear();
        idnpInput.clear();
        adresaInput.clear();
        telefonInput.clear();
        functieInput.clear();
        companieInput.clear();
        subdiviziuneInput.clear();
    }




    @Override
    public int getMaxTextFieldLength(){
        return MAX_TEXT_FIELD_LIMIT;
    }




    @FXML
    private void handleKeyTyped(KeyEvent event) {
        limitTextFieldLength(event);
    }


    @Override
    public void limitTextFieldLength(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (textField == idnpInput && textField.getText().length() >= 13) {
            textField.setText(textField.getText(0, 13));
            event.consume();
        }
        else if(textField == telefonInput && textField.getText().length() >= 9){
            textField.setText(textField.getText(0,9));
            event.consume();
        }
        else if (textField.getText().length() >= getMaxTextFieldLength()) {
            textField.setText(textField.getText(0, getMaxTextFieldLength() - 1));
            event.consume();
        }


    }


    @FXML
    private void initialize() {
        idnpInput.setTextFormatter(new TextFormatter<>(forceDigitsOnly()));
        telefonInput.setTextFormatter(new TextFormatter<>(forceDigitsOnly()));
    }


    @Override
    public UnaryOperator<TextFormatter.Change> forceDigitsOnly() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            } else {
                TextField textField = (TextField) change.getControl();
                if (textField == idnpInput) {
                    errorLabel.setText("Puteti introduce doar cifre!");
                } else if (textField == telefonInput) {
                    errorLabel2.setText("Puteti introduce doar cifre!");
                }
                return null;
            }
        };
    }

    public void loadAngajatiFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 8) {
                    Angajat angajat = new Angajat(
                            parts[0].split(": ")[1],
                            parts[1].split(": ")[1],
                            parts[2].split(": ")[1],
                            parts[3].split(": ")[1],
                            parts[4].split(": ")[1],
                            parts[5].split(": ")[1],
                            parts[6].split(": ")[1],
                            parts[7].split(": ")[1]
                    );
                    angajatList.add(angajat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
