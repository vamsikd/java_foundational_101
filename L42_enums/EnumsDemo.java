package L42_enums;

// Lesson 42: Enums
// Demonstrates enum basics, fields, methods, switch usage, polymorphism, and advanced patterns.
public class EnumsDemo {
    public static void main(String[] args) {
        System.out.println("=== Enums Demo ===");

        // 1. Basic iteration
        for (Day d : Day.values()) {
            System.out.println(d + " (ordinal=" + d.ordinal() + ") weekend? " + d.isWeekend());
        }

        // 2. Switch expression with enum
        Day today = Day.MONDAY;
        String mood = switch (today) {
            case MONDAY -> "Focused";
            case FRIDAY -> "Excited";
            case SATURDAY, SUNDAY -> "Relaxed";
            default -> "Steady";
        };
        System.out.println("Mood on " + today + ": " + mood);

        // 3. Enum with custom behavior per constant
        Operation add = Operation.ADD;
        System.out.println("2 + 3 = " + add.apply(2, 3));
        System.out.println("4 / 2 = " + Operation.DIVIDE.apply(4, 2));

        // 4. Parsing from string (case-sensitive)
        Day parsed = Day.valueOf("TUESDAY");
        System.out.println("Parsed day = " + parsed);

        // 5. Safe lookup helper
        System.out.println("Find WEDNESDAY -> " + Day.safeValueOf("WEDNESDAY"));
        System.out.println("Find FUNDAY -> " + Day.safeValueOf("FUNDAY"));

        // 6. Enum implementing interface (use SimpleOp)
        int result = SimpleOp.MULTIPLY.exec(6, 7);
        System.out.println("6 * 7 = " + result);

        // 7. Pattern: strategy via enum
        TaxStrategy strategy = TaxStrategy.STANDARD;
        System.out.println("Tax on 100 = " + strategy.compute(100.0));
    }
}

enum Day {
    MONDAY(false), TUESDAY(false), WEDNESDAY(false), THURSDAY(false), FRIDAY(false), SATURDAY(true), SUNDAY(true);

    private final boolean weekend;

    Day(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public static Day safeValueOf(String name) {
        try {
            return Day.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

enum Operation {
    ADD {
        public int apply(int a, int b) {
            return a + b;
        }
    },
    SUBTRACT {
        public int apply(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY {
        public int apply(int a, int b) {
            return a * b;
        }
    },
    DIVIDE {
        public int apply(int a, int b) {
            return b == 0 ? 0 : a / b;
        }
    }; // simplistic guard

    public abstract int apply(int a, int b);
}

// Enum implementing interface
interface MathOp {
    int exec(int a, int b);
}

enum SimpleOp implements MathOp {
    ADD, MULTIPLY;

    public int exec(int a, int b) {
        return this == ADD ? a + b : a * b;
    }
}

enum TaxStrategy {
    STANDARD {
        double compute(double amount) {
            return amount * 0.20;
        }
    },
    REDUCED {
        double compute(double amount) {
            return amount * 0.10;
        }
    },
    ZERO {
        double compute(double amount) {
            return 0.0;
        }
    };

    abstract double compute(double amount);
}
