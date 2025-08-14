package L43_annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Lesson 43: Annotations
// Demonstrates built-in annotations, custom annotations, retention policies, targets, and simple reflection processing.
public class AnnotationsDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Annotations Demo ===");

        // 1. Built-in annotations demonstration
        ExampleService service = new ExampleService();
        service.execute();

        // 2. Reflection: process custom @Task annotation
        System.out.println("-- Processing @Task annotations --");
        for (Method m : ExampleService.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Task.class)) {
                Task t = m.getAnnotation(Task.class);
                System.out.println("Found task: name=" + t.name() + ", priority=" + t.priority() + ", experimental="
                        + t.experimental());
            }
        }

        // 3. Runtime retention vs SOURCE retention demonstration
        System.out.println("Has @RuntimeMarker on ExampleService? "
                + ExampleService.class.isAnnotationPresent(RuntimeMarker.class));

        // 4. Deprecation warning example (see @Deprecated method call)
        service.legacy();

        // 5. Inherited annotation demonstration
        System.out.println("ChildController has @RuntimeMarker? "
                + ChildController.class.isAnnotationPresent(RuntimeMarker.class));

        // 6. Repeatable annotation usage
        for (Method m : RepeatableExample.class.getDeclaredMethods()) {
            if (m.getName().equals("multi")) {
                Tag[] tags = m.getAnnotationsByType(Tag.class);
                System.out.print("Tags on multi(): ");
                for (Tag tag : tags)
                    System.out.print(tag.value() + " ");
                System.out.println();
            }
        }
    }
}

// Custom annotation definitions
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Task {
    String name();

    int priority() default 0;

    boolean experimental() default false;
}

// Marker annotation with runtime retention
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited // allows subclasses to inherit this annotation
@interface RuntimeMarker {
}

// SOURCE retention (not visible at runtime)
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@interface SourceMarker {
}

// Repeatable annotations
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Tags.class)
@interface Tag {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Tags {
    Tag[] value();
}

// Example service using custom annotations
@RuntimeMarker
@SourceMarker // disappears after compilation
class ExampleService {
    @Task(name = "init", priority = 1)
    public void init() {
        System.out.println("init task running");
    }

    @Task(name = "execute", priority = 2, experimental = true)
    public void execute() {
        System.out.println("execute task running");
    }

    @Deprecated // built-in annotation signals API should not be used
    public void legacy() {
        System.out.println("legacy method invoked (deprecated)");
    }
}

class ChildController extends ExampleService {
}

class RepeatableExample {
    @Tag("alpha")
    @Tag("beta")
    public void multi() {
    }
}
