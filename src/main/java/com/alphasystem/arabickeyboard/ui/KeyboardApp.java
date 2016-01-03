package com.alphasystem.arabickeyboard.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_TYPED;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BOLD;

/**
 * @author sali
 */
public class KeyboardApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Arabic Keyboard");

        final VBox root = new VBox(5);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root);

        TextArea textArea = new TextArea();
        textArea.setFont(font("Arabic Typesetting", BOLD, REGULAR, 36));
        textArea.setNodeOrientation(RIGHT_TO_LEFT);
        textArea.setFocusTraversable(false);
        textArea.setPrefColumnCount(50);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setVbarPolicy(AS_NEEDED);
        scrollPane.setHbarPolicy(AS_NEEDED);
        scrollPane.setNodeOrientation(RIGHT_TO_LEFT);
        textArea.setStyle("-fx-border-color: transparent; -fx-border-radius: 2; -fx-border-insets: 6, 6, 6, 6; " +
                "-fx-border-style: solid inside, solid outside;");

        KeyboardView keyboardView = new KeyboardView();
        keyboardView.setTarget(textArea);

        keyboardView.setStyle("-fx-border-color: transparent; -fx-border-radius: 2; -fx-border-insets: 6, 6, 6, 6; " +
                "-fx-border-style: solid inside, dotted outside;");
        keyboardView.setOnMouseClicked(event -> keyboardView.requestFocus());
        root.getChildren().addAll(keyboardView, scrollPane, createButtonPane(textArea));

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        keyboardView.initAccelerators();
        textArea.addEventFilter(KEY_TYPED, event -> {
            final String s = event.getCharacter();
            char c = s.charAt(0);
            if (c >= '\u0021' && c <= '\u007E') {
                event.consume();
            }
        });

        textArea.addEventFilter(KEY_PRESSED, event -> {
            if (event.isShiftDown()) {
                keyboardView.shiftPressed();
            }
        });

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth() * 0.60);
        primaryStage.setHeight(bounds.getHeight() * 0.80);
        primaryStage.show();
    }

    private HBox createButtonPane(TextArea textArea) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

        Button button = new Button("Copy");
        button.setFont(font("Georgia", 20));
        button.setOnAction(event -> {
            textArea.selectAll();
            textArea.copy();
        });
        hBox.getChildren().add(button);

        button = new Button("Paste");
        button.setFont(font("Georgia", 20));
        button.setOnAction(event -> textArea.paste());
        hBox.getChildren().add(button);

        return hBox;
    }

}
