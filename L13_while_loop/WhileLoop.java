package L13_while_loop;

public class WhileLoop {
    public static void main(String[] args) {
        int i = 0;
        while (i < 3) {
            System.out.println("i = " + i);
            i++;
        }
        int countdown = 3;
        while (countdown > 0) {
            System.out.println("Countdown: " + countdown);
            countdown--;
        }
        System.out.println("Liftoff!");
        int num = 1;
        while (true) {
            if (num > 5)
                break;
            System.out.println("num = " + num);
            num++;
        }
        int k = 0;
        while (k < 6) {
            k++;
            if (k % 2 == 0)
                continue;
            System.out.println("Odd k: " + k);
        }
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
        int b = 0;
        do {
            System.out.println("do-while executes at least once");
        } while (b < 0);
    }
}
