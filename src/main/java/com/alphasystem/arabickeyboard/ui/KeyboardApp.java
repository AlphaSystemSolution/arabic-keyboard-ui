package com.alphasystem.arabickeyboard.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;
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
        textArea.setFont(font("Traditional Arabic", BOLD, REGULAR, 30));
        textArea.setNodeOrientation(RIGHT_TO_LEFT);
        textArea.setFocusTraversable(false);
        textArea.setPrefColumnCount(30);
        //textArea.setPrefRowCount(10);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setVbarPolicy(AS_NEEDED);
        scrollPane.setHbarPolicy(AS_NEEDED);
        textArea.setStyle("-fx-border-color: transparent; -fx-border-radius: 2; -fx-border-insets: 6, 6, 6, 6; " +
                "-fx-border-style: solid inside, dotted outside;");

        Keyboard keyboard = new Keyboard(textArea);

        final VBox keyboardView = keyboard.view();
        keyboardView.setOnMouseClicked(event -> keyboardView.requestFocus());
        root.getChildren().addAll(keyboardView, scrollPane);

        primaryStage.setScene(scene);
        primaryStage.show();

        keyboard.setAccelerators();
        textArea.addEventFilter(KEY_TYPED, event -> {
            final String s = event.getCharacter();
            char c = s.charAt(0);
            if (c >= '\u0021' && c <= '\u007E') {
                event.consume();
            }
        });
    }
}
