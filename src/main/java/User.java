import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        this.name = name;
        this.age = age;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public int getAge() {
        return age;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        String fileName = "fileU.txt";
        String line;
        String[] data;
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Пропускаємо перший рядок з назвами колонок
            br.readLine();

            // Читаємо файл по рядках
            while ((line = br.readLine()) != null) {
                // Розділяємо рядок на колонки
                data = line.split(" ");

                // Створюємо об'єкт User з колонок
                User user = new User(data[0], Integer.parseInt(data[1]));

                // Додаємо об'єкт User до списку
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Створюємо об'єкт Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("user.json")) {
            // Записуємо список об'єктів типу User у файл у форматі JSON
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}