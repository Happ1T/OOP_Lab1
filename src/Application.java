import java.util.Scanner;
import java.util.stream.Stream;

public class Application {

    public void menu(){
        Matrix matrix = new Matrix(3);
        Scanner scanner = new Scanner(System.in);
        int button = 0;
        while (button != 6) {
            printMenu();
            button = scanner.nextInt();
            switch (button){
                case 1: {
                    System.out.println("Введите размерность матрицы");
                    int dimension = scanner.nextInt();
                    int type = 0;
                    while (type < 1 || type > 3)
                    {
                        System.out.println("Выберете тип хранимых данных в матрице: \n1.Целые\n2.Вещественные\n3.Комплексные");
                        type = scanner.nextInt();
                    }
                    matrix.setNewMatrix(dimension, type);
                    System.out.println("Матрица была задана");

                }break;
//                case 2: {
//                    double det = matrix.getDeterminant();
//                    if(det == -1242335.23123)
//                        System.out.println("Невозможно найти определитель прямоугольной матрицы!");
//                    else
//                        System.out.print("Определитель матрицы равен: " + det);
//
//                }break;
                case 3: {
                    System.out.println("Ранг матрицы равен: " + matrix.getRank());

                }break;
                case 4:{
                    System.out.println("Матрица была транспонирована");
                    matrix.transpose();

                }break;
                case 5: {
                    matrix.printMatrix();
                }break;
         }

       }
    }
   private void printMenu(){
       System.out.println("Нажмите");
       System.out.println("1 для задания новой матрицы");
       System.out.println("2 для получения определителя");
       System.out.println("3 для получения ранга");
       System.out.println("4 для транспонирования");
       System.out.println("5 для вывода матрицы");
       System.out.println("6 для выхода");
   }
}
