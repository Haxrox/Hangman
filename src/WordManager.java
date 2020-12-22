import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WordManager {
    ArrayList<String> wordBank;
    public WordManager(BufferedReader words) throws Exception {
         wordBank = new ArrayList<String>();
        try {
            while(words.readLine() != null) {
                wordBank.add(words.readLine());
            }
        } catch (Exception error) {
            System.out.println("[WordManager] Failed to read a line!");
        }
    }
    public WordManager(String[] words) {
        ArrayList<String> wordBank = new ArrayList<String>();
        for (int index = 0; index < words.length; index ++) {
            wordBank.add(words[index]);
        }
    }
    public ArrayList<String> getBank() {
        return wordBank;
    }
}
