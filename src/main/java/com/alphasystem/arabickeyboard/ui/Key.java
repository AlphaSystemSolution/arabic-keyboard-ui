package com.alphasystem.arabickeyboard.ui;

import com.alphasystem.arabickeyboard.ui.KeyboardView.OutputType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

import static com.alphasystem.arabickeyboard.ui.KeyboardView.OutputType.HTML;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static javafx.beans.binding.Bindings.when;
import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;
import static javafx.scene.input.KeyCombination.SHIFT_DOWN;
import static javafx.scene.input.KeyEvent.CHAR_UNDEFINED;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BOLD;
import static javafx.scene.text.TextAlignment.CENTER;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

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

    private static String toHtmlCodeString(char unicode) {
        String s = format("%04x", (int) unicode);
        return format("&#%s;", parseInt(s, 16));
    }

    public static String getText(String text, OutputType outputType) {
        if (!isBlank(text) && !CHAR_UNDEFINED.equals(text) && (outputType != null) && outputType.equals(HTML)) {
            char[] chars = text.toCharArray();
            if (!isEmpty(chars)) {
                StringBuilder builder = new StringBuilder();
                for (char aChar : chars) {
                    builder.append(toHtmlCodeString(aChar));
                }
                text = builder.toString();
            }
        }
        return text;
    }

    public boolean isShiftPressed() {
        return shiftPressed.get();
    }

    public void setShiftPressed(boolean shiftPressed) {
        this.shiftPressed.set(shiftPressed);
    }

    public void setAccelerator() {
        if (keyCode != null) {
            button.getScene().getAccelerators().put(new KeyCodeCombination(keyCode), () -> fire());
            button.getScene().getAccelerators().put(new KeyCodeCombination(keyCode, SHIFT_DOWN), () -> fire());
        }
        button.setOnMouseClicked(event -> {
            if (isShiftPressed()) {
                fire();
            }
        });
    }

    private void fire() {
        button.arm();
        button.fire();
        button.disarm();
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
