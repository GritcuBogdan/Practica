package org.example.practika.DepartamentHR.API;


import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;


import java.util.function.UnaryOperator;

public interface Controller {
    int getMaxTextFieldLength();

    default void limitTextFieldLength(TextField textField, KeyEvent keyEvent){
        int maxLength = getMaxTextFieldLength();
        if(textField.getText().length() >= maxLength){
            keyEvent.consume();
        }
    }

    void limitTextFieldLength(KeyEvent event);


    UnaryOperator<TextFormatter.Change> forceDigitsOnly();




}
