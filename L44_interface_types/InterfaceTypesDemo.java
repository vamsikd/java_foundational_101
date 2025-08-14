package L44_interface_types;

// Lesson 44: Interface Types (Regular, Functional, Marker) + Lambdas
public class InterfaceTypesDemo {
    public static void main(String[] args) {
        System.out.println("=== Interface Types Demo ===");

        // 1. Regular interface implementation
        RegularImplementer regular = new RegularImplementer();
        regular.perform();
        regular.extra();

        // 2. Functional interface via lambda
        Processor upper = s -> s.toUpperCase();
        Processor trim = String::trim; // method reference
        System.out.println("upper: " + upper.process("hello"));
        System.out.println("trim: '" + trim.process("  spaced  ") + "'");

        // 3. Higher-order usage: pass functional interface
        String result = transform("Java", x -> x + " Rocks!");
        System.out.println("transform result: " + result);

        // 4. Marker interface usage (tagging)
        TaggableResource res = new TaggableResource();
        if (res instanceof Taggable) {
            System.out.println("Resource is Taggable -> enabling audit feature");
        }

        // 5. Default + static in regular interface
        regular.log("Regular interface default method call");
        RegularInterface.info();

        // 6. Compose functional interfaces
        Processor composed = upper.andThen(trim);
        System.out.println("composed: '" + composed.process("  mix  ") + "'");

        // 7. Null-safe functional wrapper
        Processor safe = Processor.nullSafe(trim);
        System.out.println("null-safe null -> '" + safe.process(null) + "'");
    }

    private static String transform(String input, Processor p) {
        return p.process(input);
    }
}

// -------------- Regular Interface --------------
interface RegularInterface {
    void perform(); // abstract

    default void log(String msg) {
        System.out.println("[RegularInterface] " + msg);
    }

    static void info() {
        System.out.println("RegularInterface.info static method");
    }
}

class RegularImplementer implements RegularInterface {
    public void perform() {
        System.out.println("RegularImplementer.perform executing");
    }

    public void extra() {
        System.out.println("RegularImplementer.extra specific method");
    }
}

// -------------- Functional Interface --------------
@FunctionalInterface
interface Processor {
    String process(String input);

    // Default compositional utility
    default Processor andThen(Processor next) {
        return in -> next.process(this.process(in));
    }

    static Processor nullSafe(Processor delegate) {
        return in -> in == null ? "" : delegate.process(in);
    }
}

// -------------- Marker Interface --------------
// No methods: presence used as metadata / capability flag
interface Taggable {
}

class TaggableResource implements Taggable {
}
