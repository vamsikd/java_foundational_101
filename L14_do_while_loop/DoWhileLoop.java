package L14_do_while_loop;

public class DoWhileLoop {
    public static void main(String[] args) {
        // Do-while: body runs first, condition is checked after each iteration.
        // Guarantees the body executes at least once.
        int i = 0;
        do {
            System.out.println("i = " + i);
            i++;
        } while (i < 3);

        // Run-once demo: condition is false initially, but body still executes once.
        int start2 = 5;
        do {
            System.out.println("Runs once even though condition false (do-while)");
        } while (start2 < 0);

        // Input/read loop: read at least once, stop when a non-blank value is
        // encountered
        // or when we exhaust the simulated inputs.
        String[] simulated = { "", " ", "ok" };
        int idx = 0;
        String accepted;
        do {
            accepted = simulated[idx];
            System.out.println("Read: '" + accepted + "'");
            idx++;
        } while ((accepted == null || accepted.isBlank()) && idx < simulated.length);
        System.out.println("Accepted: " + accepted);

        // Using continue/break inside do-while:
        // - continue: skip printing when n==2
        // - break: exit loop when n>4
        int n = 0;
        do {
            n++;
            if (n == 2)
                continue;
            if (n > 4)
                break;
            System.out.println("n = " + n);
        } while (true);

        // Search pattern with do-while: checks array at least once, advances until
        // found or end is reached (guarded by second condition).
        int[] values = { 42, 50, 60 };
        int target = 50;
        int pos = 0;
        boolean found;
        do {
            found = values[pos] == target;
            if (!found)
                pos++;
        } while (!found && pos < values.length);
        System.out.println("Found target? " + found + ", pos=" + (found ? pos : -1));
    }
}
