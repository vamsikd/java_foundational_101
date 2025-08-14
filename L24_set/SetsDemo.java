package L24_set;

import java.util.*;

// Lesson 24: Sets — implementations, HashSet with generics, rules, and best practices
public class SetsDemo {
    public static void main(String[] args) {
        System.out.println("=== L24: Set Demo ===");

        implementationsOverview();
        hashSetGenericsDemo();
        setOperationsDemo();
        linkedHashSetOrderDemo();
        treeSetDemo();
        equalsHashCodePitfallDemo();

        System.out.println("=== End of Set Demo ===");
    }

    // 1) Implementations overview
    private static void implementationsOverview() {
        System.out.println("-- Implementations overview --");
        System.out.println("Set = unique elements, no duplicates");
        System.out.println("HashSet: hash table; fastest general membership, no order");
        System.out.println("LinkedHashSet: preserves insertion order");
        System.out.println("TreeSet: sorted order (natural or comparator), navigable operations");
    }

    // 2) HashSet with generics
    private static void hashSetGenericsDemo() {
        System.out.println("-- HashSet with generics --");
        Set<String> tags = new HashSet<>(); // type-safe, disallows non-String
        tags.add("java");
        tags.add("collections");
        tags.add("java"); // duplicate ignored
        System.out.println("Tags (HashSet, unique): " + tags);
        System.out.println("contains 'java'? " + tags.contains("java"));

        // Deduplicate a list quickly
        List<Integer> nums = Arrays.asList(5, 2, 5, 1, 2, 9);
        Set<Integer> unique = new HashSet<>(nums);
        System.out.println("Deduplicated: " + unique + " (order not guaranteed)");
    }

    // Common set operations: union, intersection, difference
    private static void setOperationsDemo() {
        System.out.println("-- Set operations: union / intersection / difference --");
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5));

        // union
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("union(a,b): " + union);

        // intersection
        Set<Integer> inter = new HashSet<>(a);
        inter.retainAll(b);
        System.out.println("intersection(a,b): " + inter);

        // difference (elements in a not in b)
        Set<Integer> diff = new HashSet<>(a);
        diff.removeAll(b);
        System.out.println("difference(a,b): " + diff);
    }

    private static void linkedHashSetOrderDemo() {
        System.out.println("-- LinkedHashSet preserves insertion order --");
        Set<String> order = new LinkedHashSet<>();
        order.add("alpha");
        order.add("beta");
        order.add("gamma");
        order.add("beta");
        System.out.println("LinkedHashSet: " + order);
    }

    private static void treeSetDemo() {
        System.out.println("-- TreeSet sorted & navigable --");
        TreeSet<String> sorted = new TreeSet<>(String.CASE_INSENSITIVE_ORDER); // comparator example
        sorted.add("Banana");
        sorted.add("apple");
        sorted.add("cherry");
        System.out.println("TreeSet (CI sorted): " + sorted);
        System.out.println("first=" + sorted.first() + ", last=" + sorted.last());
        System.out.println("ceiling('b')=" + sorted.ceiling("b") + ", floor('b')=" + sorted.floor("b"));
        // Note: TreeSet with natural order generally disallows null
        // (NullPointerException)
    }

    // Pitfall: equals without hashCode breaks HashSet uniqueness
    private static void equalsHashCodePitfallDemo() {
        System.out.println("-- equals/hashCode contract pitfall --");

        class BadPerson {
            final String id;
            final String name; // name is illustrative

            BadPerson(String id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o)
                    return true;
                if (o == null || getClass() != o.getClass())
                    return false;
                BadPerson that = (BadPerson) o;
                return Objects.equals(id, that.id);
            }
            // hashCode NOT overridden — breaks contract
        }

        Set<BadPerson> bad = new HashSet<>();
        bad.add(new BadPerson("42", "Alice"));
        bad.add(new BadPerson("42", "Alice Duplicate")); // logically equal by id
        System.out.println("BadPerson HashSet size (should be 1): " + bad.size()); // often 2

        class GoodPerson {
            final String id;
            final String name; // name is illustrative

            GoodPerson(String id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o)
                    return true;
                if (o == null || getClass() != o.getClass())
                    return false;
                GoodPerson that = (GoodPerson) o;
                return Objects.equals(id, that.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public String toString() {
                return "GoodPerson{" + id + "," + name + '}';
            }
        }

        Set<GoodPerson> good = new HashSet<>();
        good.add(new GoodPerson("7", "Bob"));
        good.add(new GoodPerson("7", "Bob Duplicate"));
        System.out.println("GoodPerson HashSet size (correct 1): " + good.size());
    }
}
