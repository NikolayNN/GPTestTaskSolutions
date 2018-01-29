package task670;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

        Reader fileReader = new Reader(IN_FILE);
        Writer fileWriter = new Writer(OUT_FILE);

        int requiredPosition = fileReader.readIntFromFile();
        int currentNumber = 0;
        int currentPosition = 0;

        while (currentPosition <= requiredPosition) {

            if (hasRepeatedDigits(currentNumber)) {
                currentNumber++;
                continue;
            }
            if (requiredPosition == currentPosition) {
                break;
            } else {
                currentNumber++;
                currentPosition++;
            }
        }

        fileWriter.writeToFile(Integer.toString(currentNumber));
    }

    private boolean hasRepeatedDigits(Integer number) {

        Set<Integer> numberSet = new HashSet<>();

        int actualSize = 0;
        while (number != 0) {

            int tail = number % 10;
            numberSet.add(tail);
            actualSize++;

            number = number / 10;
        }

        if (numberSet.size() == actualSize) {
            return false;
        } else {
            return true;
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

    class Reader {

        private String fileName;

        public Reader(String fileName) {
            this.fileName = fileName;
        }

        public int readIntFromFile() {
            try (Scanner scanner = new Scanner(new File(fileName))) {
                return scanner.nextInt();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }
}
