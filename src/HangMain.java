import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HangMain {
    // [ Variables ] //
    private static WordManager wordManager;
    public static GameManager gameManager;
    // Components
    private JTextField progressionField;
    private javax.swing.JPanel JPanel;
    private JTextField statusField;
    private JButton buttonNew;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel errorLabel;
    private JTextField guessedLetters;
    // [ Constructor ] //
    public HangMain() {
        // Action Listeners
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonNew(actionEvent);
            }
        });
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guessButton(actionEvent);
            }
        });
    }
    // [ Button Functions ] //
    // New Game
    private void buttonNew(ActionEvent e) {
        gameManager = new GameManager(wordManager.getBank());
        // Setting Text
        statusField.setText("I'm thinking of a word now! Try and guess it! Type ONE character in the guess box!");
        progressionField.setText(gameManager.getProgression());
        guessedLetters.setText(gameManager.getGuessedLetters());
        errorLabel.setText("Errors: " + gameManager.getErrors());
        // Setting Buttons
        guessButton.setEnabled(true);
        guessField.setEnabled(true);
    }
    // Guess
    private void guessButton(ActionEvent e) {
        char[] letters = guessField.getText().toUpperCase().toCharArray();
        if (letters.length == 1) {
            char letter = guessField.getText().toUpperCase().toCharArray()[0];
            if (Character.isAlphabetic(letter)) {
                String result = gameManager.guessLetter(letter);
                String progression = gameManager.getProgression();
                // Setting Fields
                errorLabel.setText("Errors: " + gameManager.getErrors());
                guessedLetters.setText(gameManager.getGuessedLetters());
                progressionField.setText(progression);
                // Win Condition
                if (progression.equals(gameManager.getWord())) {
                    statusField.setText("Congratulations! You guessed the word!");
                    guessButton.setEnabled(false);
                    guessField.setEnabled(false);
                } else {
                    statusField.setText(result);
                }
            } else {
                statusField.setText("Please enter a letter!");
            };
        } else {
            statusField.setText("Please enter ONE letter!");
        }
    }
    // [ Initialization ] //
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Guess The Number");
        frame.setContentPane(new HangMain().JPanel);
        frame.pack();
        frame.setVisible(true);

        try {
            System.out.println(System.getProperty("user.dir"));
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/src/WordBank");
            BufferedReader bufferReader = new BufferedReader(fileReader);
            wordManager = new WordManager(bufferReader);
            bufferReader.close();
        } catch (Exception error) {
            System.out.println("[HangMan] Error reading WordBank! Initializing random words instead!");
            String[] wordBank = {"HELLO", "MICROSOFT", "COMPUTER", "BYE", "FRIENDS", "SURFACE", "APPLE", "GOOGLE"};
            wordManager = new WordManager(wordBank);
        }
    }

}