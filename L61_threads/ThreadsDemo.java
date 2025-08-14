package L61_threads;

// Lesson 61: Threads — creation, coordination, rules, and best practices
// Keep examples small, deterministic, and print key steps so learners can follow.
public class ThreadsDemo {
    public static void main(String[] args) {
        System.out.println("=== L61: Threads Demo ===");

        // 1) Create and start threads in three common ways
        threadCreationExamples();

        // 2) Join (wait for) threads to finish
        joinExample();

        // 3) Race condition vs synchronized fix
        raceConditionAndFix();

        // 4) Interrupts and cooperative cancellation
        interruptExample();

        // 5) Daemon threads (background helpers)
        daemonExample();

        System.out.println("=== End of Threads Demo ===");
    }

    // --- 1) Creating threads ---
    private static void threadCreationExamples() {
        System.out.println("-- Creation: extends Thread, implements Runnable, lambda Runnable --");

        // a) Extending Thread (simple but less flexible)
        class MyThread extends Thread {
            public void run() { // entry point for thread
                System.out.println("[MyThread] running on " + Thread.currentThread().getName());
            }
        }
        Thread t1 = new MyThread();

        // b) Implementing Runnable (preferred, separates task from thread)
        class MyTask implements Runnable {
            public void run() {
                System.out.println("[MyTask] running on " + Thread.currentThread().getName());
            }
        }
        Thread t2 = new Thread(new MyTask());

        // c) Runnable via lambda
        Thread t3 = new Thread(() -> System.out.println("[LambdaTask] running on " + Thread.currentThread().getName()));

        t1.start();
        t2.start();
        t3.start();

        // Wait briefly so output appears before next section
        sleepSilently(100);
    }

    // --- 2) Joining threads ---
    private static void joinExample() {
        System.out.println("-- Join: wait for a worker to finish --");
        Thread worker = new Thread(() -> {
            System.out.println("[JoinWorker] started");
            sleepSilently(150);
            System.out.println("[JoinWorker] finished");
        });

        worker.start();
        try {
            worker.join(); // main waits until worker completes
        } catch (InterruptedException e) {
            System.out.println("main interrupted while joining");
            Thread.currentThread().interrupt();
        }
        System.out.println("[main] after join");
    }

    // --- 3) Race condition and synchronized fix ---
    private static void raceConditionAndFix() {
        System.out.println("-- Race condition vs synchronized --");

        // Shared counter without synchronization (not thread-safe)
        class UnsafeCounter {
            private int value = 0; // not atomic

            void increment() {
                value = value + 1;
            } // race-prone

            int get() {
                return value;
            }
        }

        // Same counter with synchronization for correctness
        class SafeCounter {
            private int value = 0;

            synchronized void increment() {
                value = value + 1;
            } // synchronized method

            synchronized int get() {
                return value;
            }
        }

        int iterations = 5_000; // small but enough to often show a race
        UnsafeCounter uc = new UnsafeCounter();
        Thread u1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                uc.increment();
        });
        Thread u2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                uc.increment();
        });

        u1.start();
        u2.start();
        joinSilently(u1);
        joinSilently(u2);
        System.out.println("[UnsafeCounter] expected=" + (iterations * 2) + ", actual=" + uc.get());

        SafeCounter sc = new SafeCounter();
        Thread s1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                sc.increment();
        });
        Thread s2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                sc.increment();
        });

        s1.start();
        s2.start();
        joinSilently(s1);
        joinSilently(s2);
        System.out.println("[SafeCounter] expected=" + (iterations * 2) + ", actual=" + sc.get());
    }

    // --- 4) Interrupts ---
    private static void interruptExample() {
        System.out.println("-- Interrupts: cooperative cancellation --");
        Thread sleeper = new Thread(() -> {
            System.out.println("[Interruptible] going to sleep");
            try {
                Thread.sleep(300);
                System.out.println("[Interruptible] woke normally");
            } catch (InterruptedException e) {
                System.out.println("[Interruptible] interrupted during sleep");
                Thread.currentThread().interrupt(); // preserve status if needed
            }
        });

        sleeper.start();
        // Give it time to start sleeping, then interrupt
        sleepSilently(50);
        sleeper.interrupt();
        joinSilently(sleeper);
        System.out.println("[main] interrupt demo done");
    }

    // --- 5) Daemon threads ---
    private static void daemonExample() {
        System.out.println("-- Daemon threads --");
        Thread daemon = new Thread(() -> {
            while (true) { // loop a few times but exit quickly
                System.out.println("[Daemon] background tick");
                sleepSilently(40);
                // Break after a few ticks for deterministic output
                break;
            }
        });
        daemon.setDaemon(true); // JVM won't wait for daemon threads at shutdown
        daemon.start();
        sleepSilently(60); // allow one tick to print
        System.out.println("[main] daemon demo done");
    }

    // Utilities: small helpers for sleep/join with clear behavior
    private static void sleepSilently(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    private static void joinSilently(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
