package L00_playground;

public class Playground {
    public static void main(String[] args) {
        System.out.println("Welcome to the Java Playground!");
        Calculator calc = new Calculator();
        int sum = calc.add(5, 3);
        System.out.println("Sum: " + sum);
    }
}

class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int subtract(int a, int b) {
        return a - b;
    }

    int multiply(int a, int b) {
        return a * b;
    }

    double divide(int a, int b) {
        if (b == 0) {
            System.out.println("Division by zero error");
            return 0;
        }
        return (double) a / b;
    }
}
