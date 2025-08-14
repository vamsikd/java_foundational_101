package L21_arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        int[] numbers = { 10, 20, 30, 40, 50 };
        System.out.println("First number: " + numbers[0]);
        for (int i = 0; i < numbers.length; i++)
            System.out.println("numbers[" + i + "]=" + numbers[i]);
        for (int v : numbers)
            System.out.println("value = " + v);
        int[] copy = numbers.clone();
        numbers[0] = 999;
        System.out.println("copy[0] remains = " + copy[0]);
        java.util.Arrays.sort(numbers);
        System.out.println("sorted: " + java.util.Arrays.toString(numbers));
    }
}
