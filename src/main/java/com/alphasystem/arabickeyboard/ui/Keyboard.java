package com.alphasystem.arabickeyboard.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.COMMA;
import static javafx.scene.input.KeyEvent.KEY_TYPED;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BOLD;

/**
 * @author sali
 */
public class Keyboard {

    private List<Key> buttonRow1 = asList(
            new Key(DDAD.toUnicode(), FATHA.toUnicode(), Q),
            new Key(SAD.toUnicode(), FATHATAN.toUnicode(), W),
            new Key(THA.toUnicode(), DAMMA.toUnicode(), E),
            new Key(QAF.toUnicode(), DAMMATAN.toUnicode(), R),
            new Key(FA.toUnicode(), getWord(LAM, ALIF_HAMZA_BELOW).toUnicode(), T),
            new Key(GHAIN.toUnicode(), ALIF_HAMZA_BELOW.toUnicode(), Y),
            new Key(AIN.toUnicode(), valueOf('\u2018'), U),
            new Key(HHA.toUnicode(), valueOf('\u00F7'), I),
            new Key(KHA.toUnicode(), valueOf('\u00D7'), O),
            new Key(HA.toUnicode(), valueOf('\u061B'), P),
            new Key(JEEM.toUnicode(), valueOf('\u007B'), OPEN_BRACKET),
            new Key(DAL.toUnicode(), valueOf('\u007D'), CLOSE_BRACKET),
            new Key(THAL.toUnicode(), SHADDA.toUnicode(), BACK_SLASH));

    private List<Key> buttonRow2 = asList(
            new Key(SHEEN.toUnicode(), valueOf("\u005C\u005C"), A),
            new Key(SEEN.toUnicode(), valueOf(' '), S),
            new Key(YA.toUnicode(), valueOf('\u005D'), D),
            new Key(BA.toUnicode(), valueOf('\u005B'), F),
            new Key(LAM.toUnicode(), getWord(LAM, ALIF_HAMZA_ABOVE).toUnicode(), G),
            new Key(ALIF.toUnicode(), ALIF_HAMZA_ABOVE.toUnicode(), H),
            new Key(TA.toUnicode(), TATWEEL.toUnicode(), J),
            new Key(NOON.toUnicode(), valueOf('\u060C'), K),
            new Key(MEEM.toUnicode(), valueOf('\u002F'), L),
            new Key(KAF.toUnicode(), valueOf((char) 0x3B), SEMICOLON),
            new Key(TTA.toUnicode(), valueOf('\u0022'), QUOTE));

    private List<Key> buttonRow3 = asList(
            new Key(YA_HAMZA_ABOVE.toUnicode(), valueOf('\u007E'), Z),
            new Key(HAMZA.toUnicode(), SUKUN.toUnicode(), X),
            new Key(WAW_HAMZA_ABOVE.toUnicode(), KASRA.toUnicode(), C),
            new Key(RA.toUnicode(), KASRATAN.toUnicode(), V),
            new Key(getWord(LAM, ALIF).toUnicode(), getWord(LAM, ALIF_MADDAH).toUnicode(), B),
            new Key(ALIF_MAKSURA.toUnicode(), ALIF_MADDAH.toUnicode(), N),
            new Key(TA_MARBUTA.toUnicode(), valueOf('\u2019'), M),
            new Key(WAW.toUnicode(), valueOf('\u002C'), COMMA),
            new Key(ZAIN.toUnicode(), valueOf('\u002E'), PERIOD),
            new Key(DTHA.toUnicode(), valueOf('\u061F'), SLASH));

    private ToggleButton shift1 = createShiftButton();
    private ToggleButton shift2 = createShiftButton();
    private Node target;
    private VBox vBox;

    public Keyboard(Node target) {
        this.target = target;
        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        HBox row3 = addRow(buttonRow3).view();
        row3.getChildren().add(0, shift1);
        row3.getChildren().add(shift2);
        vBox.getChildren().addAll(addRow(buttonRow1).view(), addRow(buttonRow2).view(), row3);

        initBindings();
    }

    private static ToggleButton createShiftButton() {
        ToggleButton toggleButton = new ToggleButton("Shift");
        toggleButton.setStyle("-fx-base: beige;");
        toggleButton.setPrefSize(96, 48);
        toggleButton.setFont(Font.font("Candara", BOLD, REGULAR, 12));
        return toggleButton;
    }

    private void initBindings() {
        shift1.selectedProperty().bindBidirectional(shift2.selectedProperty());
        vBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> shift1.setSelected(event.isShiftDown()));
        buttonRow1.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
        buttonRow2.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
        buttonRow3.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
    }

    /**
     *
     */
    public void setAccelerators() {
        buttonRow1.forEach(Key::setAccelerator);
        buttonRow2.forEach(Key::setAccelerator);
        buttonRow3.forEach(Key::setAccelerator);
    }

    private KeyboardRow addRow(List<Key> row) {
        KeyboardRow keyboardRow = new KeyboardRow();
        row.forEach(key -> addRow(keyboardRow, key));
        return keyboardRow;
    }

    private void addRow(KeyboardRow keyboardRow, Key key) {
        final Button button = key.getButton();
        button.setOnAction(event -> {
            KeyEvent keyEvent = new KeyEvent(button, target, KEY_TYPED, key.getText(), key.getText(),
                    key.getKeyCode(), false, false, false, false);
            target.fireEvent(keyEvent);
            if (key.isShiftPressed()) {
                shift1.setSelected(false);
            }
        });
        keyboardRow.addKey(key);
    }

    public void shiftPressed() {
        shift1.setSelected(true);
    }

    public VBox view() {
        return vBox;
    }
}
