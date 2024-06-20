package org.example.practika.DepartamentHR.Controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import org.example.practika.DepartamentHR.API.Controller;
import org.example.practika.HRApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.UnaryOperator;


public class AngajatController implements Controller {


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    private Stage primaryStage;


    @FXML
    private ImageView homeButton;




    @FXML
    private TextField numeField;


    @FXML
    private TextField prenumeField;


    @FXML
    private TextField idnpField;


    @FXML
    private TextField adresaField;


    @FXML
    private TextField telefonField;


    @FXML
    private TextField functieField;


    @FXML
    private TextField companieField;


    @FXML
    private TextField subdiviziuneField;

    @FXML
    private Label validationLabel;

    @Override
    public void limitTextFieldLength(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (textField.getText().length() >= getMaxTextFieldLength(textField)) {
            event.consume();
        }
    }

    @FXML
    private void initialize() {
        // Apply digit-only filters
        forceDigitsOnly(idnpField);
        forceDigitsOnly(telefonField);

        numeField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        prenumeField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        idnpField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        adresaField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        telefonField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        functieField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        companieField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        subdiviziuneField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
    }


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        String idnp = idnpField.getText();
        String adresa = adresaField.getText();
        String telefon = telefonField.getText();
        String functie = functieField.getText();
        String companie = companieField.getText();
        String altaCompanie = subdiviziuneField.getText();

        if (nume.isEmpty() || prenume.isEmpty() || idnp.isEmpty() || adresa.isEmpty() || telefon.isEmpty()
                || functie.isEmpty() || companie.isEmpty() || altaCompanie.isEmpty()) {
            validationLabel.setText("Completați toate câmpurile!");
            return;
        }


        // Save to angajati.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("angajati.txt", true))) {
            writer.println(nume + ";" + prenume + ";" + idnp + ";" + adresa + ";" + telefon + ";" + functie + ";" + companie + ";" + altaCompanie);
        } catch (IOException e) {
            e.printStackTrace();

        }



        numeField.clear();
        prenumeField.clear();
        idnpField.clear();
        adresaField.clear();
        telefonField.clear();
        functieField.clear();
        companieField.clear();
        subdiviziuneField.clear();
    }


    @FXML
    private void backToHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/Start.fxml"));
            Parent root = loader.load();
            HRApp.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getMaxTextFieldLength(TextField textField) {
        if(textField == idnpField){
            return 13;
        }
        else if (textField == telefonField){
            return 9;
        }
        else
        return 40;
    }


    @Override
    public void forceDigitsOnly(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        textField.setTextFormatter(formatter);
    }
}
