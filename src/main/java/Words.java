import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Words {
    public static void main(String[] args) {
        String filename = "words.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(filename);
//        for (String word : wordFrequency.keySet()) {
//            System.out.println(word + " " + wordFrequency.get(word));
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordFrequency.entrySet());

        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static Map<String, Integer> countWordFrequency(String filename) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }
}