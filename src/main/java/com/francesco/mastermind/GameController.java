package com.francesco.mastermind;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Class 'GameController' that contains all the methods to keep the game running.
 */
public class GameController {
    @FXML
    private Label mastermindLabel;
    @FXML
    private ComboBox<Color> colorBox0;
    @FXML
    private ComboBox<Color> colorBox1;
    @FXML
    private ComboBox<Color> colorBox2;
    @FXML
    private ComboBox<Color> colorBox3;
    @FXML
    private HBox resultBox;
    @FXML
    private Button guessButton;
    @FXML
    private Button newOrEndGameButton;
    @FXML
    private Label resultText;
    @FXML
    private VBox attemptsBox;

    private Game game;

    /**
     * Method that initialize the game:
     * <p>
     * - create a new Game object
     * <p>
     * - sets the default setting for all the components
     * <p>
     * - makes every comboBox have the right background-color for each color
     */
    @FXML
    public void initialize() {
        game = new Game();

        mastermindLabel.setFont(Font.loadFont(getClass().getResourceAsStream("fonts/EightgonItalic-Zpw6z.ttf"), 50));
        guessButton.setVisible(true);
        newOrEndGameButton.setText("Termina partita!");
        attemptsBox.getChildren().clear();
        resultBox.getChildren().clear();
        resultBox.getChildren().add(resultText);
        resultText.setText("");

        List<ComboBox<Color>> colorBoxes = List.of(
                colorBox0, colorBox1, colorBox2, colorBox3
        );
        for (ComboBox<Color> colorBox : colorBoxes) {
            colorBox.setItems(FXCollections.observableArrayList(Color.values()));
            colorBox.getSelectionModel().selectFirst();

            /*
            Custom colored view for choosing between colors
             */
            colorBox.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(Color color, boolean empty) {
                    super.updateItem(color, empty);
                    if (empty || color == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(color.name());
                        setStyle("-fx-background-color: " + color.getHex() + "; -fx-text-fill: black;");
                    }
                }
            });

            colorBox.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Color color, boolean empty) {
                    super.updateItem(color, empty);
                    if (empty || color == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(color.name());
                        setStyle("-fx-background-color: " + color.getHex() + "; -fx-text-fill: black;");
                    }
                }
            });
        }
    }

    /**
     * Method that handles the sending of a color sequence guess by the user.
     * <p>
     * It retrieves the color sequence by the comboBox, creates the ColorSequence object with the selected colors,
     * then it creates an attempt with that. Successively, it creates a row which contains the current attempt (using the
     * <code>createAttemptRot()</code> method) and adds it to che container of the attempts.
     * <p>
     * In the end, it checks if the sequence is correct (using the <code>isCorrect()</code> method).
     */
    @FXML
    protected void onTryButtonClick() {
        List<Color> sequence = new ArrayList<>(List.of(
                colorBox0.getValue(), colorBox1.getValue(), colorBox2.getValue(), colorBox3.getValue()
        ));
        ColorSequence guess = new ColorSequence(sequence.toArray(new Color[]{}));
        Attempt currentAttempt = game.makeAttempt(guess);

        HBox row = createAttemptRow(currentAttempt);
        attemptsBox.getChildren().addFirst(row);

        if (game.isCorrect(guess)) {
            /*
            Alternative version when the sequence is correct
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hai indovinato la sequenza!");
            alert.showAndWait();
             */
            guessButton.setVisible(false);
            newOrEndGameButton.setText("Nuova partita!");
            resultText.setFont(Font.font("System", FontWeight.BOLD, 20.0));
            resultText.setText("Congratulazioni! Hai indovinato la sequenza segreta in " + game.getAttemptsCount() + " tentativi!");
        }
        else {
            resultText.setFont(Font.font("System", FontWeight.NORMAL, 20.0));
            resultText.setText("Non hai indovinato la sequenza segreta. Riprova!");
        }
    }

    /**
     * Given an attempt, it creates a row with:
     * <p>
     * - circles that represent the sequence guess of the attempt
     * <p>
     * - the number of right colors in right position and the number of right colors in wrong position
     * <p>
     * The row is an HBox, and it is what this method returns
     * @param attempt the attempt to create the row
     * @return the HBox containing the graphical representation of the attempt
     */
    private HBox createAttemptRow(Attempt attempt) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);

        for (Color color : attempt.getSequence().getSequence()) {
            Circle circle = new Circle(10);
            circle.setFill(Paint.valueOf(color.getHex()));
            row.getChildren().add(circle);
        }

        /*
        Old version of result text
        Label result = new Label("Numero colori giusti posto giusto: " + attempt.getCountRightColorRightPosition() + " | Numero colori giusti posto sbagliato: " + attempt.getCountRightColorWrongPosition());
         */
        Label result = new Label("\t\t✅: " + attempt.getCountRightColorRightPosition() + "\t\t|\t\t❗: " + attempt.getCountRightColorWrongPosition());
        result.setStyle("-fx-font-weight: bold;");
        row.getChildren().add(result);

        return row;
    }

    /**
     * This method handles the clicking of the button to start a new game (when the current game has ended) or to
     * end the game (when the current game hasn't ended yet but the player gives up).
     * <p>
     * We use the same button to handle both the forced end of the game and the starting of a new game.
     * <p>
     * The clicking of the button with the text "Nuova partita!" calls the <code>initialize</code> method that starts a new game.
     * <p>
     * The clicking of the button with the text "Termina partita!" shows the correct sequence and change the text of the
     * button itself to "Nuova partita!".
     */
    public void onNewOrEndGameButtonClick() {
        if (newOrEndGameButton.getText().equals("Termina partita!")) {
            guessButton.setVisible(false);
            resultText.setFont(Font.font("System", FontWeight.NORMAL, 20.0));
            resultText.setText("La sequenza corretta era: ");
            for (Color color : game.getCorrectSequence().getSequence()) {
                Circle circle = new Circle(10);
                circle.setFill(Paint.valueOf(color.getHex()));
                resultBox.getChildren().add(circle);
            }
            newOrEndGameButton.setText("Nuova partita!");
        }
        else if (newOrEndGameButton.getText().equals("Nuova partita!")) {
            initialize();
        }
    }
}