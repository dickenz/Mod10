import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbersValidator {
    public static void main(String[] args) {
        String fileName = "file.txt";
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                Pattern pattern = Pattern.compile("(\\(\\d{3}\\)\\s\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
