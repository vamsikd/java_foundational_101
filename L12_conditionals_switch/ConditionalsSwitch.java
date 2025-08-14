package L12_conditionals_switch;

enum Priority {
    LOW, MEDIUM, HIGH
}

public class ConditionalsSwitch {
    public static void main(String[] args) {
        // Demo guide: switch statement vs switch expression, arrow (->) vs colon (:)
        // styles,
        // strings/enums/object patterns, yield blocks, throwing in default, and ternary
        // contrast.

        int day = 3;
        // Modern switch statement with arrow labels (no fall-through). Multiple labels
        // with commas.
        switch (day) {
            case 1 -> System.out.println("Mon");
            case 2 -> System.out.println("Tue");
            case 3, 4 -> System.out.println("Midweek (Wed/Thu)");
            case 5 -> System.out.println("Fri");
            case 6, 7 -> System.out.println("Weekend");
            default -> System.out.println("Invalid day");
        }
        int small = 2;
        // Classic switch statement with colon labels. Beware: fall-through occurs
        // without 'break'.
        switch (small) {
            case 1:
                System.out.println("One");
            case 2:
                System.out.println("Two (plus maybe from case 1 if no break)");
                break;
            default:
                System.out.println("Other");
        }
        String command = "start";
        // Switch on String values (content-based comparison), arrow style.
        switch (command) {
            case "start" -> System.out.println("Starting...");
            case "stop" -> System.out.println("Stopping...");
            default -> System.out.println("Unknown command");
        }
        Priority p = Priority.HIGH;
        // Switch on enum is exhaustive when all constants are listed; no default needed
        // here.
        switch (p) {
            case LOW -> System.out.println("Low priority");
            case MEDIUM -> System.out.println("Medium priority");
            case HIGH -> System.out.println("High priority");
        }
        int month = 2;
        // Switch expression: yields a value directly. Great for computing results.
        // You can return values, and even throw exceptions from 'default' for invalid
        // inputs.
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;
            default -> throw new IllegalArgumentException("Bad month: " + month);
        };
        System.out.println("Days in month: " + days);
        int score = 72;
        // Switch expression with a block that needs multiple statements: use 'yield' to
        // provide the value.
        String grade = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> {
                System.out.println("Borderline case (70s)");
                yield "C";
            }
            case 6 -> "D";
            default -> "F";
        };
        System.out.println("Grade: " + grade);
        Object obj = "hello";
        // Pattern matching for switch (type patterns): behavior varies by runtime type.
        // Each case both tests type and binds a variable you can use.
        String kind = switch (obj) {
            case String s -> "String length=" + s.length();
            case Integer i -> "Integer squared=" + (i * i);
            default -> "Unknown type";
        };
        System.out.println(kind);
        int n = 5;
        String parity = (n % 2 == 0) ? "even" : "odd";
        // Switch as a clearer alternative to nested ternaries; default is theoretically
        // unreachable here.
        String parityViaSwitch = switch (n % 2) {
            case 0 -> "even";
            case 1 -> "odd";
            default -> "?";
        };
        System.out.println(parity + " / " + parityViaSwitch);

        // Guarded patterns ("when" clauses) — refine a type pattern with extra
        // conditions.
        // Order matters: the first matching case wins. Put more specific guards before
        // broader ones.
        Object any = "hello world";
        String desc = switch (any) {
            case String s when s.isBlank() -> "blank string";
            case String s when s.length() > 5 -> "long string (len=" + s.length() + ")";
            case String s -> "short string (len=" + s.length() + ")";
            case Integer i when i % 2 == 0 -> "even int: " + i;
            case Integer i -> "odd int: " + i;
            case Number n2 when n2.doubleValue() < 0 -> "negative number: " + n2;
            default -> "other";
        };
        System.out.println(desc);

        // Guarded pattern with generics (List<?>)
        Object maybeList = java.util.List.of(1, 2, 3);
        String listInfo = switch (maybeList) {
            case java.util.List<?> list when list.isEmpty() -> "empty list";
            case java.util.List<?> list when list.size() >= 3 -> "list size >= 3 (" + list.size() + ")";
            case java.util.List<?> list -> "list size = " + list.size();
            default -> "not a list";
        };
        System.out.println(listInfo);
    }
}
