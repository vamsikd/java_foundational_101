package L15_for_loop;

public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            System.out.println("i = " + i);
        for (int n = 5; n > 0; n--)
            System.out.println("n = " + n);
        for (int e = 0; e <= 10; e += 2)
            System.out.println("even: " + e);
        for (int a = 0, b = 10; a < b; a++, b--)
            System.out.println("a=" + a + ", b=" + b);
        int k = 0;
        for (; k < 3;) {
            System.out.println("k = " + k);
            k++;
        }
        int count = 0;
        for (;;) {
            if (count >= 3)
                break;
            System.out.println("count = " + count);
            count++;
        }
        for (int x = 1; x <= 5; x++) {
            if (x % 2 == 0)
                continue;
            System.out.println("odd x: " + x);
        }
        int[] nums = { 1, 2, 3, 4 };
        int sum = 0;
        for (int value : nums)
            sum += value;
        System.out.println("sum = " + sum);
        String word = "Hi";
        for (char ch : word.toCharArray())
            System.out.println("ch = " + ch);
        int[][] grid = { { 1, 2 }, { 3, 4 } };
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                System.out.println("grid[" + row + "][" + col + "]=" + grid[row][col]);
        outer: for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                if (r == 1 && c == 1)
                    break outer;
                System.out.println("r=" + r + ", c=" + c);
            }
        int totalSquares = 0;
        for (int v = 1; v <= 5; v++)
            totalSquares += v * v;
        System.out.println("Sum of squares 1..5 = " + totalSquares);
        int[] data = { 4, 9, 15, 22 };
        int target = 15;
        int index = -1;
        for (int idx = 0; idx < data.length; idx++)
            if (data[idx] == target) {
                index = idx;
                break;
            }
        System.out.println("Found target index = " + index);
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 5; t++) {
            if (t > 0)
                sb.append(", ");
            sb.append(t);
        }
        System.out.println("List: " + sb);
        String[] names = { "A", "B", "C" };
        for (int p = 0; p < names.length; p++)
            System.out.println(names[p]);
    }
}
