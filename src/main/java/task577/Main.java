package task577;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";


    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {

        Reader fileReader = new Reader(IN_FILE);
        Writer fileWriter = new Writer(OUT_FILE);
        long start = System.currentTimeMillis();

        solve(fileReader, fileWriter);

        long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(usedBytes);

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

    }

    private void solve(Reader fileReader, Writer fileWriter) throws IOException {

        int[] params = fileReader.readInts();
        final int expectedMatrixCount = params[0];
        final int expectedMatrixSize = params[1];
        params = fileReader.readInts();
        final int requiredRow = params[0];
        final int requiredColumn = params[1];
        params = fileReader.readInts();
        final int p = params[0];

        int[] resultMatrixRow = fileReader.readRequiredRow(requiredRow, expectedMatrixSize);

        for (int m = 1; m < expectedMatrixCount; m++) {
            fileReader.skipLine();
            int[] tempSumMatrix = new int[expectedMatrixSize];

            for (int i = 0; i < expectedMatrixSize - 1; i++) {
                int[] matrixBRow = fileReader.readNextMatrixRow();
                for (int j = 0; j < expectedMatrixSize; j++) {
                    tempSumMatrix[j] += resultMatrixRow[i] * matrixBRow[j];
                }
            }

            int[] matrixBRow = fileReader.readNextMatrixRow();
            for (int j = 0; j < expectedMatrixSize; j++) {
                tempSumMatrix[j] += resultMatrixRow[expectedMatrixSize - 1] * matrixBRow[j];
                if (tempSumMatrix[j] >= p) {
                    tempSumMatrix[j] = tempSumMatrix[j] % p;
                }
            }
            resultMatrixRow = tempSumMatrix;
        }

        fileWriter.writeToFile(resultMatrixRow[requiredColumn - 1]);
    }

    class Writer {

        private FileWriter writer;

        public Writer(String fileName) {
            try {
                writer = new FileWriter(fileName, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void writeToFile(int number) {
            try {
                writer.write(Integer.toString(number));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Reader {

        BufferedReader reader;

        public Reader(String fileName) {
            try {
                this.reader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public int[] readInts() throws IOException {
            return readNextMatrixRow();
        }

        public void skipLine() throws IOException {
            reader.readLine();
        }

        public int[] readRequiredRow(int requiredRow, int matrixSize) throws IOException {

            skipLine();

            int i = 1;
            while (i != requiredRow) {
                i++;
                reader.readLine();
            }

            int[] result = readNextMatrixRow();

            while (i != matrixSize) {
                i++;
                reader.readLine();
            }
            return result;
        }

        public int[] readNextMatrixRow() throws IOException {

            return Arrays.stream(reader.readLine().split(" "))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
    }
}
