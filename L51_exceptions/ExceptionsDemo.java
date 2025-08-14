package L51_exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

// Lesson 51: Exceptions
// Illustrates exception hierarchy, checked vs unchecked, creation, handling patterns, try-with-resources.
public class ExceptionsDemo {
    public static void main(String[] args) {
        System.out.println("=== Exceptions Demo ===");

        // 1. Runtime (unchecked) exception example
        try {
            int x = divide(10, 0); // will throw
            System.out.println(x); // not reached
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        }

        // 2. Checked exception handling with try-with-resources
        try {
            int lines = countLines("line1\nline2\n");
            System.out.println("Lines counted: " + lines);
        } catch (IOException e) {
            System.out.println("IO problem: " + e.getMessage());
        }

        // 3. Multi-catch & finally
        try {
            riskyOp("io");
            riskyOp("null");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Handled multi-catch: " + ex.getClass().getSimpleName());
        } finally {
            System.out.println("finally block always runs (cleanup)");
        }

        // 4. Custom unchecked exception
        try {
            validateAge(-5);
        } catch (ValidationException ve) {
            System.out.println("Validation failed: " + ve.getMessage());
        }

        // 5. Wrapping lower-level exception
        try {
            serviceLayer();
        } catch (ServiceException se) {
            System.out.println("Service failed root cause: " + se.getCause());
        }

        // 6. Demonstrate rethrow with improved type inference
        try {
            rethrowDemo();
        } catch (IOException e) {
            System.out.println("Rethrow captured IOException");
        }

        // 7. Showcase best practice: narrow catch & meaningful message
        try {
            parsePositiveInt("-7");
        } catch (NumberFormatException e) {
            System.out.println("Parsing error: " + e.getMessage());
        }
    }

    // Unchecked exception source
    private static int divide(int a, int b) {
        return a / b;
    }

    // Checked exception handling (try-with-resources ensures close)
    private static int countLines(String content) throws IOException {
        try (BufferedReader br = new BufferedReader(new StringReader(content))) {
            int count = 0;
            while (br.readLine() != null)
                count++;
            return count;
        }
    }

    private static void riskyOp(String mode) {
        switch (mode) {
            case "io" -> throw new IllegalStateException("Simulated state issue");
            case "null" -> throw new IllegalArgumentException("Null param simulated");
            default -> System.out.println("No issue");
        }
    }

    // Custom runtime exception
    static class ValidationException extends RuntimeException {
        ValidationException(String msg) {
            super(msg);
        }
    }

    private static void validateAge(int age) {
        if (age < 0)
            throw new ValidationException("age must be non-negative: " + age);
    }

    // Wrapping pattern (service layer)
    static class ServiceException extends Exception {
        ServiceException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    private static void serviceLayer() throws ServiceException {
        try {
            divide(3, 0);
        } catch (ArithmeticException e) {
            throw new ServiceException("Computation failed", e);
        }
    }

    private static void rethrowDemo() throws IOException {
        try {
            mightThrow();
        } catch (IOException e) { // rethrow (could enrich context)
            throw e;
        }
    }

    private static void mightThrow() throws IOException {
        if (System.nanoTime() % 2 == 0)
            throw new IOException("Random IO fluctuation");
    }

    private static int parsePositiveInt(String s) {
        int v = Integer.parseInt(s);
        if (v < 0)
            throw new NumberFormatException("Expected non-negative integer but got " + v);
        return v;
    }
}
