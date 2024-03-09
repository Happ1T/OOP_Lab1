import java.util.ArrayList;
import java.util.Scanner;

public class Matrix{
    private ArrayList<ArrayList<NumberOperations>> matrix;
    private int type = 1;
    public Matrix(int dimension) {
        matrix = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            ArrayList<NumberOperations> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                row.add(new Number(Integer.valueOf(0)));
            }
            matrix.add(row);
        }
    }

    public void setNewMatrix(int dimension, int type) {
        this.type = type;
        matrix = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < dimension; i++) {
            ArrayList<NumberOperations> row = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                NumberOperations element = switch (type) {
                    case 1 ->  new Number(scanner.nextDouble());
                    default -> Complex.parseComplex(scanner.next("([-+]?\\d*\\.?\\d+)([-+])(\\d*\\.?\\d+)i"));
                };
                row.add(element);
            }
            matrix.add(row);
        }
    }

    public void printMatrix() {
        int maxLength = getMaxElementLength();
        int dimension = matrix.size();
        for (ArrayList<NumberOperations> row : matrix) {
            for (NumberOperations element : row) {
                element.printNumber();
            }
            System.out.println();
        }
    }
    public int getRank() {
        ArrayList<ArrayList<NumberOperations>> mat = new ArrayList<>(matrix);
        int rows = mat.size();
        int cols = mat.get(0).size();
        int rank = cols;

        for (int row = 0; row < rank-1; row++) {
            // Приведение нулевых элементов под текущим ведущим элементом к нулю
            for (int i = 0; i < rows; i++) {
                if (i != row) {
                    NumberOperations factor = (NumberOperations) mat.get(i).get(row).divide( mat.get(row).get(row));
                    for (int j = 0; j < rank; j++) {
                        mat.get(i).set(j, (NumberOperations) mat.get(i).get(j)
                                                                    .subtract(factor
                                                                        .multiply(mat.get(row).get(j))));
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            boolean allZeroes = true;
            for (int col = 0; col < cols; col++) {
                if (!mat.get(row).get(col).isZero()) {
                    allZeroes = false;
                    break;
                }
            }
            if (allZeroes) {
                rank--;
            }

        }

        return rank;
    }

    public void transpose() {
        int rows = matrix.size();
        int cols = matrix.get(0).size();

        ArrayList<ArrayList<NumberOperations>> transposed = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            ArrayList<NumberOperations> newRow = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }
        matrix = transposed;
        printMatrix();
    }
    public void getDeterminant(){
        determinant(matrix).printNumber();
        System.out.println();
    }
    private NumberOperations determinant(ArrayList<ArrayList<NumberOperations>> matrix) {
        ArrayList<ArrayList<NumberOperations>> mat = new ArrayList<>(matrix);
        int size = mat.size();


        if (size == 1) {
            return mat.get(0).get(0);
        }
        NumberOperations det = switch (type){
            case 1 -> new Number(0);
            default -> new Complex(0,0);
        };

        for (int i = 0; i < size ; i++) {
            det = (NumberOperations) det.summ(mat.get(0).get(i).multiply(cofactor(mat, 0, i)));
        }

        return det;
    }

    private NumberOperations cofactor(ArrayList<ArrayList<NumberOperations>> mat, int row, int col) {
        switch (type){
            case 1 : return (NumberOperations) minor(mat, row, col).multiply(new Number(Math.pow(-1, row + col) ));
            default: return (NumberOperations) minor(mat, row, col).multiply(new Complex(Math.pow(-1, row + col),0 ));
        }

    }

    private NumberOperations minor(ArrayList<ArrayList<NumberOperations>> mat, int row, int col) {
        return determinant(getSubMatrix(mat, row, col));
    }

    private ArrayList<ArrayList<NumberOperations>> getSubMatrix(ArrayList<ArrayList<NumberOperations>> mat, int rowToRemove, int colToRemove) {
        int rows = mat.size();
        int cols = mat.get(0).size();
        int r = 0, c = 0;
        ArrayList<ArrayList<NumberOperations>> subMatrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            if (i == rowToRemove) {
                continue;
            }
            c = 0;
            ArrayList<NumberOperations> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                if (j == colToRemove) {
                    continue;
                }
                row.add(c, mat.get(i).get(j));
                c++;
            }
            subMatrix.add(r,row);
            r++;
        }
        return subMatrix;
    }






    private int getMaxElementLength() {
        int maxLength = 0;
        for (ArrayList<NumberOperations> row : matrix) {
            for (NumberOperations element : row) {
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