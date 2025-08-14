package L11_conditionals_if;

public class ConditionalsIf {
    public static void main(String[] args) {
        int score = 75;
        if (score >= 50) {
            System.out.println("Pass (basic if)");
        }
        if (score >= 90) {
            System.out.println("Grade A");
        } else {
            System.out.println("Not Grade A");
        }
        if (score >= 90) {
            System.out.println("Chain: A");
        } else if (score >= 80) {
            System.out.println("Chain: B");
        } else if (score >= 70) {
            System.out.println("Chain: C");
        } else {
            System.out.println("Chain: Below C");
        }
        int age = 20;
        boolean hasTicket = true;
        if (age >= 18) {
            if (hasTicket) {
                System.out.println("Entry allowed");
            } else {
                System.out.println("Need a ticket");
            }
        } else {
            System.out.println("Must be 18+");
        }
        boolean isMember = true;
        double price = 120.0;
        if (isMember && price > 100) {
            System.out.println("Member discount applied");
        }
        if (!isMember || price <= 100) {
            System.out.println("No large purchase member discount");
        }
        String input = "hi";
        if (input == null || input.isBlank()) {
            System.out.println("Invalid input");
        } else {
            System.out.println("Input length: " + input.length());
        }
        int number = -3;
        String sign = number > 0 ? "positive" : number == 0 ? "zero" : "negative";
        System.out.println("sign: " + sign);
        boolean ready = true;
        if (ready) {
            System.out.println("Ready is true");
        }
        int x = 5, y = 12;
        if (!(x > 0 && y < 10)) {
            System.out.println("Either x <= 0 or y >= 10");
        }
        if (score > 60) {
            int bonus = 10;
            System.out.println("Bonus inside block: " + bonus);
        }
        System.out.println("safeDivide(10,2) = " + safeDivide(10, 2));
        System.out.println("safeDivide(10,0) = " + safeDivide(10, 0));
    }

    private static double safeDivide(double a, double b) {
        if (b == 0) {
            return Double.NaN;
        }
        return a / b;
    }
}
