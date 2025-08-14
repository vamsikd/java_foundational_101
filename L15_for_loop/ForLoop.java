package L15_for_loop;

public class ForLoop {
    public static void main(String[] args) {
        // Basic counted for-loop
        // for (init; condition; update) { body }
        // i starts at 0; loop runs while i < 5; i increments after each iteration
        for (int i = 0; i < 5; i++)
            System.out.println("i = " + i);

        // Countdown loop: starts high and decrements
        // Prints 5 down to 1 (since condition is n > 0)
        for (int n = 5; n > 0; n--)
            System.out.println("n = " + n);

        // Step by 2: generate even numbers from 0 to 10 inclusive
        for (int e = 0; e <= 10; e += 2)
            System.out.println("even: " + e);

        // Multiple loop variables: advance a up and b down until they cross
        for (int a = 0, b = 10; a < b; a++, b--)
            System.out.println("a=" + a + ", b=" + b);

        // For-loop as a while-loop: init outside, update inside
        int k = 0;
        for (; k < 3;) {
            System.out.println("k = " + k);
            k++;
        }

        // Infinite loop pattern with explicit break for termination
        int count = 0;
        for (;;) {
            if (count >= 3)
                break;
            System.out.println("count = " + count);
            count++;
        }

        // Using continue to skip even numbers; prints only odd x values
        for (int x = 1; x <= 5; x++) {
            if (x % 2 == 0)
                continue;
            System.out.println("odd x: " + x);
        }

        // Enhanced for-loop over int array: accumulate sum
        int[] nums = { 1, 2, 3, 4 };
        int sum = 0;
        for (int value : nums)
            sum += value;
        System.out.println("sum = " + sum);

        // Enhanced for-loop over characters: iterate a String via toCharArray()
        String word = "Hi";
        for (char ch : word.toCharArray())
            System.out.println("ch = " + ch);

        // Nested loops over a 2D array using indices
        int[][] grid = { { 1, 2 }, { 3, 4 } };
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                System.out.println("grid[" + row + "][" + col + "]=" + grid[row][col]);

        // Labeled break: exit the outer loop from inside an inner loop
        outer: for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                if (r == 1 && c == 1)
                    break outer;
                System.out.println("r=" + r + ", c=" + c);
            }

        // Accumulation within a loop: sum of squares from 1 to 5
        int totalSquares = 0;
        for (int v = 1; v <= 5; v++)
            totalSquares += v * v;
        System.out.println("Sum of squares 1..5 = " + totalSquares);

        // Search pattern: find target index, break when found
        int[] data = { 4, 9, 15, 22 };
        int target = 15;
        int index = -1;
        for (int idx = 0; idx < data.length; idx++)
            if (data[idx] == target) {
                index = idx;
                break;
            }
        System.out.println("Found target index = " + index);

        // Building a comma-separated string efficiently with StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 5; t++) {
            if (t > 0)
                sb.append(", ");
            sb.append(t);
        }
        System.out.println("List: " + sb);

        // Index-based loop over String array (shows access with index)
        String[] names = { "A", "B", "C" };
        for (int p = 0; p < names.length; p++)
            System.out.println(names[p]);
    }
}
