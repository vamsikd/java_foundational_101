package L38_wrapper_classes;

// Lesson 38: Wrapper Classes (Integer, Long, Double, Float, Short, Byte, Character, Boolean)
// Shows boxing, unboxing, utilities, caching, null handling.
public class WrapperClassesDemo {
    public static void main(String[] args) {
        System.out.println("=== Wrapper Classes Demo ===");

        // 1. Boxing & Unboxing
        int primitiveInt = 42;
        Integer boxedInt = primitiveInt; // auto-boxing
        int unboxedInt = boxedInt; // auto-unboxing
        System.out.println("primitiveInt=" + primitiveInt + ", boxedInt=" + boxedInt + ", unboxedInt=" + unboxedInt);

        // 2. Prefer valueOf / literals over deprecated constructors
        Integer valueOfInt = Integer.valueOf(42); // uses cache
        Integer another = 42; // auto-box literal (also cached)
        System.out.println("valueOfInt == boxedInt? " + (valueOfInt == boxedInt)); // likely true (cache)
        System.out.println("another == boxedInt? " + (another == boxedInt)); // true (same cache value)

        // 3. Caching range demonstration (-128..127) for Integer, Short, Byte, Long,
        // Character
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("a==b? " + (a == b)); // true (cache)
        System.out.println("c==d? " + (c == d)); // false (outside cache)

        // 4. Null pitfalls with unboxing
        Integer maybeNull = null; // demonstration only
        try {
            int risk = maybeNull; // NullPointerException during unboxing
            System.out.println(risk); // not reached
        } catch (NullPointerException e) {
            System.out.println("Caught NPE on unboxing null Integer");
        }

        // 5. Parsing & value conversion
        int parsed = Integer.parseInt("123");
        Integer parsedWrapper = Integer.valueOf("123");
        System.out.println("parsed=" + parsed + ", parsedWrapper=" + parsedWrapper);

        // 6. Utility constants & methods
        System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println("Double.isNaN(Math.sqrt(-1))=" + Double.isNaN(Math.sqrt(-1)));

        // 7. Character utilities
        char ch = 'A';
        System.out.println("Character.isLetter('A')=" + Character.isLetter(ch));
        System.out.println("Character.toLowerCase('A')=" + Character.toLowerCase(ch));

        // 8. Boolean parsing
        System.out.println("Boolean.parseBoolean('true')=" + Boolean.parseBoolean("true"));
        System.out.println("Boolean.valueOf('TRUE')=" + Boolean.valueOf("TRUE"));

        // 9. Avoid == with wrappers (except intentional cache check) -> use equals
        Integer x = 1000;
        Integer y = 1000;
        System.out.println("x.equals(y)? " + x.equals(y));
        System.out.println("x==y? " + (x == y)); // false

        // 10. Performance note: prefer primitive in hot loops
        long start = System.nanoTime();
        Long sum = 0L; // wrapper inside loop -> boxing overhead
        for (int i = 0; i < 10_000; i++) {
            sum += i; // unboxing + boxing each iteration
        }
        long durationBoxed = System.nanoTime() - start;

        start = System.nanoTime();
        long sumPrimitive = 0L; // primitive version
        for (int i = 0; i < 10_000; i++) {
            sumPrimitive += i;
        }
        long durationPrimitive = System.nanoTime() - start;

        System.out.println("Sum wrapper=" + sum + ", time(ns)=" + durationBoxed);
        System.out.println("Sum primitive=" + sumPrimitive + ", time(ns)=" + durationPrimitive);

        // 11. Safe null handling helper
        System.out.println("safeUnbox(null, 5)=" + safeUnbox(null, 5));
        System.out.println("safeUnbox(9, 5)=" + safeUnbox(9, 5));
    }

    // Provide default when wrapper is null
    private static int safeUnbox(Integer value, int defaultValue) {
        return (value != null) ? value : defaultValue;
    }
}
