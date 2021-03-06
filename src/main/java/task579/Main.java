package task579;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] argv) throws IOException {
        new Main().run();
    }

    private void run() throws FileNotFoundException {

        Reader fileReader = new Reader(IN_FILE);
        Writer fileWriter = new Writer(OUT_FILE);

        int[] sourceArray = fileReader.readIntArrayFromFile();

        Adder negativeNumbers = new Adder();
        Adder positiveNumbers = new Adder();

        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i] == 0) {
                continue;
            }
            if (sourceArray[i] > 0) {
                positiveNumbers.add(new Element(i, sourceArray[i]));
            } else {
                negativeNumbers.add(new Element(i, sourceArray[i]));
            }
        }

        if (negativeNumbers.sum > positiveNumbers.sum) {
            fileWriter.writeToFile(negativeNumbers);
        } else {
            fileWriter.writeToFile(positiveNumbers);
        }
    }

    class Adder {

        private int sum;
        private int size;
        private StringBuilder positionsStr;

        public Adder() {
            positionsStr = new StringBuilder();
        }

        private void add(Element element) {
            this.sum += Math.abs(element.getValue());
            this.positionsStr.append(element.getPosition()).append(" ");
            this.size++;
        }

        public int getSize() {
            return size;
        }

        public String getPositionsStr() {
            return positionsStr.toString();
        }
    }

    class Element {

        private final int positionCorrection = 1;
        private int position;
        private int value;

        public Element(int position, int value) {
            this.position = position + positionCorrection;
            this.value = value;
        }

        public int getPosition() {
            return position;
        }

        public int getValue() {
            return value;
        }
    }

    class Writer {

        private String fileName;

        public Writer(String fileName) {
            this.fileName = fileName;
        }

        public void writeToFile(Adder adder) {
            try (FileWriter writer = new FileWriter(fileName, false)) {
                writer.write(adder.getSize() + "\n");
                writer.write(adder.getPositionsStr());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    class Reader {

        private String fileName;

        public Reader(String fileName) {
            this.fileName = fileName;
        }

        public int[] readIntArrayFromFile() {
            try (Scanner scanner = new Scanner(new File(fileName))) {
                final int arrayLength = scanner.nextInt();
                int[] result = new int[arrayLength];
                int i = 0;
                while (scanner.hasNext()) {
                    result[i++] = scanner.nextInt();
                }
                return result;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
