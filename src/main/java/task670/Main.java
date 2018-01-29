package task670;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {

        Scanner scanner = new Scanner(new File(IN_FILE));

        final int requiredPosition = scanner.nextInt();
        scanner.close();
        int currentNumber = 0;
        int currentPosition = 0;

        while (currentPosition < requiredPosition) {
            if (!hasRepeatedDigits(++currentNumber)) {
                currentPosition++;
            }
        }

        FileWriter writer = new FileWriter(OUT_FILE, false);
        writer.write(Integer.toString(currentNumber));
        writer.close();
    }

    private boolean hasRepeatedDigits(Integer number) {

        Set<Integer> digitsSetWithoutDuplicates = new HashSet<>(5);

        int actualDigitCount = 0;
        while (number != 0) {

            int digit = number % 10;
            digitsSetWithoutDuplicates.add(digit);
            actualDigitCount++;

            number = number / 10;
        }
        return digitsSetWithoutDuplicates.size() != actualDigitCount;
    }
}
