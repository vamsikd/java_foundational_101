package L39_abstract_classes;

// Lesson 39: Abstract Classes
// Demonstrates abstract class definition, abstract vs concrete methods, template pattern, partial implementation, and limitations.
public class AbstractClassesDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Classes Demo ===");

        // Cannot instantiate abstract directly:
        // Shape s = new Shape(); // compile error

        Shape circle = new Circle(5.0);
        Shape rect = new Rectangle(4.0, 3.0);

        printShape(circle);
        printShape(rect);

        // Template method demonstration (final in base)
        ReportGenerator pdf = new PdfReport();
        ReportGenerator csv = new CsvReport();
        pdf.generate();
        csv.generate();

        // Partial implementation example
        TwoDShape tri = new Triangle(3, 4, 5);
        System.out.println("Triangle perimeter=" + tri.perimeter());
        System.out.println("Triangle area (Heron)=" + tri.area());
    }

    private static void printShape(Shape s) {
        System.out.println(s.getName() + " area=" + s.area());
    }
}

// Abstract base with common API
abstract class Shape {
    private final String name; // shared state

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area(); // must be implemented
    // May include concrete helpers

    protected double squared(double v) {
        return v * v;
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * squared(radius);
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// Template Method pattern
abstract class ReportGenerator {
    public final void generate() { // final prevents override altering sequence
        loadData();
        format();
        export();
    }

    protected abstract void loadData();

    protected abstract void format();

    protected abstract void export();
}

class PdfReport extends ReportGenerator {
    protected void loadData() {
        System.out.println("PdfReport.loadData");
    }

    protected void format() {
        System.out.println("PdfReport.format");
    }

    protected void export() {
        System.out.println("PdfReport.export PDF file");
    }
}

class CsvReport extends ReportGenerator {
    protected void loadData() {
        System.out.println("CsvReport.loadData");
    }

    protected void format() {
        System.out.println("CsvReport.format");
    }

    protected void export() {
        System.out.println("CsvReport.export CSV file");
    }
}

// Partial implementation with both abstract and concrete
abstract class TwoDShape {
    public abstract double area();

    public abstract double perimeter();

    protected double sum(double... vals) {
        double s = 0;
        for (double v : vals)
            s += v;
        return s;
    }
}

class Triangle extends TwoDShape {
    private final double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double p = perimeter() / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c)); // Heron's formula
    }
}
