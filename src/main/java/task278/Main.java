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

        String s = scanner.nextLine(); //subsequence
        String t = scanner.nextLine(); //sequence

        if (dnaCalculator.canEvolve(s.toCharArray(), t.toCharArray())){
            writer.writeToFile("YES");
        }else {
            writer.writeToFile("NO");
        }
    }


    class DnaCalculator {

        private boolean canEvolve(char[] sArray, char[] tArray) {

            int sIndex = 0;
            int tIndex = 0;
            while (sIndex < sArray.length && tIndex < tArray.length) {
                if (sArray[sIndex] == (tArray[tIndex])) {
                    sIndex++;
                    tIndex++;
                } else {
                    tIndex++;
                }
            }
            if (sIndex == sArray.length) {
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