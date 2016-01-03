package com.alphasystem.arabickeyboard.ui.skin;

import com.alphasystem.arabickeyboard.ui.Key;
import com.alphasystem.arabickeyboard.ui.KeyboardRow;
import com.alphasystem.arabickeyboard.ui.KeyboardView;
import com.alphasystem.arabickeyboard.ui.KeyboardView.OutputType;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.arabickeyboard.ui.Key.getText;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.input.KeyEvent.*;
import static javafx.scene.layout.Priority.ALWAYS;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BOLD;

/**
 * @author sali
 */
public class KeyboardSkin extends SkinBase<KeyboardView> {

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
            new Key(SEEN.toUnicode(), ALIF_KHAN_JAREEYA.toUnicode(), S),
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
            new Key(WAW.toUnicode(), valueOf('\u002C'), KeyCode.COMMA),
            new Key(ZAIN.toUnicode(), valueOf('\u002E'), PERIOD),
            new Key(DTHA.toUnicode(), valueOf('\u061F'), SLASH));

    private ToggleButton shift1;
    private ToggleButton shift2;
    private Button backspace;
    private Button spaceBar;
    private VBox vBox = new VBox();

    public KeyboardSkin(KeyboardView control) {
        super(control);

        initializeSkin();
        initBindings();
    }

    private static ToggleButton createShiftButton() {
        ToggleButton toggleButton = new ToggleButton("Shift");
        toggleButton.setStyle("-fx-base: beige;");
        toggleButton.setPrefSize(128, 48);
        toggleButton.setFont(font("Candara", BOLD, REGULAR, 12));
        return toggleButton;
    }

    public void setAccelerators() {
        buttonRow1.forEach(Key::setAccelerator);
        buttonRow2.forEach(Key::setAccelerator);
        buttonRow3.forEach(Key::setAccelerator);
        backspace.setOnAction(event -> initKeyEvent(backspace, KEY_PRESSED, CHAR_UNDEFINED, CHAR_UNDEFINED,
                BACK_SPACE, false, false, false, false));
        backspace.getScene().getAccelerators().put(new KeyCodeCombination(BACK_SPACE), () -> fire(backspace));
        spaceBar.setOnAction(event -> initKeyEvent(spaceBar, KEY_TYPED, " ", " ", SPACE, false, false, false, false));
        spaceBar.getScene().getAccelerators().put(new KeyCodeCombination(SPACE), () -> fire(spaceBar));
    }

    public void shiftPressed() {
        shift1.setSelected(true);
    }

    private void initializeSkin() {
        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        shift1 = createShiftButton();
        shift2 = createShiftButton();
        backspace = createButton("Backspace");
        backspace.setPrefSize(128, 48);
        spaceBar = createButton("");
        spaceBar.setMaxWidth(POSITIVE_INFINITY);
        HBox.setHgrow(spaceBar, ALWAYS);

        final HBox row2 = addRow(buttonRow2).view();
        row2.getChildren().add(backspace);

        final HBox row3 = addRow(buttonRow3).view();
        row3.getChildren().add(0, shift1);
        row3.getChildren().add(shift2);

        vBox.getChildren().addAll(addRow(buttonRow1).view(), row2, row3, createLastRow());

        getChildren().addAll(vBox);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-base: beige;");
        button.setFont(font("Candara", BOLD, REGULAR, 12));
        return button;
    }

    private KeyboardRow addRow(List<Key> row) {
        KeyboardRow keyboardRow = new KeyboardRow();
        row.forEach(key -> addRow(keyboardRow, key));
        return keyboardRow;
    }

    private void addRow(KeyboardRow keyboardRow, Key key) {
        final Button button = key.getButton();
        button.setOnAction(event -> {
            initKeyEvent(button, KEY_TYPED, key.getText(), key.getText(), key.getKeyCode(), false, false, false, false);
            if (key.isShiftPressed()) {
                shift1.setSelected(false);
            }
        });
        keyboardRow.addKey(key);
    }

    private void initKeyEvent(Button button, EventType<KeyEvent> eventType, String character,
                              String text, KeyCode keyCode, boolean shiftDown, boolean controlDown,
                              boolean altDown, boolean metaDown) {
        Node target = getSkinnable().getTarget();
        OutputType outputType = getSkinnable().getOutputType();
        KeyEvent keyEvent = new KeyEvent(button, target, eventType, getText(character, outputType),
                getText(text, outputType), keyCode, shiftDown, controlDown,
                altDown, metaDown);
        target.fireEvent(keyEvent);
    }

    private HBox createLastRow() {
        final HBox row5 = new HBox();
        row5.setSpacing(10);
        row5.setPadding(new Insets(10, 10, 10, 10));

        Button b1 = createButton("");
        b1.setPrefSize(64, 48);
        b1.setDisable(true);

        Button b2 = createButton("");
        b2.setPrefSize(64, 48);
        b2.setDisable(true);

        Button b3 = createButton("");
        b3.setPrefSize(64, 48);
        b3.setDisable(true);

        row5.getChildren().addAll(b1, b2, spaceBar, b3);

        return row5;
    }

    private void initBindings() {
        shift1.selectedProperty().bindBidirectional(shift2.selectedProperty());
        vBox.addEventFilter(KEY_PRESSED, event -> shift1.setSelected(event.isShiftDown()));
        vBox.addEventFilter(KEY_RELEASED, event -> shift1.setSelected(event.isShiftDown()));
        buttonRow1.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
        buttonRow2.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
        buttonRow3.forEach(key -> key.shiftPressedProperty().bind(shift1.selectedProperty()));
    }

    private void fire(Button button) {
        button.arm();
        button.fire();
        button.disarm();
    }

}
