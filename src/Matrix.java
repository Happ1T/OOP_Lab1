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

    public int getRank(){
        return switch (type) {
            case 1, 2 -> getIntRank();
            default -> getComplexRank();
        };
    }
    private int getComplexRank(){
        ArrayList<ArrayList<T>> matrixCopy = new ArrayList<>();

        for (ArrayList<T> row : matrix) {
            ArrayList<T> newRow = new ArrayList<>(row);
            matrixCopy.add(newRow);
        }

        int rowCount = matrixCopy.size();
        int colCount = matrixCopy.get(0).size();
        int rank = 0;
        for (int row = 0; row < rowCount; row++) {
            boolean rowContainsNonZero = false;
            for (int col = 0; col < colCount; col++) {
                if (!matrixCopy.get(row).get(col).equals(new Complex(0, 0))) { // Сравнение с нулем
                    rowContainsNonZero = true;
                    break;
                }
            }
            if (rowContainsNonZero) {
                rank++;
                for (int i = row + 1; i < rowCount; i++) {
                    double ratio = matrixCopy.get(i).get(row).divide(matrixCopy.get(row).get(row)).abs(); // Вычисление абсолютного значения
                    for (int j = row; j < colCount; j++) {
                        Complex.multiply((Complex) matrixCopy.get(i).get(j));
                        Complex newValue = matrixCopy.get(i).get(j).subtract(matrixCopy.get(row).get(j).);
                        matrixCopy.get(i).set(j, (T) newValue);
                    }
                }
            }
        }
    }
    private int getIntRank() {

        ArrayList<ArrayList<T>> matrixCopy = matrix.stream()
                .map(ArrayList::new)
                .collect(Collectors.toCollection(ArrayList::new));

        int rowCount = matrixCopy.size();
        int colCount = matrixCopy.get(0).size();
        int rank = 0;

        for (int row = 0; row < rowCount; row++) {
            boolean rowContainsNonZero = false;
            for (int col = 0; col < colCount; col++) {
                System.out.println(matrixCopy.get(row).get(col));
                if (!Objects.equals(matrixCopy.get(row).get(col).toString(), "0.0")) {
                    rowContainsNonZero = true;
                    break;
                }
            }
            if (rowContainsNonZero) {
                rank++;
                for (int i = row + 1; i < rowCount; i++) {
                    T ratio = ((Number) matrixCopy.get(i).get(row)) / ((Number) matrixCopy.get(row).get(row));
                    for (int j = row; j < colCount; j++) {
                        T newValue = ((Number) matrixCopy.get(i).get(j)).doubleValue() -
                                ratio * ((Number) matrixCopy.get(row).get(j)).doubleValue();
                        matrixCopy.get(i).set(j, (T) Double.valueOf(newValue));
                    }
                }

            }
        }

        return rank;
    }


    private void swapRows(ArrayList<ArrayList<T>> matrix, int row1, int row2) {
        ArrayList<T> temp = new ArrayList<>(matrix.get(row1));
        matrix.set(row1, new ArrayList<>(matrix.get(row2)));
        matrix.set(row2, temp);
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

    private
}