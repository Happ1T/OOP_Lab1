public class Complex implements NumberOperations<Complex>{
    private double realPart;
    private double imaginaryPart;

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public Complex summ(Complex other) {
        return new Complex(this.realPart + other.realPart, this.imaginaryPart + other.imaginaryPart);
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
            System.out.println("(" + realPart + imaginaryPart + "i)");
        } else {
            System.out.println("(" + realPart + "+" + imaginaryPart + "i)");
        }
    }
}
