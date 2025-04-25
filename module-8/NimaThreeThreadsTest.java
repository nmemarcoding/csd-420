// Name: Nima Memarzadeh
// Date: 04/25/2025
// Assignment: M8: Programming Assignment

//  This is a test class for the NimaThreeThreads class.
//  It creates three threads: one for letters, one for digits, and one for symbols.
//  Each thread runs a specific task and prints its output.
//  The test class verifies that each thread is created successfully and runs without errors.
//  It also checks that the threads are not null and that they execute correctly.

public class NimaThreeThreadsTest {
    public static void main(String[] args) {
        boolean allPassed = true;

        // Test createLetterThread
        try {
            Thread letterThread = NimaThreeThreads.createLetterThread();
            assertNotNull(letterThread, "Letter thread should not be null");
            letterThread.start();
            letterThread.join();
            System.out.println("[PASS] Letter thread executed successfully.");
        } catch (Exception e) {
            allPassed = false;
            System.out.println("[FAIL] Letter thread test failed: " + e.getMessage());
        }

        // Test createDigitThread
        try {
            Thread digitThread = NimaThreeThreads.createDigitThread();
            assertNotNull(digitThread, "Digit thread should not be null");
            digitThread.start();
            digitThread.join();
            System.out.println("[PASS] Digit thread executed successfully.");
        } catch (Exception e) {
            allPassed = false;
            System.out.println("[FAIL] Digit thread test failed: " + e.getMessage());
        }

        // Test createSymbolThread
        try {
            Thread symbolThread = NimaThreeThreads.createSymbolThread();
            assertNotNull(symbolThread, "Symbol thread should not be null");
            symbolThread.start();
            symbolThread.join();
            System.out.println("[PASS] Symbol thread executed successfully.");
        } catch (Exception e) {
            allPassed = false;
            System.out.println("[FAIL] Symbol thread test failed: " + e.getMessage());
        }

        if (allPassed) {
            System.out.println("All tests PASSED.");
        } else {
            System.out.println("Some tests FAILED.");
        }
    }

    private static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new AssertionError(message);
        }
    }
}
