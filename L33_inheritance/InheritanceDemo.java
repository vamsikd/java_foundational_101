package L33_inheritance;

public class InheritanceDemo {
    public static void main(String[] args) {
        Animal generic = new Animal("Creature");
        generic.speak();
        Dog d = new Dog("Rex");
        d.speak();
        Animal asAnimal = d;
        asAnimal.speak();
        d.describe();
        GoldenRetriever g = new GoldenRetriever("Goldie");
        g.speak();
        g.fetch();
        g.play();
        Cat c = new Cat("Misty");
        c.speak();
        Shape circle = new Circle(5);
        System.out.println("Circle area: " + circle.area());
        circle.printArea();
        Animal[] zoo = { new Dog("Spot"), new Cat("Whisk"), new GoldenRetriever("Sunny") };
        for (Animal a : zoo)
            a.speak();
        System.out.println("Dog toString: " + d);
        Object maybeAnimal = new Dog("Patch");
        if (maybeAnimal instanceof Dog dogRef)
            dogRef.fetchStick();
    }
}

class Animal {
    private final String name;
    private final String type;

    Animal(String name) {
        this.name = name;
        this.type = this.getClass().getSimpleName();
    }

    public void speak() {
        System.out.println(name + " (Animal) makes a sound");
    }

    public String getName() {
        return name;
    }

    protected String getType() {
        return type;
    }

    public String toString() {
        return type + "{" + name + '}';
    }
}

class Dog extends Animal {
    Dog(String n) {
        super(n);
    }

    public void speak() {
        System.out.println(getName() + " (Dog) barks");
    }

    public void describe() {
        System.out.println("describe -> this.name=" + getName() + ", super.getType()=" + super.getType());
    }

    public void fetchStick() {
        System.out.println(getName() + " fetches a stick");
    }

    public void play() {
        System.out.println(getName() + " plays fetch");
    }
}

class GoldenRetriever extends Dog {
    GoldenRetriever(String n) {
        super(n);
    }

    public void speak() {
        System.out.println(getName() + " (GoldenRetriever) joyful bark");
    }

    public void play() {
        System.out.println(getName() + " (GoldenRetriever) starts play");
        super.play();
        System.out.println(getName() + " (GoldenRetriever) ends play");
    }

    public void fetch() {
        System.out.println(getName() + " gently retrieves item");
    }
}

class Cat extends Animal {
    Cat(String n) {
        super(n);
    }

    public void speak() {
        System.out.println(getName() + " (Cat) meows");
    }
}

abstract class Shape {
    public abstract double area();

    public final void printArea() {
        System.out.println("Area = " + area());
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double r) {
        radius = r;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}
