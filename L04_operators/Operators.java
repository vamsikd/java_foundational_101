package L04_operators;

// L04 - Operators
// Arithmetic, unary, comparison, logical, bitwise, shifts, ternary, and concatenation with pitfalls.
public class Operators {
    public static void main(String[] args) {
        System.out.println("=== L04: Operators Demo ===");
        arithmetic();
        unary();
        comparison();
        logical();
        bitwiseAndShifts();
        ternaryAndConcat();
        pitfalls();
        System.out.println("=== End of L04 ===");
    }

    // Arithmetic: + - * / %
    private static void arithmetic() {
        System.out.println("-- Arithmetic --");
        int a = 7, b = 3;
        System.out.println("7 + 3 = " + (a + b));
        System.out.println("7 - 3 = " + (a - b));
        System.out.println("7 * 3 = " + (a * b));
        System.out.println("7 / 3 = " + (a / b) + " (integer division truncates)");
        System.out.println("7 % 3 = " + (a % b) + " (remainder sign follows dividend)");
        System.out.println("-7 % 3 = " + (-a % b));
        System.out.println("7 / 3.0 = " + (a / 3.0));
    }

    // Unary: + - ++ --
    private static void unary() {
        System.out.println("-- Unary ++/-- --");
        int x = 5;
        System.out.println("x   = " + x);
        System.out.println("x++ = " + (x++)); // post-increment returns old value
        System.out.println("now x = " + x);
        System.out.println("++x = " + (++x)); // pre-increment returns new value
        System.out.println("now x = " + x);
        System.out.println("x-- = " + (x--));
        System.out.println("now x = " + x);
    }

    // Comparison: == != < <= > >=
    private static void comparison() {
        System.out.println("-- Comparison --");
        int p = 10, q = 20;
        System.out.println("p==q: " + (p == q));
        System.out.println("p!=q: " + (p != q));
        System.out.println("p<q : " + (p < q));
        System.out.println("p>=q: " + (p >= q));
    }

    // Logical: && || ! (short-circuit) vs bitwise boolean &: both sides evaluate
    private static void logical() {
        System.out.println("-- Logical short-circuit vs & --");
        boolean leftFalse = false;
        System.out.println("false && trace(true): " + (leftFalse && trace("rhs-&&", true)) + " (rhs not evaluated)");
        System.out.println("false &  trace(true): " + (leftFalse & trace("rhs-&", true)) + " (rhs evaluated)");

        boolean leftTrue = true;
        System.out.println("true || trace(true):  " + (leftTrue || trace("rhs-||", true)) + " (rhs not evaluated)");
        System.out.println("true |  trace(false): " + (leftTrue | trace("rhs-|", false)) + " (rhs evaluated)");

        System.out.println("!true = " + (!true));
    }

    private static boolean trace(String label, boolean value) {
        System.out.println("  evaluating " + label);
        return value;
    }

    // Bitwise: & | ^ ~ and Shifts: << >> >>>
    private static void bitwiseAndShifts() {
        System.out.println("-- Bitwise & Shifts --");
        int m = 0b0101; // 5
        int n = 0b0011; // 3
        System.out.println("m & n = " + (m & n) + " (AND)"); // 1
        System.out.println("m | n = " + (m | n) + " (OR)"); // 7
        System.out.println("m ^ n = " + (m ^ n) + " (XOR)"); // 6
        System.out.println("~m    = " + (~m)); // bitwise NOT

        // Masks/flags example
        int READ = 1 << 0; // 0001
        int WRITE = 1 << 1; // 0010
        int EXEC = 1 << 2; // 0100
        int flags = 0;
        flags |= READ | EXEC; // set bits
        System.out.println("flags has READ?  " + ((flags & READ) != 0));
        System.out.println("flags has WRITE? " + ((flags & WRITE) != 0));
        System.out.println("flags has EXEC?  " + ((flags & EXEC) != 0));

        // Shifts
        int v = -8;
        System.out.println("-8 << 1  = " + (v << 1)); // arithmetic left
        System.out.println("-8 >> 1  = " + (v >> 1) + " (arithmetic right keeps sign)");
        System.out.println("-8 >>> 1 = " + (v >>> 1) + " (logical right fills with zeros)");
    }

    // Ternary and String concatenation
    private static void ternaryAndConcat() {
        System.out.println("-- Ternary & String concat --");
        int score = 68;
        String result = (score >= 70) ? "pass" : "fail";
        System.out.println("score=" + score + ": " + result);

        // Concat order
        System.out.println("\"A\"+1+2 = " + ("A" + 1 + 2));
        System.out.println("\"A\"+(1+2) = " + ("A" + (1 + 2)));
    }

    // Pitfalls and safety habits
    private static void pitfalls() {
        System.out.println("-- Pitfalls --");
        // Truncation in integer division
        System.out.println("5/2 = " + (5 / 2) + ", 5/2.0 = " + (5 / 2.0));

        // Overflow: promote to long first
        int big = 2_000_000_000;
        int overflow = big * 2; // wraps around
        long safe = 1L * big * 2; // promote first
        System.out.println("overflow int: 2_000_000_000*2 -> " + overflow + "; safe long -> " + safe);

        // Remainder sign
        System.out.println("-5 % 2 = " + (-5 % 2));

        // Compound assignment hides cast
        int k = 1;
        k += 3.5; // compiles; equivalent to k = (int)(k + 3.5)
        System.out.println("k after k+=3.5 -> " + k);
    }
}
