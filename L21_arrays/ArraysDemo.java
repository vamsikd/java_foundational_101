package L21_arrays;

import java.util.Arrays;

public class ArraysDemo {
    // Internal state to demonstrate defensive copying
    private static int[] internalScores = { 1, 2, 3 };

    // Returns a defensive copy so callers cannot mutate internal array
    private static int[] getScores() {
        return internalScores.clone(); // clone() is shallow, fine for primitives
    }

    // Linear search (works on unsorted arrays). Returns index or -1 if not found.
    private static int indexOf(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // 1) Declaration & Allocation
        int[] zeros; // declaration (no elements yet)
        zeros = new int[3]; // allocation (default-initialized to 0)
        System.out.println("defaults: " + Arrays.toString(zeros));
        String[] names = { "Ann", "Bob" }; // literal shorthand
        System.out.println("names: " + Arrays.toString(names));

        // 2) Indexing & Bounds
        int[] numbers = { 10, 20, 30, 40, 50 };
        System.out.println("First number: " + numbers[0]); // valid index 0
        // Note: accessing numbers[numbers.length] would throw
        // ArrayIndexOutOfBoundsException
        // Always use arr.length as the upper bound (exclusive).

        // 3) Iteration Patterns
        // Classic for (index needed)
        for (int i = 0; i < numbers.length; i++)
            System.out.println("numbers[" + i + "]=" + numbers[i]);
        // Enhanced for-each (read-only traversal)
        for (int v : numbers)
            System.out.println("value = " + v);
        // While loop pattern
        int i = 0;
        while (i < numbers.length) {
            System.out.println("while numbers[" + i + "]=" + numbers[i]);
            i++;
        }
        // Reverse iteration
        for (int r = numbers.length - 1; r >= 0; r--)
            System.out.println("reverse numbers[" + r + "]=" + numbers[r]);
        // Two indices (symmetric compare demo)
        int[] maybePal = { 1, 2, 3, 2, 1 };
        boolean symmetric = true;
        for (int a = 0, b = maybePal.length - 1; a < b; a++, b--) {
            if (maybePal[a] != maybePal[b]) {
                symmetric = false;
                break;
            }
        }
        System.out.println("symmetric? " + symmetric);

        // 4) Core Utility Methods (java.util.Arrays)
        // clone(): copy same length (primitive arrays copied by value)
        int[] copy = numbers.clone();
        numbers[0] = 999; // mutate original
        System.out.println("original after mutate: " + Arrays.toString(numbers));
        System.out.println("clone unaffected:      " + Arrays.toString(copy));

        // copyOf: change length (pad with defaults if longer)
        int[] wider = Arrays.copyOf(copy, 7);
        System.out.println("copyOf len=7: " + Arrays.toString(wider));

        // copyOfRange: [from, to) end-exclusive
        int[] mid = Arrays.copyOfRange(copy, 1, 4);
        System.out.println("copyOfRange(1,4): " + Arrays.toString(mid));

        // fill: bulk overwrite
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("filled with 7: " + Arrays.toString(filled));

        // sort: in-place ascending
        int[] unsorted = { 5, 2, 9, 1 };
        Arrays.sort(unsorted);
        System.out.println("sorted: " + Arrays.toString(unsorted));

        // binarySearch: requires sorted array
        int foundAt = Arrays.binarySearch(unsorted, 5);
        int notFound = Arrays.binarySearch(unsorted, 6);
        int insertionPoint = -notFound - 1; // where 6 would be inserted
        System.out.println("binarySearch 5 -> index " + foundAt);
        System.out.println("binarySearch 6 -> " + notFound +
                " (insertionPoint=" + insertionPoint + ")");

        // equals vs deepEquals
        int[] a1 = { 1, 2 };
        int[] a2 = { 1, 2 };
        System.out.println("equals(a1,a2): " + Arrays.equals(a1, a2));
        int[][] m1 = { { 1, 2 }, { 3 } };
        int[][] m2 = { { 1, 2 }, { 3 } };
        System.out.println("deepEquals(m1,m2): " + Arrays.deepEquals(m1, m2));

        // toString vs deepToString
        System.out.println("toString(a1): " + Arrays.toString(a1));
        System.out.println("deepToString(m1): " + Arrays.deepToString(m1));

        // Arrays.stream helpers (works for primitive arrays)
        int sum = Arrays.stream(a1).sum();
        double avg = Arrays.stream(unsorted).average().orElse(0);
        System.out.println("sum(a1) = " + sum + ", avg(unsorted) = " + avg);

        // 5) Searching
        int[] bag = { 9, 4, 7, 2 }; // unsorted
        System.out.println("linear indexOf 7 -> " + indexOf(bag, 7));
        System.out.println("linear indexOf 5 -> " + indexOf(bag, 5));
        Arrays.sort(bag);
        System.out.println("bag sorted: " + Arrays.toString(bag));
        System.out.println("binarySearch 7 -> " + Arrays.binarySearch(bag, 7));

        // 6) Multidimensional & Jagged arrays
        int[][] grid = new int[2][3]; // rectangular 2x3 (all zeros)
        grid[0][1] = 5;
        System.out.println("grid: " + Arrays.deepToString(grid));

        int[][] jagged = new int[3][]; // lengths differ per row
        jagged[0] = new int[1];
        jagged[1] = new int[2];
        jagged[2] = new int[4];
        // initialize jagged with incremental values
        int val = 1;
        for (int r2 = 0; r2 < jagged.length; r2++)
            for (int c2 = 0; c2 < jagged[r2].length; c2++)
                jagged[r2][c2] = val++;
        System.out.println("jagged: " + Arrays.deepToString(jagged));

        // 7) Defensive Copying demo: expose a copy, not internals
        int[] external = getScores();
        external[0] = 42; // attempt to mutate external copy
        System.out.println("external mutated:  " + Arrays.toString(external));
        System.out.println("internal intact:  " + Arrays.toString(internalScores));

        // 8) Common pitfalls (commentary)
        // - Off-by-one: use i < arr.length, not i <= arr.length
        // - Hard-coded sizes: prefer arr.length
        // - Aliasing: arr2 = arr1 shares same storage; use clone/copyOf to separate
        // - For nested arrays, use deepEquals/deepToString.
    }
}
