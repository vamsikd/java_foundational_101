package L40_inner_classes;

// Lesson 40: Inner Classes (Member, Static Nested, Local, Anonymous)
public class InnerClassesDemo {
    public static void main(String[] args) {
        System.out.println("=== Inner Classes Demo ===");

        // 1. Member (non-static) inner class requires outer instance
        Outer outer = new Outer("Outer-1");
        Outer.MemberInner inner = outer.new MemberInner(10);
        inner.print();

        // 2. Static nested class (no outer instance required)
        Outer.StaticNested nested = new Outer.StaticNested("SN-Value");
        nested.show();

        // 3. Local class inside method
        class LocalHelper { // scoped only within main
            void run() {
                System.out.println("LocalHelper.run using outer name=" + outer.getName());
            }
        }
        new LocalHelper().run();

        // 4. Anonymous inner class implementing interface
        Runnable r = new Runnable() { // creates synthetic subclass of Object implementing Runnable
            @Override
            public void run() {
                System.out.println("Anonymous Runnable running with outer name=" + outer.getName());
            }
        };
        r.run();

        // 5. Anonymous subclass overriding method
        Greeter greeter = new Greeter("Alice") {
            @Override
            public void greet() {
                System.out.println("Hi (anonymous override), name=" + getName());
            }
        };
        greeter.greet();

        // 6. Lambda vs anonymous (functional interface only)
        Action actionAnon = new Action() { // verbose
            public void execute(String msg) {
                System.out.println("Anon Action: " + msg);
            }
        };
        Action actionLambda = msg -> System.out.println("Lambda Action: " + msg);
        actionAnon.execute("test1");
        actionLambda.execute("test2");

        // 7. Capture effectively-final variable in local/anonymous
        int factor = 3; // effectively final
        Action multiplier = x -> System.out.println("Length * factor = " + (x.length() * factor));
        multiplier.execute("Hello");
    }
}

// Outer class containing various inner types
class Outer {
    private final String name;
    private static int staticCounter = 0;

    Outer(String name) {
        this.name = name;
        staticCounter++;
    }

    String getName() {
        return name;
    }

    // Member (non-static) inner class: has implicit reference to Outer.this
    class MemberInner {
        private final int value;

        MemberInner(int value) {
            this.value = value;
        }

        void print() {
            System.out.println(
                    "MemberInner.print outer.name=" + name + ", value=" + value + ", staticCounter=" + staticCounter);
            System.out.println("Accessing outer via Outer.this.getName()=" + Outer.this.getName());
        }
    }

    // Static nested class: no implicit outer reference
    static class StaticNested {
        private final String tag;

        StaticNested(String tag) {
            this.tag = tag;
        }

        void show() {
            System.out.println("StaticNested.show tag=" + tag + ", staticCounter=" + staticCounter);
        }
    }
}

// Simple base for anonymous subclass example
class Greeter {
    private final String name;

    Greeter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void greet() {
        System.out.println("Hello, " + name);
    }
}

// Functional interface for lambda vs anonymous demonstration
@FunctionalInterface
interface Action {
    void execute(String msg);
}
