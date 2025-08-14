package L36_static_keyword;

// Lesson 36: The 'static' Keyword
// Covers: static fields, methods, blocks, nested classes, imports, and common patterns.
public class StaticKeywordDemo {
    public static void main(String[] args) {
        System.out.println("=== Static Keyword Demo ===");

        // 1. Single shared field across all instances
        Counter c1 = new Counter();
        Counter c2 = new Counter(); // second instance increments same static counter
        System.out.println("Instances created (via c2) = " + Counter.instancesCreated); // now used

        // 2. Static utility method (no instance needed)
        int sum = MathUtil.add(5, 7);
        System.out.println("MathUtil.add(5,7) = " + sum);

        // 3. Static initialization block side-effect demonstration
        System.out.println("Config loaded? " + Config.LOADED_FLAG);

        // 4. Accessing static nested class
        Outer.StaticNested helper = new Outer.StaticNested();
        helper.ping();

        // 5. Demonstrate static factory vs constructor
        User u = User.of("alice");
        System.out.println("User created: " + u.getName());

        // 6. Static import style (we could: import static java.lang.Math.*;) -
        // demonstration below
        double r = java.lang.Math.max(4.5, 9.2); // without static import
        System.out.println("Math.max result = " + r);

        // 7. Static final constants
        System.out.println("Max connections = " + Config.MAX_CONNECTIONS);

        // 8. Show that modifying static through instance is same shared value
        // (discouraged style)
        c1.nonPreferredMutation(42);
        System.out.println("Counter.instancesCreated (after awkward mutation) = " + Counter.instancesCreated);
    }
}

class Counter {
    // Shared across all Counter objects (class-level state)
    static int instancesCreated = 0;

    public Counter() {
        instancesCreated++; // increments class variable
    }

    void nonPreferredMutation(int value) {
        // Preferred: Counter.instancesCreated = value; Using class qualifier
        Counter.instancesCreated = value; // clearer static access
    }
}

class MathUtil {
    private MathUtil() {
        /* prevent instantiation */ }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int square(int x) {
        return x * x;
    }
}

class Config {
    // Constants: UPPER_SNAKE_CASE, public if part of API
    public static final int MAX_CONNECTIONS = 100;
    public static final String APP_NAME = "SampleApp";
    public static final boolean LOADED_FLAG;

    static { // static initialization block, runs once when class is loaded
        System.out.println("Config static block executing (simulate reading file)...");
        LOADED_FLAG = true; // assign final after computation
    }
}

class Outer {
    private static int sharedValue = 123; // accessible to static nested

    static class StaticNested { // does NOT need Outer instance
        void ping() {
            System.out.println("StaticNested.ping sharedValue=" + sharedValue);
        }
    }
}

class User {
    private final String name;

    private User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Static factory method: descriptive naming, caching, or validation potential
    public static User of(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must be non-blank");
        }
        return new User(name.trim());
    }
}
