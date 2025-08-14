package L41_interfaces;

// Lesson 41: Interfaces
// Covers: interface declaration, constants, default & static methods, multiple inheritance of type, functional interfaces.
public class InterfacesDemo {
    public static void main(String[] args) {
        System.out.println("=== Interfaces Demo ===");

        // 1. Basic implementation
        Notifier email = new EmailNotifier();
        email.notify("Welcome user");

        // 2. Multiple interface implementation via composed interface
        SmartDevice device = new SmartPhone("Pixel");
        device.notify("Low battery");
        device.connect();
        device.reboot();

        // 3. Default method usage
        device.healthCheck();

        // 4. Static interface method
        System.out.println("Notifier.supported() = " + Notifier.supported());

        // 5. Constant usage
        System.out.println("Notifier.MAX_LENGTH = " + Notifier.MAX_LENGTH);

        // 6. Functional interface with lambda
        Action action = msg -> System.out.println("Action via lambda: " + msg);
        action.run("Execute task");

        // 7. Default method conflict resolution demonstration
        ConflictImpl conflict = new ConflictImpl();
        conflict.step();

        // 8. Private method in interface (helper for defaults)
        PrivateHelperExample ex = new PrivateHelperExample() {
        }; // anonymous impl
        ex.pub();

        // 9. Polymorphism
        Notifier poly = device; // upcast to interface
        poly.notify("Interface polymorphism call");

        // 10. Using adapter pattern via interface (simple)
        LegacyPrinter legacy = new LegacyPrinter();
        Notifier adapter = new LegacyPrinterAdapter(legacy);
        adapter.notify("Print this");
    }
}

// Simple functional interface
@FunctionalInterface
interface Action {
    void run(String msg);
}

// Core interface
interface Notifier {
    // Constant (implicitly public static final)
    int MAX_LENGTH = 256;

    // Abstract method (implicitly public abstract)
    void notify(String message);

    // Default method provides reusable behavior
    default void healthCheck() {
        System.out.println("Notifier healthCheck: OK");
    }

    // Static utility method (namespaced)
    static boolean supported() {
        return true;
    }
}

interface Connectable {
    void connect();
}

interface Rebootable {
    default void reboot() {
        System.out.println("Rebooting (default)");
    }
}

// Composed interface (multiple inheritance of type)
interface SmartDevice extends Notifier, Connectable, Rebootable {
}

// Multiple inheritance of behavior
class SmartPhone implements SmartDevice {
    private final String model;

    SmartPhone(String model) {
        this.model = model;
    }

    public void notify(String message) {
        System.out.println("[SmartPhone:" + model + "] " + trim(message));
    }

    public void connect() {
        System.out.println("SmartPhone connect network");
    }

    // inherit reboot default
    private String trim(String m) {
        return m.length() > MAX_LENGTH ? m.substring(0, MAX_LENGTH) : m;
    }
}

class EmailNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("Email sent: " + message);
    }
}

// Default method conflict example
interface A {
    default void step() {
        System.out.println("A.step");
    }
}

interface B {
    default void step() {
        System.out.println("B.step");
    }
}

class ConflictImpl implements A, B {
    // Must override to resolve conflict
    public void step() {
        A.super.step(); // choose one or combine
        B.super.step();
        System.out.println("ConflictImpl.step merged");
    }
}

// Private helper in interface (Java 9+)
interface PrivateHelperExample {
    default void pub() {
        System.out.println(helper());
    }

    private String helper() {
        return "Private helper logic";
    }
}

// Interface-based adapter
class LegacyPrinter {
    void print(String text) {
        System.out.println("LegacyPrinter: " + text);
    }
}

class LegacyPrinterAdapter implements Notifier {
    private final LegacyPrinter printer;

    LegacyPrinterAdapter(LegacyPrinter printer) {
        this.printer = printer;
    }

    public void notify(String message) {
        printer.print(message);
    }
}
