import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {
    private char[] selectedWord;
    private char[] progression;
    private ArrayList<Character> guessedLetters = new ArrayList<Character>();
    private int errors = 0;
    public GameManager(ArrayList<String> wordBank) {
        errors = 0;
        selectedWord = wordBank.get((int)(Math.random() * wordBank.size())).toCharArray();
        progression = Arrays.copyOf(selectedWord, selectedWord.length);
        Arrays.fill(progression, ("_").charAt(0));
    }
    public String getWord() {
        return Arrays.toString(selectedWord);
    }
    public String getProgression() {
        return Arrays.toString(progression);
    }
    public String guessLetter(char letter) {
        if (guessedLetters.contains(letter)) {
            return "You already guessed that letter!";
        }
        guessedLetters.add(letter);
        boolean letterFound = false;
        for (int index = 0; index < selectedWord.length; index ++) {
            if (selectedWord[index] == letter) {
                progression[index] = letter;
                letterFound = true;
            }
        }
        if (!letterFound) {
            errors++;
            return "Sorry, that letter is not in the word!";
        }
        return "Good job! That letter is in the word!";
    }
    public String getGuessedLetters() {
        return guessedLetters.toString();
    }
    public int getErrors() {
        return errors;
    }
    public boolean isGuessed(char letter) {
        return guessedLetters.contains(letter);
    }
}
