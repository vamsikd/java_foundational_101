package L13_while_loop;

public class WhileLoop {
    public static void main(String[] args) {
        // Basic counting while: check condition first, then run body if true
        int i = 0;
        while (i < 3) {
            System.out.println("i = " + i);
            i++;
        }

        // Countdown pattern: decrement until condition fails
        int countdown = 3;
        while (countdown > 0) {
            System.out.println("Countdown: " + countdown);
            countdown--;
        }
        System.out.println("Liftoff!");

        // while(true) with break: loop until an explicit exit condition occurs
        int num = 1;
        while (true) {
            if (num > 5)
                break;
            System.out.println("num = " + num);
            num++;
        }

        // Using continue: skip to next iteration (here: print only odd numbers)
        int k = 0;
        while (k < 6) {
            k++;
            if (k % 2 == 0)
                continue;
            System.out.println("Odd k: " + k);
        }

        // Sentinel/guarded search: stop early when found (short-circuit condition)
        int[] data = { 3, 9, 12, 18, 25 };
        int target = 18;
        int index = 0;
        boolean found = false;
        while (index < data.length && !found) {
            if (data[index] == target) {
                found = true;
            } else {
                index++;
            }
        }
        System.out.println("Found? " + found + ", index=" + (found ? index : -1));

        // Input validation loop: accept first non-blank input, otherwise keep trying
        String[] simulatedInputs = { "", "  ", "ok" };
        int attempt = 0;
        String accepted = null;
        while (attempt < simulatedInputs.length) {
            String raw = simulatedInputs[attempt];
            if (raw != null && !raw.isBlank()) {
                accepted = raw;
                break;
            }
            attempt++;
        }
        System.out.println("Accepted input: " + accepted);

        // do-while: runs body at least once, then checks the condition
        int b = 0;
        do {
            System.out.println("do-while executes at least once");
        } while (b < 0);
    }
}
