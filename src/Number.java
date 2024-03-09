public class Number implements NumberOperations<Number>{
    private double value;

    public Number(double value) {
        this.value = value;
    }
    @Override
    public Number summ(Number other) {
        return new Number(this.value + other.value);
    }

    @Override
    public Number subtract(Number other) {
        return new Number(this.value - other.value);
    }

    @Override
    public Number multiply(Number other) {
        return new Number(this.value * other.value);
    }

    @Override
    public Number divide(Number other) {
        if (other.value != 0) {
            return new Number(this.value / other.value);
        } else {
            throw new ArithmeticException("error");
        }
    }
    @Override
    public boolean isZero(){
        return value == 0;
    }

    @Override
    public void printNumber() {
        System.out.print(this.value + " ");
    }
}
