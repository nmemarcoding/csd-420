// Name: Nima Memarzadeh
// Date: 04/25/2025
// Assignment: M8: Programming Assignment

// This program creates three threads that generate random letters, digits, and symbols.
// Each thread generates 10,000 characters and prints them in a single line format.
// The program ensures that all threads finish execution before exiting.

import java.util.Random;

public class NimaThreeThreads {
    public static final int COUNT = 10000;

    public static void main(String[] args) {
        Thread letterThread = createLetterThread();
        Thread digitThread = createDigitThread();
        Thread symbolThread = createSymbolThread();

        letterThread.start();
        digitThread.start();
        symbolThread.start();

        try {
            letterThread.join();
            digitThread.join();
            symbolThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll threads have finished execution.");
    }

    // Modularized thread creation methods
    public static Thread createLetterThread() {
        return new Thread(() -> {
            StringBuilder output = new StringBuilder();
            Random random = new Random();
            output.append("\n=== Letter Thread Output ===\n");
            for (int i = 0; i < COUNT; i++) {
                char letter = (char) ('a' + random.nextInt(26));
                output.append(letter);
            }
            System.out.println(output);
        });
    }

    public static Thread createDigitThread() {
        return new Thread(() -> {
            StringBuilder output = new StringBuilder();
            Random random = new Random();
            output.append("\n=== Digit Thread Output ===\n");
            for (int i = 0; i < COUNT; i++) {
                char digit = (char) ('0' + random.nextInt(10));
                output.append(digit);
            }
            System.out.println(output);
        });
    }

    public static Thread createSymbolThread() {
        return new Thread(() -> {
            StringBuilder output = new StringBuilder();
            char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};
            Random random = new Random();
            output.append("\n=== Symbol Thread Output ===\n");
            for (int i = 0; i < COUNT; i++) {
                char symbol = symbols[random.nextInt(symbols.length)];
                output.append(symbol);
            }
            System.out.println(output);
        });
    }

   
}
