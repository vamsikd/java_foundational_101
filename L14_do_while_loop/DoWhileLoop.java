package L14_do_while_loop;

public class DoWhileLoop {
    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println("i = " + i);
            i++;
        } while (i < 3);
        int start2 = 5;
        do {
            System.out.println("Runs once even though condition false (do-while)");
        } while (start2 < 0);
        String[] simulated = { "", " ", "ok" };
        int idx = 0;
        String accepted;
        do {
            accepted = simulated[idx];
            System.out.println("Read: '" + accepted + "'");
            idx++;
        } while ((accepted == null || accepted.isBlank()) && idx < simulated.length);
        System.out.println("Accepted: " + accepted);
        int n = 0;
        do {
            n++;
            if (n == 2)
                continue;
            if (n > 4)
                break;
            System.out.println("n = " + n);
        } while (true);
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
