package L52_checked_unchecked;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Lesson 52: Checked vs Unchecked Exceptions
// Shows hierarchy, differences, when to use which, and patterns.
public class CheckedUncheckedDemo {
    public static void main(String[] args) {
        System.out.println("=== Checked vs Unchecked Demo ===");

        // 1. Unchecked exception (RuntimeException)
        try {
            int v = unsafeDivide(5, 0);
            System.out.println(v);
        } catch (ArithmeticException e) {
            System.out.println("Caught runtime exception: " + e);
        }

        // 2. Checked exception (IOException)
        try {
            long bytes = sizeOf("non-existent-file.txt");
            System.out.println("size=" + bytes);
        } catch (IOException e) {
            System.out.println("Checked IOException handled: " + e.getMessage());
        }

        // 3. Wrap checked to unchecked at boundary
        try {
            repositorySave("data");
        } catch (RepositoryException e) {
            System.out.println("RepositoryException (unchecked) -> cause: " + e.getCause());
        }

        // 4. API choice demo: validation should throw unchecked
        try {
            requirePositive(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    // Unchecked
    static int unsafeDivide(int a, int b) {
        return a / b;
    }

    // Checked path (declares throws)
    static long sizeOf(String path) throws IOException {
        return Files.size(Path.of(path));
    }

    // Wrapping strategy
    static class RepositoryException extends RuntimeException {
        RepositoryException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    static void repositorySave(String content) {
        try {
            // Simulate IO error
            throw new IOException("disk full");
        } catch (IOException e) {
            throw new RepositoryException("save failed", e); // unchecked wrapper at boundary
        }
    }

    static void requirePositive(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be > 0, got " + n);
    }
}
