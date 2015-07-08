package com.alphasystem.arabickeyboard.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static javafx.beans.binding.Bindings.when;
import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BOLD;
import static javafx.scene.text.TextAlignment.CENTER;

/**
 * @author sali
 */
public class Key {

    private final Button button;
    private final BooleanProperty shiftPressed;
    private KeyCode keyCode;

    public Key(String defaultText, String alternateText, KeyCode keyCode) {
        button = new Button();
        button.setStyle("-fx-base: beige;");
        button.setPrefSize(48, 16);
        button.setNodeOrientation(RIGHT_TO_LEFT);
        button.setTextAlignment(CENTER);
        button.setFont(font("Traditional Arabic", BOLD, REGULAR, 24));
        shiftPressed = new SimpleBooleanProperty();
        this.keyCode = keyCode;

        String at = alternateText != null ? alternateText : defaultText;

        button.textProperty().bind(when(shiftPressedProperty()).then(at).otherwise(defaultText));
        setShiftPressed(false);
    }

    public boolean isShiftPressed() {
        return shiftPressed.get();
    }

    public void setShiftPressed(boolean shiftPressed) {
        this.shiftPressed.set(shiftPressed);
    }

    public void setAccelerator() {
        if (keyCode != null) {
            button.getScene().getAccelerators().put(new KeyCodeCombination(keyCode), () -> {
                button.arm();
                button.fire();
                button.disarm();
            });
        }
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public Button getButton() {
        return button;
    }

    public BooleanProperty shiftPressedProperty() {
        return shiftPressed;
    }

    public String getText() {
        return button.getText();
    }
}
