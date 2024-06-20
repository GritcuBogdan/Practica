package org.example.practika.DepartamentHR.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.practika.DepartamentHR.API.Controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;


public class DepartamentHR implements Controller {


    @FXML
    private ToggleGroup toggleGroup;


    @FXML
    private Stage primaryStage;


    @FXML
    private ImageView homeButton;


    @FXML
    private RadioButton englezaButton;


    @FXML
    private RadioButton programareButton;


    @FXML
    private RadioButton hrButton;


    @FXML
    private AnchorPane englezaAnchorPane;


    @FXML
    private AnchorPane programareAnchorPane;


    @FXML
    private AnchorPane hrAnchorPane;


    @FXML
    private TextField numeTextfield;


    @FXML
    private TextField prenumeTextfield;


    @FXML
    private TextField idnpTextField;


    @FXML
    private TextField numeTextfield2;


    @FXML
    private TextField prenumeTextfield2;


    @FXML
    private TextField idnpTextfield2;


    @FXML
    private TextField numeTextfield3;


    @FXML
    private TextField prenumeTextfield3;


    @FXML
    private TextField idnpTextfield3;


    @FXML
    private Button submit;


    @FXML
    private Button submit2;


    @FXML
    private Button submit3;


    private File selectedFile;

    private final String specialFolderPath = "D:\\IntelliJ IDEA 2023.3.4\\Practika\\src\\main\\resources\\org\\example\\practika";

    public DepartamentHR() {
        // Create the special folder if it does not exist
        File specialFolder = new File(specialFolderPath);
        if (!specialFolder.exists()) {
            specialFolder.mkdirs();
        }
    }

    @FXML
    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Save Data");
        fileChooser.setInitialDirectory(new File(specialFolderPath));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            selectedFile = file;
        }
    }

    @FXML
    private void initialize() {
        // Initialize the ToggleGroup
        toggleGroup = new ToggleGroup();


        // Assign ToggleGroup to RadioButtons
        englezaButton.setToggleGroup(toggleGroup);
        programareButton.setToggleGroup(toggleGroup);
        hrButton.setToggleGroup(toggleGroup);


        // Set initial visibility of AnchorPanes
        englezaAnchorPane.setVisible(false);
        programareAnchorPane.setVisible(false);
        hrAnchorPane.setVisible(false);


        // Listener for ToggleGroup changes
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                englezaAnchorPane.setVisible(false);
                programareAnchorPane.setVisible(false);
                hrAnchorPane.setVisible(false);
            } else {
                if (newValue == englezaButton) {
                    englezaAnchorPane.setVisible(true);
                    programareAnchorPane.setVisible(false);
                    hrAnchorPane.setVisible(false);
                } else if (newValue == programareButton) {
                    englezaAnchorPane.setVisible(false);
                    programareAnchorPane.setVisible(true);
                    hrAnchorPane.setVisible(false);
                } else if (newValue == hrButton) {
                    englezaAnchorPane.setVisible(false);
                    programareAnchorPane.setVisible(false);
                    hrAnchorPane.setVisible(true);
                }
            }
        });


        forceDigitsOnly(idnpTextField);
        forceDigitsOnly(idnpTextfield2);
        forceDigitsOnly(idnpTextfield3);

        // Add length limit listeners to relevant fields
        numeTextfield.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        prenumeTextfield.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        idnpTextField.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        numeTextfield2.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        prenumeTextfield2.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        idnpTextfield2.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        numeTextfield3.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        prenumeTextfield3.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
        idnpTextfield3.addEventFilter(KeyEvent.KEY_TYPED, this::limitTextFieldLength);
    }


    @FXML
    private void backToHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/practika/Start.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void submitForm() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("posturi.txt", true))) {
            if (englezaButton.isSelected()) {
                String nume = numeTextfield.getText();
                String prenume = prenumeTextfield.getText();
                String idnp = idnpTextField.getText();
                writer.write("Java developer: " + nume + " " + prenume + " " + idnp + "\n");
                numeTextfield.clear();
                prenumeTextfield.clear();
                idnpTextField.clear();
            } else if (programareButton.isSelected()) {
                String nume = numeTextfield2.getText();
                String prenume = prenumeTextfield2.getText();
                String idnp = idnpTextfield2.getText();
                writer.write("HR Manager: " + nume + " " + prenume + " " + idnp + "\n");
                numeTextfield2.clear();
                prenumeTextfield2.clear();
                idnpTextfield2.clear();
            } else if (hrButton.isSelected()) {
                String nume = numeTextfield3.getText();
                String prenume = prenumeTextfield3.getText();
                String idnp = idnpTextfield3.getText();
                writer.write("Paznic: " + nume + " " + prenume + " " + idnp + "\n");
                numeTextfield3.clear();
                prenumeTextfield3.clear();
                idnpTextfield3.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxTextFieldLength(TextField textField) {
        if (textField == idnpTextField || textField == idnpTextfield2 || textField == idnpTextfield3) {
            return 13;
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



    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

