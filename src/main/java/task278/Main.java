package task278;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 23.01.2018.
 */
public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] args) throws FileNotFoundException {
        new Main().run();
    }

    private void run() throws FileNotFoundException {
        DnaCalculator dnaCalculator = new DnaCalculator();
        Scanner scanner = new Scanner(new File(IN_FILE));
        Writer writer = new Writer(OUT_FILE);

        if (dnaCalculator.canEvolve(scanner.nextLine().toCharArray(), scanner.nextLine().toCharArray())){
            writer.writeToFile("YES");
        }else {
            writer.writeToFile("NO");
        }
    }


    class DnaCalculator {

        private boolean canEvolve(char[] subSequence, char[] sequence) {
            DnaComparativeTable dnaComparativeTable = new DnaComparativeTable(subSequence, sequence);
            return dnaComparativeTable.hasSubsequence();
        }
    }

    class DnaComparativeTable {
        private char[] columnTitle;
        private char[] lineTitle;

        public DnaComparativeTable(char[] columnTitle, char[] lineTitle) {
            this.columnTitle = columnTitle;
            this.lineTitle = lineTitle;
        }

        public boolean hasSubsequence() {

            int currentLength = 0;
            int currentIndex = 0;
            while (currentLength < columnTitle.length && currentIndex < lineTitle.length) {
                if (columnTitle[currentLength] == (lineTitle[currentIndex])) {
                    currentLength++;
                    currentIndex++;
                } else {
                    currentIndex++;
                }
            }
            if (currentLength == columnTitle.length) {
                return true;
            } else {
                return false;
            }
        }
    }

    class Writer {

        private String fileName;

        public Writer(String fileName) {
            this.fileName = fileName;
        }

        public void writeToFile(String str) throws FileNotFoundException {
            try (FileWriter writer = new FileWriter(fileName, false)) {
                writer.write(str);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}