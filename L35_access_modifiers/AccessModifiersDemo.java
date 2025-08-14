package L35_access_modifiers;

// Lesson 35: Access Modifiers
// Demonstrates visibility: private, (package-private), protected, public.
// Also shows typical use cases and pitfalls.
public class AccessModifiersDemo {
    public static void main(String[] args) {
        System.out.println("=== Access Modifiers Demo ===");
        SampleBase base = new SampleBase();
        base.publicField = 10; // allowed everywhere
        // base.privateField = 5; // NOT allowed (private)
        base.packageField = 20; // allowed (same package)
        base.setPrivateField(5); // controlled write via method
        System.out.println("private via getter: " + base.getPrivateField());

        SampleSub sub = new SampleSub();
        sub.demo(); // demonstrates protected access

        // Polymorphic reference
        SampleBase poly = sub;
        poly.publicMethod(); // overridden method call
    }
}

class SampleBase {
    public int publicField; // wide visibility
    protected int protectedField; // package + subclasses
    int packageField; // package-private (default)
    private int privateField; // only inside this class

    public void publicMethod() {
        System.out.println("SampleBase.publicMethod");
    }

    protected void protectedHook() {
        System.out.println("SampleBase.protectedHook (customizable by subclass)");
    }

    void packageMethod() {
        System.out.println("SampleBase.packageMethod (internal API)");
    }

    private void privateHelper() {
        System.out.println("SampleBase.privateHelper (encapsulation)");
    }

    public int getPrivateField() { // controlled read
        return privateField;
    }

    public void setPrivateField(int value) { // validation spot
        if (value < 0) {
            System.out.println("Rejected negative privateField");
            return;
        }
        privateField = value;
        privateHelper(); // internal invariant step
    }
}

class SampleSub extends SampleBase {
    public void demo() {
        // Access inherited members:
        publicField = 1; // OK
        protectedField = 2; // OK (subclass)
        packageField = 3; // OK (same package)
        // privateField = 4; // NOT visible

        protectedHook(); // OK
        packageMethod(); // OK (same package)
        // privateHelper(); // NOT visible

        System.out.println("Sub can see protectedField=" + protectedField);
    }

    @Override
    public void publicMethod() { // widened or same visibility only
        System.out.println("SampleSub.publicMethod (override)");
    }
}

// NOTE: A top-level class cannot be private or protected; only public or
// package-private.
// Omitting 'public' here makes this helper package-private.
class PackageOnlyHelper { // visible only within L35_access_modifiers package
    static void ping() {
        System.out.println("PackageOnlyHelper.ping");
    }
}
