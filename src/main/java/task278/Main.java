package task278;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(new File(IN_FILE));
        Writer fileWriter = new Writer(OUT_FILE);

        char[] s = scanner.nextLine().toCharArray();
        char[] t = scanner.nextLine().toCharArray();

        int sIndex = 0;
        int tIndex = 0;

        while (sIndex < s.length && tIndex < t.length) {
            if (s[sIndex] == (t[tIndex])) {
                sIndex++;
                tIndex++;
            } else {
                tIndex++;
            }
        }
        if (sIndex == s.length) {
            fileWriter.writeToFile("YES");
        } else {
            fileWriter.writeToFile("NO");
        }
    }

    class Writer {

        private String fileName;

        public Writer(String fileName) {
            this.fileName = fileName;
        }

        public void writeToFile(String str) {
            try (FileWriter writer = new FileWriter(fileName, false)) {
                writer.write(str);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}