package L23_list;

import java.util.*;

// Lesson 23: Lists — implementations, generics with ArrayList, rules, and best practices
public class ListsDemo {
    public static void main(String[] args) {
        System.out.println("=== L23: List Demo ===");

        implementationsOverview();
        arrayListGenericsDemo();
        linkedListDemo();
        sortingAndComparatorDemo();
        iterationAndRemovalDemo();
        arraysAndListsInteropDemo();

        System.out.println("=== End of List Demo ===");
    }

    // 1) Implementations overview
    private static void implementationsOverview() {
        System.out.println("-- Implementations overview --");
        System.out.println("List = ordered, indexed, allows duplicates and null (most impls) ");
        System.out.println("ArrayList: dynamic array; fast random access, amortized O(1) append");
        System.out.println("LinkedList: doubly-linked; fast head/tail ops, slower random access");
        System.out.println("Others: Vector (legacy), CopyOnWriteArrayList (concurrent read-heavy)");
    }

    // 2) ArrayList with generics
    private static void arrayListGenericsDemo() {
        System.out.println("-- ArrayList with generics --");
        List<String> cities = new ArrayList<>(); // compile-time type safety
        cities.add("Hyderabad");
        cities.add("Bengaluru");
        cities.add("Chennai");
        cities.add(null); // allowed
        System.out.println("Cities: " + cities);

        // Index access
        System.out.println("get(1): " + cities.get(1));
        cities.set(1, "Bangalore");
        System.out.println("after set(1, ...): " + cities);

        // Search
        System.out.println("contains('Chennai'): " + cities.contains("Chennai"));
        System.out.println("indexOf(null): " + cities.indexOf(null));
    }

    // LinkedList specific operations
    private static void linkedListDemo() {
        System.out.println("-- LinkedList specifics --");
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(10); // head insert
        list.addLast(30); // tail insert
        list.add(1, 20); // insert in middle
        System.out.println("LinkedList: " + list + ", removeFirst=" + list.removeFirst());
    }

    // Sorting and comparators
    private static void sortingAndComparatorDemo() {
        System.out.println("-- Sorting & comparators --");
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 5));
        Collections.sort(nums); // natural order
        System.out.println("Sorted ints: " + nums);

        List<String> names = new ArrayList<>(Arrays.asList("Raj", "Anita", "Vikram", "Li"));
        // By length, then alphabetically
        names.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        System.out.println("Names by length then alpha: " + names);
    }

    // Iteration styles and safe removal
    private static void iterationAndRemovalDemo() {
        System.out.println("-- Iterate & remove (removeIf) --");
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            data.add(i);
        System.out.println("Original: " + data);

        // Remove even numbers safely
        data.removeIf(n -> n % 2 == 0);
        System.out.println("After removeIf(even): " + data);

        // Enhanced for iteration
        System.out.print("for-each: ");
        for (int n : data)
            System.out.print(n + " ");
        System.out.println();

        // Index loop
        System.out.print("index loop: ");
        for (int i = 0; i < data.size(); i++)
            System.out.print(data.get(i) + " ");
        System.out.println();
    }

    // Arrays <-> Lists interop and pitfalls
    private static void arraysAndListsInteropDemo() {
        System.out.println("-- Arrays <> Lists interop --");
        List<String> fixed = Arrays.asList("a", "b", "c"); // fixed-size view
        System.out.println("Arrays.asList fixed-size: " + fixed);
        try {
            fixed.add("d"); // throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot add to fixed-size list from Arrays.asList");
        }

        List<String> mutable = new ArrayList<>(fixed); // real resizable list
        mutable.add("d");
        System.out.println("Mutable copy: " + mutable);

        List<String> ro = Collections.unmodifiableList(mutable); // read-only view
        System.out.println("Unmodifiable view: " + ro);
    }
}
