package task577;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final String IN_FILE = "input.txt";
    private final String OUT_FILE = "output.txt";


    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

        Reader fileReader = new Reader(IN_FILE);
        Writer fileWriter = new Writer(OUT_FILE);
        long start = System.currentTimeMillis();

        solve(fileReader, fileWriter);

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

    }

    private void solve(Reader fileReader, Writer fileWriter) {

        final int expectedMatrixCount = fileReader.readInt();
        final int expectedMatrixSize = fileReader.readInt();
        final int requiredRow = fileReader.readInt();
        final int requiredColumn = fileReader.readInt();
        final int p = fileReader.readInt();
        fileReader.nextLine();

        int[] resultMatrixRow = fileReader.readNextMatrix(expectedMatrixSize)[requiredRow-1];

        for (int i = 1; i < expectedMatrixCount; i++) {
            int[][] currentMatrix = fileReader.readNextMatrix(expectedMatrixSize);
            resultMatrixRow = Matrixes.multipleMatrixWithConstrain(resultMatrixRow, currentMatrix, expectedMatrixSize, p);
        }

        fileWriter.writeToFile(resultMatrixRow[requiredColumn - 1]);
    }

    static class Matrixes {

        public static int[] multipleMatrixWithConstrain(int[] matrixARow, int[][] matrixB, int matrixSize, int p) {

            int[] resultMatrix = new int[matrixSize];

                for (int i = 0; i < matrixSize; i++) {
                    int sum = 0;
                    for (int j = 0; j < matrixSize; j++) {
                       sum += matrixARow[j] * matrixB[j][i];
                    }
                    if (sum >= p) {
                        sum = sum % p;
                    }
                    resultMatrix[i] = sum;
                }
            return resultMatrix;
        }
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

        Scanner scanner;
        String fileName;

        public Reader(String fileName) {
            this.fileName = fileName;
            try {
                scanner = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public int readInt() {
            return scanner.nextInt();
        }

        public String nextLine() {
            return scanner.nextLine();
        }

        public int[][] readNextMatrix(int matrixSize) {

            scanner.nextLine();

            int[][] result = new int[matrixSize][matrixSize];

            int i = 0;
            while (i != matrixSize) {
                result[i++] = Arrays.stream(scanner.nextLine().split(" "))
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
            }
            return result;
        }
    }
}
