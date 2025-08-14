package L34_Packages.com.example.util; // lesson-scoped package

public class StringUtils {
    public static String shout(String s) {
        return s == null ? "" : s.toUpperCase();
    }

    // Example of a method we might static import later
    public static String repeat(String s, int times) {
        if (s == null || times <= 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++)
            sb.append(s);
        return sb.toString();
    }
}
