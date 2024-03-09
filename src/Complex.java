public class Complex implements NumberOperations<Complex>{
    private double realPart;
    private double imaginaryPart;

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }
    public static Complex parseComplex(String input) {
        // Разделение строки на действительную и мнимую части
        String[] parts = input.split("(?=[+-])");

        // Проверка на наличие мнимой части
        if (parts.length == 1) {
            // Если нет мнимой части, предполагаем, что мнимая часть равна 0
            double realPart = Double.parseDouble(parts[0]);
            return new Complex(realPart, 0);
        } else {
            // Если есть и действительная, и мнимая части
            double realPart = Double.parseDouble(parts[0]);
            // Удаление "i" из строки мнимой части и парсинг в double
            double imaginaryPart = Double.parseDouble(parts[1].replace("i", ""));
            return new Complex(realPart, imaginaryPart);
        }
    }
    @Override
    public Complex summ(Complex other) {
        return new Complex(this.realPart + other.realPart, this.imaginaryPart + other.imaginaryPart);
    }
    @Override
    public boolean isZero(){
        return realPart == 0 && imaginaryPart == 0;
    }

    @Override
    public Complex subtract(Complex other) {
        return new Complex(this.realPart - other.realPart, this.imaginaryPart - other.imaginaryPart);
    }


    @Override
    public Complex multiply(Complex other) {
        double real = this.realPart * other.realPart - this.imaginaryPart * other.imaginaryPart;
        double imaginary = this.realPart * other.imaginaryPart + this.imaginaryPart * other.realPart;
        return new Complex(real, imaginary);
    }


    @Override
    public Complex divide(Complex other) {
        double denominator = other.realPart * other.realPart + other.imaginaryPart * other.imaginaryPart;
        double real = (this.realPart * other.realPart + this.imaginaryPart * other.imaginaryPart) / denominator;
        double imaginary = (this.imaginaryPart * other.realPart - this.realPart * other.imaginaryPart) / denominator;
        return new Complex(real, imaginary);
    }


    @Override
    public void printNumber() {
        if (imaginaryPart < 0) {
            System.out.print("(" + realPart + imaginaryPart + "i) ");
        } else {
            System.out.print("(" + realPart + "+" + imaginaryPart + "i) ");
        }
    }
}
