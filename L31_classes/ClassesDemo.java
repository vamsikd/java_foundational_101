package L31_classes;

public class ClassesDemo {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        p1.sayHello();
        p1.setAge(31);
        System.out.println("Updated age: " + p1.getAge());
        System.out.println("People created: " + Person.getCount());
        Person p2 = new Person("Bob");
        p2.sayHello();
        Plain empty = new Plain();
        System.out.println("Plain default id: " + empty.id);
        Outer outer = new Outer(10);
        Outer.Inner inner = outer.new Inner(5);
        inner.printSum();
        Utility.Pair pair = new Utility.Pair(3, 4);
        System.out.println("Pair sum: " + pair.sum());
        class LocalGreeter {
            void greet() {
                System.out.println("Local hello to " + p1.getName());
            }
        }
        new LocalGreeter().greet();
        Person temp = new Person("Temp", 99) {
            @Override
            public void sayHello() {
                System.out.println("(Anonymous) Hi I'm " + getName() + " age=" + getAge());
            }
        };
        temp.sayHello();
        System.out.println("Config version: " + Config.VERSION);
        FinalDemo fd = new FinalDemo(7);
        System.out.println("Final value: " + fd.getValue());
        Person teen = Person.createTeen("Charlie");
        teen.sayHello();
        System.out.println("Person toString: " + teen);
    }
}

class Person {
    private static int count = 0;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }

    public Person(String name) {
        this(name, 0);
    }

    public static Person createTeen(String name) {
        return new Person(name, 13);
    }

    public static int getCount() {
        return count;
    }

    public void sayHello() {
        System.out.println("Hi I'm " + name + " and I'm " + age + " years old.");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            System.out.println("Age rejected");
            return;
        }
        this.age = age;
    }

    public String toString() {
        return "Person{" + name + ", age=" + age + '}';
    }
}

class Plain {
    int id = 42;
}

class Outer {
    private int base;

    Outer(int base) {
        this.base = base;
    }

    class Inner {
        private int offset;

        Inner(int o) {
            offset = o;
        }

        void printSum() {
            System.out.println("Outer+Inner sum = " + (base + offset));
        }
    }
}

class Utility {
    static class Pair {
        private final int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        int sum() {
            return a + b;
        }
    }
}

class Config {
    static final String VERSION;
    static {
        VERSION = "1.0";
    }
}

class FinalDemo {
    private final int value;

    FinalDemo(int v) {
        value = v;
    }

    int getValue() {
        return value;
    }
}
