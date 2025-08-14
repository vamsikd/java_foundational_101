package L03_type_conversions;

// L03 - Type Conversions & Castings
// Widening (implicit) vs narrowing (explicit), overflow, truncation, promotion.
public class TypeConversions {
    public static void main(String[] args) {
        System.out.println("=== L03: Type Conversions & Castings ===");

        wideningConversions();
        narrowingConversions();
        expressionPromotion();
        roundingHelpers();
        floatPrecisionLoss();

        System.out.println("=== End of L03 ===");
    }

    // 1) Widening conversions (implicit, safe for range)
    private static void wideningConversions() {
        System.out.println("-- Widening (implicit) --");
        byte b = 100;
        short s = b; // byte -> short
        int i = s; // short -> int
        long l = i; // int -> long
        float f = l; // long -> float (may lose precision, but fits range)
        double d = f; // float -> double
        char c = 'A';
        int ci = c; // char -> int
        System.out.println("b->s->i->l->f->d: " + b + ", " + s + ", " + i + ", " + l + ", " + f + ", " + d);
        System.out.println("char 'A' -> int: " + ci);
    }

    // 2) Narrowing conversions (explicit, risky)
    private static void narrowingConversions() {
        System.out.println("-- Narrowing (explicit) --");
        long big = 3_000_000_000L; // beyond int range
        int narrowed = (int) big; // overflow wrap
        System.out.println("(int)3_000_000_000L -> " + narrowed);

        double precise = 123.987;
        int truncated = (int) precise; // truncates toward zero
        System.out.println("(int)123.987 -> " + truncated);

        long large = 9_223_372_036_854_775_000L; // close to Long.MAX for illustration
        float lf = (float) large; // loses digits
        System.out.println("(float)large long -> " + lf);
    }

    // 3) Expression promotion rules
    private static void expressionPromotion() {
        System.out.println("-- Expression promotion --");
        byte x = 10, y = 20;
        // x + y promotes to int; need cast to assign back to byte
        byte sum = (byte) (x + y);
        System.out.println("byte x+y -> int; cast back: (byte)(10+20) = " + sum);

        int i = 5;
        double d = 2.5;
        double res = i * d; // int * double -> double
        System.out.println("int * double -> double: 5 * 2.5 = " + res);
    }

    // 4) Rounding helpers vs cast
    private static void roundingHelpers() {
        System.out.println("-- Rounding helpers --");
        double val = 2.6;
        int cast = (int) val; // 2
        long round = Math.round(val); // 3
        int floor = (int) Math.floor(val); // 2
        int ceil = (int) Math.ceil(val); // 3
        System.out.println(
                "(int)2.6 = " + cast + ", Math.round(2.6) = " + round + ", floor = " + floor + ", ceil = " + ceil);
    }

    // 5) Float precision loss illustration
    private static void floatPrecisionLoss() {
        System.out.println("-- Float precision loss --");
        float f = 1_000_000_000_000_000_000f; // large magnitude reduces precision
        System.out.println("Float large magnitude example: " + f);
        float a = 0.1f, b = 0.2f;
        System.out.println("0.1f + 0.2f = " + (a + b) + " (binary rounding effects)");
    }
}
