package L25_map;

import java.util.*;

// Lesson 25: Maps — implementations, HashMap with generics, rules, and best practices
public class MapsDemo {
    public static void main(String[] args) {
        System.out.println("=== L25: Map Demo ===");

        implementationsOverview();
        hashMapGenericsDemo();
        commonOperationsDemo();
        linkedHashMapDemo();
        treeMapDemo();
        computeAndMergeDemo();
        keyEqualityPitfallDemo();

        System.out.println("=== End of Map Demo ===");
    }

    // 1) Implementations overview
    private static void implementationsOverview() {
        System.out.println("-- Implementations overview --");
        System.out.println("Map = key -> value associations (not a Collection)");
        System.out.println("HashMap: general-purpose, no order; allows one null key and many null values");
        System.out.println("LinkedHashMap: preserves insertion (or access) order");
        System.out.println("TreeMap: sorted by key (natural or Comparator); no null keys in natural order");
    }

    // 2) HashMap with generics
    private static void hashMapGenericsDemo() {
        System.out.println("-- HashMap with generics --");
        Map<String, Integer> scores = new HashMap<>(); // type-safe
        scores.put("alice", 90);
        scores.put("bob", 82);
        scores.put("carol", 95);
        scores.put(null, 0); // allowed in HashMap
        System.out.println("Scores size=" + scores.size() + ", bob=" + scores.get("bob"));
        System.out.println("Contains key 'dave'? " + scores.containsKey("dave"));
        System.out.println("HashMap keys (order not guaranteed): " + scores.keySet());
    }

    // Common operations and iteration patterns
    private static void commonOperationsDemo() {
        System.out.println("-- Common operations --");
        Map<String, String> capitals = new HashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("Japan", "Tokyo");

        // getOrDefault
        String cap = capitals.getOrDefault("Canada", "Unknown");
        System.out.println("Canada capital: " + cap);

        // putIfAbsent
        capitals.putIfAbsent("India", "Delhi"); // no change
        capitals.putIfAbsent("France", "Paris");
        System.out.println("After putIfAbsent: " + capitals);

        // Iterate entries
        System.out.print("Entries: ");
        for (Map.Entry<String, String> e : capitals.entrySet()) {
            System.out.print(e.getKey() + "->" + e.getValue() + " ");
        }
        System.out.println();

        // Remove a key
        capitals.remove("USA");
        System.out.println("After remove 'USA': " + capitals);
    }

    // LinkedHashMap insertion order (and mention of access order)
    private static void linkedHashMapDemo() {
        System.out.println("-- LinkedHashMap order --");
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("first", 1);
        order.put("second", 2);
        order.put("third", 3);
        System.out.println("Insertion order: " + order);
    }

    // TreeMap sorted order with Comparator
    private static void treeMapDemo() {
        System.out.println("-- TreeMap sorted keys --");
        TreeMap<String, Integer> sorted = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        sorted.put("Banana", 1);
        sorted.put("apple", 2);
        sorted.put("cherry", 3);
        System.out.println("TreeMap (CI order): " + sorted);
        System.out.println("firstKey=" + sorted.firstKey() + ", ceiling('b')=" + sorted.ceilingKey("b"));
    }

    // Counting with computeIfAbsent/merge
    private static void computeAndMergeDemo() {
        System.out.println("-- computeIfAbsent / merge --");
        String text = "to be or not to be";
        Map<String, Integer> freq = new HashMap<>();
        for (String w : text.split(" ")) {
            // Two idioms: computeIfAbsent + set, or merge
            freq.merge(w, 1, Integer::sum);
        }
        System.out.println("Word frequencies: " + freq);
    }

    // equals/hashCode rules for keys
    private static void keyEqualityPitfallDemo() {
        System.out.println("-- Key equality pitfall --");
        class Key { // mutable key example (avoid mutating fields used in equality)
            String id;

            Key(String id) {
                this.id = id;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o)
                    return true;
                if (o == null || getClass() != o.getClass())
                    return false;
                Key key = (Key) o;
                return Objects.equals(id, key.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public String toString() {
                return "Key{" + id + '}';
            }
        }

        Map<Key, String> map = new HashMap<>();
        Key k = new Key("42");
        map.put(k, "answer");
        System.out.println("Before mutate: get(k)=" + map.get(k));
        k.id = "43"; // mutating key breaks retrieval
        System.out.println("After mutate: get(k)=" + map.get(k) + " (likely null)");
        System.out.println("ContainsKey(new Key('42'))? " + map.containsKey(new Key("42")));
    }
}
