import java.util.Random;
import java.util.Scanner;

public class Matrix {
    double[][] matrix;

    public Matrix(int dimensionX, int dimensionY){
        Random rn = new Random();
        int n = 10 - 1 + 1;
        matrix = new double[dimensionX][dimensionY];
        for (int i = 0; i<dimensionX; i++){
            for (int j = 0; j<dimensionY; j++){
                matrix[i][j] = rn.nextInt() % n +1;
            }
        }
    }
    private int getMaxElementLength() {
        int maxLength = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int length = String.valueOf(matrix[i][j]).length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }

    public void setNewMatrix(int dimensionX, int dimensionY){
        matrix = new double[dimensionX][dimensionY];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i<dimensionX; i++){
            for (int j = 0; j<dimensionY; j++){
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }
    public void printMatrix(){
        int maxLength = getMaxElementLength();
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<cols; j++){
                System.out.printf("%" + (maxLength + 3) + ".1f", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void transpose() {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        matrix = transposed;

    }
    public double getDeterminant(){
        if (matrix.length != matrix[0].length) return -1242335.23123;
        return determinant(matrix);
    }
    private double determinant(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows != cols) {
            throw new IllegalArgumentException("Matrix must be square");
        }

        if (rows == 1 && cols == 1) {
            return matrix[0][0];
        }

        int det = 0;
        for (int i = 0; i < cols; i++) {
            det += matrix[0][i] * cofactor(matrix, 0, i);
        }

        return det;
    }

    private  double cofactor(double[][] matrix, int row, int col) {
        return (int) Math.pow(-1, row + col) * minor(matrix, row, col);
    }

    private  double minor(double[][] matrix, int row, int col) {
        return determinant(getSubMatrix(matrix, row, col));
    }

    private  double[][] getSubMatrix(double[][] matrix, int rowToRemove, int colToRemove) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] subMatrix = new double[rows - 1][cols - 1];
        int r = 0, c = 0;
        for (int i = 0; i < rows; i++) {
            if (i == rowToRemove) {
                continue;
            }
            c = 0;
            for (int j = 0; j < cols; j++) {
                if (j == colToRemove) {
                    continue;
                }
                subMatrix[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return subMatrix;
    }
    public int getRank() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rank = Math.min(rows, cols);

        for (int row = 0; row < rank; row++) {
            if (matrix[row][row] != 0) {
                for (int col = 0; col < rows; col++) {
                    if (col != row) {
                        double ratio = matrix[col][row] / matrix[row][row];
                        for (int i = 0; i < rank; i++) {
                            matrix[col][i] -= ratio * matrix[row][i];
                        }
                    }
                }
            } else {
                boolean reduce = true;
                for (int i = row + 1; i < rows; i++) {
                    if (matrix[i][row] != 0) {
                        swapRows(matrix, row, i);
                        reduce = false;
                        break;
                    }
                }

                if (reduce) {
                    rank--;

                    for (int i = 0; i < rows; i++) {
                        matrix[i][row] = matrix[i][rank];
                    }
                }

                row--;
            }
        }

        return rank;
    }

    private static void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
}
