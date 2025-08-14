package L02_variables;

// L02 - Java Primitive Types (Variables)
// Demonstrates the 8 primitives: sizes, ranges, and typical uses.
public class Variables {
    public static void main(String[] args) {
        System.out.println("=== L02: Java Primitive Types ===");

        // 1) Integer types
        // byte: 8-bit signed, range [-128, 127]
        byte b = 120; // raw bytes, streams, compact arrays
        System.out.println("byte: size=8 bits, min=" + Byte.MIN_VALUE + ", max=" + Byte.MAX_VALUE + ", sample=" + b);

        // short: 16-bit signed, range [-32_768, 32_767]
        short s = 30_000; // large arrays when memory matters
        System.out
                .println("short: size=16 bits, min=" + Short.MIN_VALUE + ", max=" + Short.MAX_VALUE + ", sample=" + s);

        // int: 32-bit signed, ~±2.1B — default for whole numbers
        int population = 1_400_000_000; // illustrative; underscores improve readability
        System.out.println("int: size=32 bits, min=" + Integer.MIN_VALUE + ", max=" + Integer.MAX_VALUE + ", sample="
                + population);

        // long: 64-bit signed, huge range — use L suffix
        long timestamp = 1_695_000_000_000L; // e.g., milliseconds since epoch
        System.out.println(
                "long: size=64 bits, min=" + Long.MIN_VALUE + ", max=" + Long.MAX_VALUE + ", sample=" + timestamp);

        // 2) Floating-point types (IEEE 754)
        // float: 32-bit (~6-7 digits). Use 'f' suffix.
        float price = 199.99f; // memory-sensitive numeric arrays
        System.out.println(
                "float: size=32 bits, min=" + Float.MIN_VALUE + ", max=" + Float.MAX_VALUE + ", sample=" + price);

        // double: 64-bit (~15 digits) — default for decimals
        double pi = 3.141_592_653_589_793; // scientific/financial (but see BigDecimal)
        System.out.println(
                "double: size=64 bits, min=" + Double.MIN_VALUE + ", max=" + Double.MAX_VALUE + ", sample=" + pi);

        // 3) char & boolean
        // char: 16-bit UTF-16 code unit; represents a single symbol
        char letter = 'A';
        char rupee = '\u20B9'; // Unicode escape for Indian Rupee sign
        System.out.println("char: size=16 bits, examples='" + letter + "' and '" + rupee + "' (codepoint of 'A'="
                + (int) letter + ")");

        // boolean: true/false
        boolean isJavaFun = true;
        boolean isCold = false; // illustrative
        System.out.println("boolean: values=true/false, samples=" + isJavaFun + ", " + isCold);

        // 4) Overflow and promotion demos
        int overflow = Integer.MAX_VALUE + 1; // wraps around (no exception)
        System.out.println("overflow demo: Integer.MAX_VALUE + 1 -> " + overflow);

        int fromChar = 'A'; // char promotes to int (code point)
        System.out.println("char to int promotion: 'A' -> " + fromChar);

        // 5) Casting examples
        long bigCount = 3_000_000_000L; // > Integer.MAX_VALUE
        int narrowed = (int) bigCount; // explicit cast, data loss expected
        System.out.println("narrowing cast long->int: 3_000_000_000L -> " + narrowed);

        double precise = 1.0 / 3; // repeating binary fraction
        System.out.println("double rounding note: 1.0/3 -> " + precise);

        System.out.println("=== End of L02 ===");
    }
}
