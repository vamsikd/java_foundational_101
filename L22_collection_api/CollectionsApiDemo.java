package L22_collection_api;

import java.util.*;

// Lesson 22: Java Collections API — interfaces, hierarchy, and practical uses
// Focus: Collection, List, Set, Queue/Deque, Map (not a Collection), with small demos and notes.
public class CollectionsApiDemo {
    public static void main(String[] args) {
        System.out.println("=== L22: Collections API Demo ===");
        interfacesAndHierarchyOverview();
        listExamples();
        setExamples();
        queueAndDequeExamples();
        mapExamples();
        System.out.println("=== End of Collections API Demo ===");
    }

    // 1) Interfaces & hierarchy quick tour
    private static void interfacesAndHierarchyOverview() {
        System.out.println("-- Interfaces & hierarchy --");
        System.out.println("Collection <- List, Set, Queue");
        System.out.println("Deque extends Queue");
        System.out.println("SortedSet / NavigableSet for ordered sets");
        System.out.println("Map is NOT a Collection; SortedMap / NavigableMap for ordered maps");
    }

    // 2) Lists
    private static void listExamples() {
        System.out.println("-- List: ordered, indexed, duplicates allowed --");

        List<String> arrayList = new ArrayList<>(); // good random access, resize array
        arrayList.add("alpha");
        arrayList.add("beta");
        arrayList.add("beta");
        arrayList.add(1, "inserted");
        System.out.println("ArrayList: " + arrayList + ", get(2)=" + arrayList.get(2));

        List<String> linkedList = new LinkedList<>(); // good insert/remove at ends, queue/deque ops
        linkedList.add("head");
        linkedList.add("mid");
        linkedList.add("tail");
        linkedList.addFirst("first"); // LinkedList specific
        System.out.println("LinkedList: " + linkedList + ", removeFirst()=" + linkedList.removeFirst());
    }

    // 3) Sets
    private static void setExamples() {
        System.out.println("-- Set: unique elements, no duplicates --");

        Set<Integer> hashSet = new HashSet<>(); // no order guarantees
        hashSet.addAll(Arrays.asList(3, 1, 2, 2, 3));
        System.out.println("HashSet (no order, unique): " + hashSet);

        Set<Integer> linkedHashSet = new LinkedHashSet<>(); // insertion order
        linkedHashSet.addAll(Arrays.asList(3, 1, 2, 2, 3));
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);

        Set<Integer> treeSet = new TreeSet<>(); // sorted ascending (natural order)
        treeSet.addAll(Arrays.asList(3, 1, 2));
        System.out.println("TreeSet (sorted): " + treeSet + ", first=" + ((TreeSet<Integer>) treeSet).first());
    }

    // 4) Queue and Deque
    private static void queueAndDequeExamples() {
        System.out.println("-- Queue & Deque --");

        // Queue: FIFO behavior (e.g., LinkedList or ArrayDeque). PriorityQueue orders
        // by priority
        Queue<String> fifo = new ArrayDeque<>();
        fifo.offer("A");
        fifo.offer("B");
        fifo.offer("C");
        System.out.print("ArrayDeque as Queue (FIFO poll): ");
        while (!fifo.isEmpty())
            System.out.print(fifo.poll());
        System.out.println();

        // PriorityQueue: orders elements by natural order (or comparator)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(3);
        System.out.print("PriorityQueue (ascending): ");
        while (!pq.isEmpty())
            System.out.print(pq.poll() + " ");
        System.out.println();

        // Deque: double-ended queue operations
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(2);
        deque.addLast(4);
        deque.addFirst(1); // [1,2,4]
        System.out.println("Deque: " + deque + ", peekFirst=" + deque.peekFirst() + ", peekLast=" + deque.peekLast());
    }

    // 5) Maps (not a Collection)
    private static void mapExamples() {
        System.out.println("-- Map: key->value associations (not a Collection) --");

        Map<String, Integer> hashMap = new HashMap<>(); // no order
        hashMap.put("alice", 3);
        hashMap.put("bob", 5);
        hashMap.put("carol", 2);
        System.out.println("HashMap: value of bob = " + hashMap.get("bob") + ", keys=" + hashMap.keySet());

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(); // insertion order
        linkedHashMap.put("z", 1);
        linkedHashMap.put("a", 2);
        linkedHashMap.put("m", 3);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);

        Map<String, Integer> treeMap = new TreeMap<>(); // sorted by key
        treeMap.put("z", 1);
        treeMap.put("a", 2);
        treeMap.put("m", 3);
        System.out.println("TreeMap (sorted by key): " + treeMap + ", firstKey="
                + ((TreeMap<String, Integer>) treeMap).firstKey());

        // Common traversal
        System.out.print("Iterate entries: ");
        for (Map.Entry<String, Integer> e : treeMap.entrySet()) {
            System.out.print(e.getKey() + "=" + e.getValue() + " ");
        }
        System.out.println();
    }
}
