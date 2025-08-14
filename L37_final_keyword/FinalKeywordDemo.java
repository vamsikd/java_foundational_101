package L37_final_keyword;

// Lesson 37: The 'final' Keyword
// Demonstrates final for variables, fields, parameters, methods, and classes.
public class FinalKeywordDemo {
    public static void main(String[] args) {
        System.out.println("=== Final Keyword Demo ===");

        // 1. Local final variable (must assign once)
        final int port = 8080; // cannot reassign
        System.out.println("port=" + port);

        // 2. Final reference (object contents can still mutate)
        final StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // allowed: internal state changes
        System.out.println("StringBuilder contents: " + sb);
        // sb = new StringBuilder(); // NOT allowed: reassignment

        // 3. Final field in instance (see ImmutablePoint)
        ImmutablePoint p = new ImmutablePoint(3, 4);
        System.out.println("ImmutablePoint length=" + p.length());

        // 4. Final static constant pattern
        System.out.println("Config.APP_VERSION=" + Config.APP_VERSION);

        // 5. Final method cannot be overridden (see Child attempt)
        Base b = new Child();
        b.template(); // calls final method defined in Base

        // 6. Final class cannot be subclassed (see FinalUtility)
        System.out.println("FinalUtility.square(5)=" + FinalUtility.square(5));

        // 7. Effectively final (used in lambda or inner class)
        int factor = 2; // not declared final but not reassigned -> effectively final
        int result = multiply(sb.length(), factor);
        System.out.println("multiply(len, factor)=" + result);
    }

    private static int multiply(int a, int b) {
        return a * b;
    }
}

// Immutable data holder pattern
class ImmutablePoint {
    private final int x; // assigned exactly once in constructor
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }
}

class Config {
    // Compile-time constant: primitive or String initialized with literal ->
    // inlined by compiler
    public static final String APP_VERSION = "1.0.0";
    // Not a compile-time constant (computed) but still a constant variable because
    // final
    public static final long START_TIME = System.currentTimeMillis();
}

class Base {
    public final void template() {
        // algorithm steps
        step1();
        step2();
    }

    protected void step1() {
        System.out.println("Base.step1");
    }

    protected void step2() {
        System.out.println("Base.step2");
    }
}

class Child extends Base {
    // Attempting to override final method would fail:
    // public void template() {} // compile error if uncommented
    @Override
    protected void step1() {
        System.out.println("Child.step1 override allowed");
    }
}

// Final class cannot be extended
final class FinalUtility {
    private FinalUtility() {
    }

    public static int square(int n) {
        return n * n;
    }
}
