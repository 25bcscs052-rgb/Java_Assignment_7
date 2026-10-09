import java.io.*;
public class FileStorage {

    private FileStorage() {
        //private constructor to prevent instantiation
    }

    public static void loadFromFile(String filename, MyList list) {
        File file = new File(filename);
        if (!file.exists()) {
            return; //returns if file does not exist, no crash as asked in question
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (null !=(line = reader.readLine())) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    int value = Integer.parseInt(line); //wrapper class of Integer to convert string to int
                    list.add(value);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed line in " + filename + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    public static void saveToFile(String filename, MyList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(String.valueOf(list.get(i)));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing " + filename + ": " + e.getMessage());
        }
    }
}
