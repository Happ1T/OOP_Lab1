import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Matrix<T> {
    private ArrayList<ArrayList<T>> matrix;
    private int type = 1;
    public Matrix(int dimension) {
        matrix = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add((T) Integer.valueOf(0));
            }
            matrix.add(row);
        }
    }

    public void setNewMatrix(int dimension, int type) {
        this.type = type;
        matrix = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < dimension; i++) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                T element = switch (type) {
                    case 1 -> (T) Integer.valueOf(scanner.nextInt());
                    case 2 -> (T) Double.valueOf(scanner.nextDouble());
                    default ->(T) scanner.next("([-+]?\\d*\\.?\\d+)([-+])(\\d*\\.?\\d+)i");
                };
                row.add(element);
            }
            matrix.add(row);
        }
    }

    public void printMatrix() {
        int maxLength = getMaxElementLength();
        int dimension = matrix.size();
        for (ArrayList<T> row : matrix) {
            for (T element : row) {
                System.out.printf("%-" + (maxLength + 2) + "s", element);
            }
            System.out.println();
        }
    }

    public void transpose() {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        ArrayList<ArrayList<T>> transposed = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            ArrayList<T> newRow = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }
        matrix = transposed;
    }







    private int getMaxElementLength() {
        int maxLength = 0;
        for (ArrayList<T> row : matrix) {
            for (T element : row) {
                if (element != null) {
                    int length = String.valueOf(element).length();
                    if (length > maxLength) {
                        maxLength = length;
                    }
                }
            }
        }
        return maxLength;
    }


}