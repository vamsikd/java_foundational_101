package L62_runnable;

// Lesson 62: Runnable — what it is, how it differs from Thread, and how to use it well
// Focus: decoupling task (Runnable) from execution (Thread), rules, and best practices.
public class RunnableDemo {
    public static void main(String[] args) {
        System.out.println("=== L62: Runnable Demo ===");

        // 1) Runnable vs Thread
        runnableVsThread();

        // 2) Reuse and naming
        reuseAndNaming();

        // 3) Shared state: race vs synchronized fix
        sharedStateRaceAndFix();

        // 4) Interrupt handling in run()
        interruptHandling();

        System.out.println("=== End of Runnable Demo ===");
    }

    // --- 1) Runnable vs Thread ---
    private static void runnableVsThread() {
        System.out.println("-- Runnable vs Thread --");

        // a) Extends Thread: couples work with thread
        class WorkerThread extends Thread {
            public WorkerThread(String name) {
                super(name);
            }

            @Override
            public void run() {
                System.out.println("[WorkerThread] running on " + Thread.currentThread().getName());
            }
        }
        Thread tA = new WorkerThread("Extender-1");

        // b) Implements Runnable: preferred — separate task from thread
        class PrintTask implements Runnable {
            @Override
            public void run() {
                System.out.println("[PrintTask] running on " + Thread.currentThread().getName());
            }
        }
        Runnable task = new PrintTask();
        Thread tB = new Thread(task, "Runnable-1");

        // c) Lambda runnable
        Thread tC = new Thread(() -> System.out.println("[LambdaTask] running on " + Thread.currentThread().getName()),
                "Runnable-2");

        tA.start();
        tB.start();
        tC.start();
        joinSilently(tA);
        joinSilently(tB);
        joinSilently(tC);
    }

    // --- 2) Reuse and naming ---
    private static void reuseAndNaming() {
        System.out.println("-- Reuse a stateless Runnable & name threads --");
        // Stateless task can be reused by multiple threads safely
        Runnable helloTask = () -> System.out.println("[HelloTask] hi from " + Thread.currentThread().getName());

        Thread u1 = new Thread(helloTask, "User-1");
        Thread u2 = new Thread(helloTask, "User-2");
        u1.start();
        u2.start();
        joinSilently(u1);
        joinSilently(u2);
    }

    // --- 3) Shared mutable state: race vs fix ---
    private static void sharedStateRaceAndFix() {
        System.out.println("-- Shared state: race vs synchronized --");
        final int iterations = 5_000;

        // Shared object with non-atomic increment
        class Counter {
            int v = 0;

            void inc() {
                v = v + 1;
            }

            int get() {
                return v;
            }
        }
        Counter unsafe = new Counter();

        Runnable r1 = () -> {
            for (int i = 0; i < iterations; i++)
                unsafe.inc();
        };
        Runnable r2 = () -> {
            for (int i = 0; i < iterations; i++)
                unsafe.inc();
        };

        Thread a = new Thread(r1, "Race-A");
        Thread b = new Thread(r2, "Race-B");
        a.start();
        b.start();
        joinSilently(a);
        joinSilently(b);
        System.out.println("[Unsafe] expected=" + (iterations * 2) + ", actual=" + unsafe.get());

        // Synchronized version
        class SafeCounter {
            int v = 0;

            synchronized void inc() {
                v = v + 1;
            }

            synchronized int get() {
                return v;
            }
        }
        SafeCounter safe = new SafeCounter();
        Thread c = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                safe.inc();
        }, "Safe-A");
        Thread d = new Thread(() -> {
            for (int i = 0; i < iterations; i++)
                safe.inc();
        }, "Safe-B");
        c.start();
        d.start();
        joinSilently(c);
        joinSilently(d);
        System.out.println("[Safe] expected=" + (iterations * 2) + ", actual=" + safe.get());
    }

    // --- 4) Interrupt handling ---
    private static void interruptHandling() {
        System.out.println("-- Interrupt handling with Runnable --");
        Runnable sleepy = () -> {
            System.out.println("[SleepyTask] going to sleep");
            try {
                Thread.sleep(300);
                System.out.println("[SleepyTask] woke normally");
            } catch (InterruptedException e) {
                // Rule: either propagate (not possible here) or restore interrupt flag
                System.out.println("[SleepyTask] interrupted");
                Thread.currentThread().interrupt();
            }
        };

        Thread t = new Thread(sleepy, "Sleeper");
        t.start();
        sleepSilently(60); // let it start sleeping
        t.interrupt();
        joinSilently(t);
        System.out.println("[main] interrupt demo done");
    }

    // Helpers
    private static void joinSilently(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void sleepSilently(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
