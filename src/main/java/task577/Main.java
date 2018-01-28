package task577;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {

        Reader fileReader = new Reader(IN_FILE);
        Writer fileWriter = new Writer(OUT_FILE);

        solve(fileReader, fileWriter);
    }

    private void solve(Reader fileReader, Writer fileWriter) throws IOException {

        int[] params = fileReader.readInts(2);
        final int expectedMatrixCount = params[0];
        final int expectedMatrixSize = params[1];

        params = fileReader.readInts(2);
        final int requiredRow = params[0];
        final int requiredColumn = params[1];

        params = fileReader.readInts(1);
        final int p = params[0];

        int[] resultMatrixRow = fileReader.readRequiredRow(requiredRow, expectedMatrixSize);

        for (int m = 1; m < expectedMatrixCount; m++) {
            fileReader.skipLine();

            int[] tempSumMatrix = new int[expectedMatrixSize];

            int[] currentMultipliedRow;
            for (int i = 0; i < expectedMatrixSize - 1; i++) {
                currentMultipliedRow = fileReader.readNextMatrixRow(expectedMatrixSize);
                for (int j = 0; j < expectedMatrixSize; j++) {
                    tempSumMatrix[j] += resultMatrixRow[i] * currentMultipliedRow[j];
                }
            }

            currentMultipliedRow = fileReader.readNextMatrixRow(expectedMatrixSize);
            for (int j = 0; j < expectedMatrixSize; j++) {
                tempSumMatrix[j] += resultMatrixRow[expectedMatrixSize - 1] * currentMultipliedRow[j];
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

        public int[] readInts(int count) throws IOException {
            return readNextMatrixRow(count);
        }

        public void skipLine() throws IOException {
            reader.readLine();
        }

        public int[] readRequiredRow(int requiredRow, int matrixSize) throws IOException {

            skipLine();

            int i = 1;
            while (i != requiredRow) {
                i++;
                skipLine();
            }

            int[] result = readNextMatrixRow(matrixSize);

            while (i != matrixSize) {
                i++;
                skipLine();
            }
            return result;
        }

        public int[] readNextMatrixRow(int matrixSize) throws IOException {

            char[] chars = reader.readLine().toCharArray();

            int[] result = new int[matrixSize];
            int k = 0;
            for (int i = 0; i < chars.length; i++) {
                char[] temp = new char[4];
                int j = 0;
                while (i < chars.length && chars[i] != ' ') {
                    temp[j++] = chars[i++];
                }
                result[k++] = Integer.parseInt(String.valueOf(temp).trim());
            }
            return result;
        }
    }
}
