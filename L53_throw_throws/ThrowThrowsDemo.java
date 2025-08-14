package L53_throw_throws;

import java.io.IOException;

// Lesson 53: 'throw' vs 'throws'
// 'throw' = actually throw an exception object now.
// 'throws' = declare that a method may propagate specified checked exceptions.
public class ThrowThrowsDemo {
    public static void main(String[] args) {
        System.out.println("=== throw vs throws Demo ===");

        // 1. Using throw to signal an error immediately (unchecked)
        try {
            requireNonBlank("");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught: " + e.getMessage());
        }

        // 2. Calling method that declares checked exception with throws
        try {
            mightFailIO(true);
        } catch (IOException e) {
            System.out.println("Handled checked IOException: " + e.getMessage());
        }

        // 3. Rethrowing with additional context
        try {
            wrapAndRethrow();
        } catch (IOException e) {
            System.out.println("Wrapped & rethrown caught: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }

        // 4. Demonstrate throws on interface method and implementation
        NetworkClient client = new DummyClient();
        try {
            client.send("payload");
        } catch (IOException e) {
            System.out.println("NetworkClient send failed: " + e.getMessage());
        }

        // 5. Throwing custom unchecked vs checked
        try {
            throwUnchecked(false);
            throwUnchecked(true);
        } catch (CustomUnchecked ex) {
            System.out.println("Caught CustomUnchecked: " + ex.getMessage());
        }

        try {
            throwChecked(true);
        } catch (CustomChecked e) {
            System.out.println("Caught CustomChecked: " + e.getMessage());
        }
    }

    // 'throws' not needed for unchecked exception
    static void requireNonBlank(String s) {
        if (s == null || s.isBlank())
            throw new IllegalArgumentException("String must be non-blank"); // throw
    }

    // 'throws' indicates caller must handle or propagate
    static void mightFailIO(boolean fail) throws IOException { // throws
        if (fail)
            throw new IOException("simulated IO failure");
    }

    // Wrap low-level checked and rethrow new checked retaining cause
    static void wrapAndRethrow() throws IOException {
        try {
            mightFailIO(true);
        } catch (IOException e) {
            throw new IOException("Higher-level operation failed", e); // throw new
        }
    }

    interface NetworkClient {
        void send(String data) throws IOException; // declaration with throws
    }

    static class DummyClient implements NetworkClient {
        public void send(String data) throws IOException { // still must declare
            if (data.length() < 2)
                throw new IOException("data too short");
            System.out.println("DummyClient sent: " + data);
        }
    }

    // Custom unchecked
    static class CustomUnchecked extends RuntimeException {
        CustomUnchecked(String msg) {
            super(msg);
        }
    }

    // Custom checked
    static class CustomChecked extends Exception {
        CustomChecked(String msg) {
            super(msg);
        }
    }

    static void throwUnchecked(boolean fail) {
        if (fail)
            throw new CustomUnchecked("runtime problem");
    }

    static void throwChecked(boolean fail) throws CustomChecked {
        if (fail)
            throw new CustomChecked("checked problem");
    }
}
