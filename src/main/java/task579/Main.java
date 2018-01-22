package task579;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
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

    class Writer {

        private String fileName;

        public Writer(String fileName) {
            this.fileName = fileName;
        }

        public void writeToFile(Adder adder) throws FileNotFoundException {
            try (FileWriter writer = new FileWriter(fileName, false)) {
                writer.write(adder.getSize() + "\n");
                writer.write(adder.positionsToString());
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

        public int[] readIntArrayFromFile() throws FileNotFoundException {
            Scanner scanner = new Scanner(new File(fileName));
            final int arrayLength = scanner.nextInt();
            int[] result = new int[arrayLength];
            int i = 0;
            while (scanner.hasNext()) {
                result[i++] = scanner.nextInt();
            }
            return result;
        }
    }

    class Adder {

        private int sum;
        private List<Integer> positions;

        public Adder() {
            positions = new LinkedList<Integer>();
        }

        private void add(Element element) {
            final int positionCorrection = 1;
            this.sum += Math.abs(element.getValue());
            this.positions.add(element.getPosition() + positionCorrection);
        }

        public int getSize() {
            return positions.size();
        }

        public String positionsToString() {
            StringBuilder result = new StringBuilder();

            for (Integer position : positions) {
                result.append(position).append(" ");
            }
            return result.toString();
        }
    }

    class Element {

        private int position;
        private int value;

        public Element(int position, int value) {
            this.position = position;
            this.value = value;
        }

        public int getPosition() {
            return position;
        }

        public int getValue() {
            return value;
        }
    }
}
