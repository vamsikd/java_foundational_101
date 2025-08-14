package L05_strings;

// L05 - Strings: creation, inspection, slicing, searching, formatting, building
public class StringsDemo {
    public static void main(String[] args) {
        System.out.println("=== L05: Strings Demo ===");

        creation();
        coreMethods();
        formatting();
        builderVsConcat();
        equality();
        emptyVsBlank();
        splitJoinRepeat();

        System.out.println("=== End of L05 ===");
    }

    private static void creation() {
        System.out.println("-- Creation --");
        String literal = "Hello"; // pooled literal
        String constructed = new String("Hello"); // avoid unless needed
        char[] chars = { 'J', 'a', 'v', 'a' };
        String fromChars = new String(chars);
        String fromValueOf = String.valueOf(1234); // number to string
        System.out.println(literal + ", " + constructed + ", " + fromChars + ", " + fromValueOf);
    }

    private static void coreMethods() {
        System.out.println("-- Core methods --");
        String s = "  Java Strings API  ";
        System.out.println("length: " + s.length());
        System.out.println("charAt(2): " + s.charAt(2));
        System.out.println("substring(2, 6): '" + s.substring(2, 6) + "'");
        System.out.println("indexOf('Strings'): " + s.indexOf("Strings"));
        System.out.println("lastIndexOf('a'): " + s.lastIndexOf('a'));
        System.out.println("contains('API'): " + s.contains("API"));
        System.out.println("startsWith('  Java'): " + s.startsWith("  Java"));
        System.out.println("endsWith('API  '): " + s.endsWith("API  "));
        System.out.println("replace(' ', '_'): '" + s.replace(' ', '_') + "'");

        // substring and slicing safety
        String trimmed = s.trim(); // legacy trim (ASCII); strip is Unicode-aware
        System.out.println("trim(): '" + trimmed + "'");
        System.out.println("toLowerCase(): '" + s.toLowerCase() + "'");
        System.out.println("toUpperCase(): '" + s.toUpperCase() + "'");
    }

    private static void formatting() {
        System.out.println("-- Formatting --");
        int year = 2025;
        double price = 19.99;
        String concat = "Year:" + year + ", price=" + price;
        String fmt = String.format("Year:%d, price=%.2f", year, price);
        String printfLike = ("Year:%d, hex=%x").formatted(year, year);
        System.out.println(concat);
        System.out.println(fmt);
        System.out.println(printfLike);
    }

    private static void builderVsConcat() {
        System.out.println("-- Builder vs concat --");
        // Bad in loops: creates many intermediates
        String s = "";
        for (int i = 0; i < 5; i++)
            s += i; // illustrative
        System.out.println("concat in loop -> '" + s + "'");

        // Good: StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++)
            sb.append(i);
        System.out.println("StringBuilder loop -> '" + sb + "'");

        // Thread-safe builder (legacy)
        StringBuffer buf = new StringBuffer("Sync");
        buf.append("-0").append("-1").append("-2");
        System.out.println("StringBuffer example -> '" + buf + "'");
    }

    private static void equality() {
        System.out.println("-- Equality --");
        String a = "test";
        String b = new String("test");
        System.out.println("a == b: " + (a == b)); // reference compare
        System.out.println("a.equals(b): " + a.equals(b)); // content compare
        System.out.println("compareTo: 'a' vs 'b' -> " + "a".compareTo("b"));
    }

    private static void emptyVsBlank() {
        System.out.println("-- Empty vs Blank --");
        String empty = "";
        String blanks = "  \t\n ";
        System.out.println("empty length: " + empty.length());
        System.out.println("isEmpty: " + empty.isEmpty());
        System.out.println("isBlank: empty -> " + empty.isBlank() + ", blanks -> " + blanks.isBlank());
        System.out.println(
                "strip vs trim: '  café  '.strip()='" + "  café  ".strip() + "', trim()='" + "  café  ".trim() + "'");
    }

    private static void splitJoinRepeat() {
        System.out.println("-- split, join, repeat --");
        String csv = "a,b,c";
        String[] parts = csv.split(",");
        System.out.println("split -> length=" + parts.length + ", second='" + parts[1] + "'");
        String joined = String.join("|", parts);
        System.out.println("join -> '" + joined + "'");
        System.out.println("repeat -> 'na'.repeat(4) + ' Batman!' => '" + "na".repeat(4) + " Batman!'");
    }
}
