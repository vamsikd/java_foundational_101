# Hello World in Java

This project demonstrates a simple "Hello World" program in Java. It is intended for beginners to understand the basic Java execution flow, including compilation and running the program, as well as the roles of JDK, JRE, and JVM.

## 0.1. What is this?
This is a basic "Hello World" example for Java. It prints `Hello, World!` to the console.

### Lesson Order
1. `L01_hello/Hello.java` – First program & execution flow
2. `L02_variables/Variables.java` – Primitive types overview
3. `L03_type_conversions/TypeConversions.java` – Implicit vs explicit casts, overflow, promotion
4. `L04_operators/Operators.java` – Arithmetic, comparison, logical, bitwise, shifts, ternary, concat
5. `L05_strings/StringsDemo.java` – Creation, immutability, methods, formatting, builder
6. `L11_conditionals_if/ConditionalsIf.java` – if, else, chains, ternary, guard clauses
7. `L12_conditionals_switch/ConditionalsSwitch.java` – switch statement & expression, modern syntax
8. `L13_while_loop/WhileLoop.java` – while, break, continue, sentinel, do-while contrast
9. `L14_do_while_loop/DoWhileLoop.java` – do-while, guaranteed first run, patterns
10. `L15_for_loop/ForLoop.java` – classic for, enhanced for-each, patterns, pitfalls

## 0.2. Java Execution Flow: Source Code to Compiled Code
The journey: you write source (.java) -> compile to bytecode (.class) -> JVM interprets/JITs and runs it.

Compile a single lesson (example L01):
```pwsh
javac L01_hello/Hello.java
```
Run it (use package-qualified class name):
```pwsh
java L01_hello.Hello
```
Compile several lessons at once:
```pwsh
javac L01_hello/Hello.java L11_conditionals_if/ConditionalsIf.java L21_arrays/ArraysDemo.java
```
Run another lesson (e.g., Arrays):
```pwsh
java L21_arrays.ArraysDemo
```

## 0.3. Using `java` to Run Compiled Code

## L01. Java Basics: JDK, JRE, JVM
  - A software development kit for Java. It includes tools like `javac` (compiler), `java` (launcher), and other utilities.
  - Needed for writing and compiling Java programs.
  - Contains the libraries and JVM required to run Java applications.
  - Does **not** include development tools like the compiler.
  - The engine that runs Java bytecode. It is platform-independent.
  - Converts bytecode into machine code for your operating system.

### How They Work Together
1. **Write code** in `Hello.java` (requires JDK).
2. **Compile** with `javac Hello.java` (JDK compiles to `Hello.class`).
3. **Run** with `java Hello` (JRE/JVM loads and executes the bytecode).
4. **Output**: The program prints `Hello, World!` to your screen.

## Lesson Summaries (L01 - L15)

Detailed lesson write-ups moved to separate markdown files under `docs/`. Below is a concise summary list with links to both the doc and source code file.

| Lesson | Topic | Summary |
|--------|-------|---------|
| L01 | [Basics](docs/L01.md) | JDK vs JRE vs JVM, compile→run flow. Source: `L01_hello/Hello.java` |
| L02 | [Primitives](docs/L02.md) | 8 primitive types, ranges, usage. Source: `L02_variables/Variables.java` |
| L03 | [Type Conversions](docs/L03.md) | Widening, narrowing, overflow, promotion. Source: `L03_type_conversions/TypeConversions.java` |
| L04 | [Operators](docs/L04.md) | Arithmetic, logical, bitwise, shifts, ternary. Source: `L04_operators/Operators.java` |
| L05 | [Strings](docs/L05.md) | Immutability, APIs, formatting, performance. Source: `L05_strings/StringsDemo.java` |
| L11 | [Conditionals If](docs/L11.md) | if/else chains, guard clauses, ternary. Source: `L11_conditionals_if/ConditionalsIf.java` |
| L12 | [Switch](docs/L12.md) | Classic & modern switch expressions. Source: `L12_conditionals_switch/ConditionalsSwitch.java` |
| L13 | [While Loop](docs/L13.md) | Pre-test loop, sentinel, break/continue. Source: `L13_while_loop/WhileLoop.java` |
| L14 | [Do-While](docs/L14.md) | Post-test loop, guaranteed first run. Source: `L14_do_while_loop/DoWhileLoop.java` |
| L15 | [For Loop](docs/L15.md) | Classic & enhanced for-each, patterns. Source: `L15_for_loop/ForLoop.java` |
| L22 | [Collections API](docs/L22.md) | Interfaces, hierarchy, and practical uses. Source: `L22_collection_api/CollectionsApiDemo.java` |
| L23 | [List](docs/L23.md) | ArrayList vs LinkedList, generics, sorting, removal, interop. Source: `L23_list/ListsDemo.java` |
| L24 | [Set](docs/L24.md) | HashSet/LinkedHashSet/TreeSet, dedup, set ops, equals/hashCode. Source: `L24_set/SetsDemo.java` |
| L25 | [Map](docs/L25.md) | HashMap/LinkedHashMap/TreeMap, iteration, compute/merge, key rules. Source: `L25_map/MapsDemo.java` |
| L21 | [Arrays](docs/L21.md) | Declaration, iteration, copy, sort, search. Source: `L21_arrays/ArraysDemo.java` |
| L31 | [Classes](docs/L31.md) | Fields, methods, constructors, nested types. Source: `L31_classes/ClassesDemo.java` |
| L32 | [Encapsulation](docs/L32.md) | Access control, getters/setters, defensive copies. Source: `L32_encapsulation/EncapsulationDemo.java` |
| L33 | [Inheritance](docs/L33.md) | this vs super, multi-level, overriding, polymorphism. Source: `L33_inheritance/InheritanceDemo.java` |
| L34 | [Packages](docs/L34.md) | Declaring, importing, static imports, best practices. Source: `L34_Packages/PackagesDemo.java` |
| L35 | [Access Modifiers](docs/L35.md) | public, private, protected, package-private, design guidelines. Source: `L35_access_modifiers/AccessModifiersDemo.java` |
| L36 | [Static Keyword](docs/L36.md) | static fields/methods, blocks, nested classes, factories. Source: `L36_static_keyword/StaticKeywordDemo.java` |
| L37 | [Final Keyword](docs/L37.md) | final variables, fields, methods, classes, immutability. Source: `L37_final_keyword/FinalKeywordDemo.java` |
| L38 | [Wrapper Classes](docs/L38.md) | Boxing/unboxing, caching, parsing, null safety, performance. Source: `L38_wrapper_classes/WrapperClassesDemo.java` |
| L39 | [Abstract Classes](docs/L39.md) | Partial implementation, abstract methods, template pattern. Source: `L39_abstract_classes/AbstractClassesDemo.java` |
| L40 | [Inner Classes](docs/L40.md) | Member, static nested, local, anonymous, lambdas contrast. Source: `L40_inner_classes/InnerClassesDemo.java` |
| L41 | [Interfaces](docs/L41.md) | Contracts, default/static/ private methods, multiple inheritance. Source: `L41_interfaces/InterfacesDemo.java` |
| L42 | [Enums](docs/L42.md) | Constants with behavior, switching, strategies. Source: `L42_enums/EnumsDemo.java` |
| L43 | [Annotations](docs/L43.md) | Metadata, custom annotations, retention, processing basics. Source: `L43_annotations/AnnotationsDemo.java` |
| L44 | [Interface Types](docs/L44.md) | Regular vs functional vs marker, lambdas, composition. Source: `L44_interface_types/InterfaceTypesDemo.java` |
| L51 | [Exceptions](docs/L51.md) | Checked vs unchecked, handling patterns, custom exceptions. Source: `L51_exceptions/ExceptionsDemo.java` |
| L52 | [Checked vs Unchecked](docs/L52.md) | Hierarchy, differences, API design choices. Source: `L52_checked_unchecked/CheckedUncheckedDemo.java` |
| L53 | [throw vs throws](docs/L53.md) | Keyword roles, declaration vs action, wrapping patterns. Source: `L53_throw_throws/ThrowThrowsDemo.java` |
| L61 | [Threads](docs/L61.md) | Create/join, race conditions, interrupts, daemon, best practices. Source: `L61_threads/ThreadsDemo.java` |
| L62 | [Runnable](docs/L62.md) | Runnable vs Thread, reuse, interrupts, shared state patterns. Source: `L62_runnable/RunnableDemo.java` |

Next up (planned): methods, collections, objects equality, concurrency utilities (executors, futures).

See each `docs/Lxx.md` for: detailed explanations, pitfalls, best practices, mini-practice exercises, and takeaways.

Cheat summary:
- JDK = JRE + compiler & dev tools
- JRE = JVM + standard libraries
- JVM = executes bytecode (platform independent)

Contributing / Learning Flow:
1. Read a summary in the table above.
2. Open the corresponding `docs/Lxx.md` for depth.
3. Run the matching `Lxx_*.java` file to see output.
4. Tweak code, recompile, re-run.

Want more? Add a new lesson file (e.g. `L16_Arrays.java`) and a doc `docs/L16.md` following the pattern.

Happy learning—keep experiments small and focused.

