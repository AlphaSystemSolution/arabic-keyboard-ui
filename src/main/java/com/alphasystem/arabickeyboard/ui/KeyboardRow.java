package com.alphasystem.arabickeyboard.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/**
 * @author sali
 */
public class KeyboardRow {

    private HBox hBox;

    public KeyboardRow() {
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
    }

    public HBox view() {
        return hBox;
    }

    public KeyboardRow addKey(Key key) {
        hBox.getChildren().addAll(key.getButton());
        return this;
    }
}
