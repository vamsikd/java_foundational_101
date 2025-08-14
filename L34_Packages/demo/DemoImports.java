package L34_Packages.demo; // matches folder path

// Demonstration of different import styles.

// 1. Single-type import
import L34_Packages.com.example.math.Calculator;
// 2. On-demand (wildcard) import - brings all public types in the util package into scope
import L34_Packages.com.example.util.*;
// 3. Static import (specific member)
import static L34_Packages.com.example.math.Calculator.add;
// 4. Static on-demand import (all public static members)
import static L34_Packages.com.example.util.StringUtils.*;

/**
 * Demo entry showing package usage. Compile from project root: javac
 * L34_Packages\\**\\*.java
 */
public class DemoImports {
    public static void main(String[] args) {
        int sum = Calculator.add(3, 4); // single-type import
        int sum2 = add(5, 6); // static import of Calculator.add
        String loud = StringUtils.shout("hello"); // via wildcard type import
        String repeated = repeat("Hi", 3); // static on-demand import
        System.out.println("sum=" + sum + ", sum2=" + sum2 + ", loud=" + loud + ", repeated=" + repeated);

        // Fully qualified reference (no import needed, but verbose)
        int diff = L34_Packages.com.example.math.Calculator.sub(10, 3);
        System.out.println("diff=" + diff);
    }
}
