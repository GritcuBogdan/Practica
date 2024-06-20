package org.example.practika.DepartamentHR.Controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import org.example.practika.DepartamentHR.API.Controller;
import org.example.practika.HRApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.UnaryOperator;


public class FilialaController implements Controller {


    @FXML
    private TextField numeField;


    @FXML
    private TextField adresaField;


    @FXML
    private TextField telefonField;


    @FXML
    private Button submitButton;


    @FXML
    private Button backToHome;

    @FXML
    private Label validationLabel;
    private Stage primaryStage;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    private void initialize() {
        // Apply digit-only filters
        forceDigitsOnly(telefonField);

        // Add length limit listeners to relevant fields
        numeField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        adresaField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        telefonField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
    }


    @FXML
    private void handleSubmitButtonAction() {
        String nume = numeField.getText();
        String adresa = adresaField.getText();
        String telefon = telefonField.getText();

        if (nume.isEmpty() || adresa.isEmpty() || telefon.isEmpty()) {
            validationLabel.setText("Completați toate câmpurile!");
            return;
        }



        // Save data to a file
        saveToFile(nume, adresa, telefon);
        numeField.clear();
        adresaField.clear();
        telefonField.clear();
    }


    private void saveToFile(String nume, String adresa, String telefon) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("filiale.txt", true))) {
            writer.printf("Nume: %s, Adresa: %s, Telefon: %s%n", nume, adresa, telefon);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleHomeButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/Start.fxml"));
        Parent root = loader.load();
        HRApp controller = loader.getController();
        if (primaryStage != null) {
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(new Scene(root));
        } else {
            System.out.println("Primary stage is null.");
        }
    }

    @Override
    public int getMaxTextFieldLength(TextField textField) {
        if (textField == telefonField) {
            return 9;
        } else {
            return 40;
        }
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

    @Override
    public void limitTextFieldLength(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (textField.getText().length() >= getMaxTextFieldLength(textField)) {
            event.consume();
        }
    }



}
