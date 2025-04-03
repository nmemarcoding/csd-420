// Name: Nima Memarzadeh
// Date: 04/01/2025
// Assignment: M2: Programming Assignment

// // This program tests the performance of traversing a LinkedList using two methods:
// // 1. Using an Iterator
// // 2. Using the get(index) method
// // It compares the time taken for each method and prints the results. The program also includes error handling
// // and assertions to ensure the correctness of the operations performed on the LinkedList
import java.util.LinkedList;

public class LinkedListTraversalTestRunner {
    public static void main(String[] args) {
        try {
            int[] testSizes = {50_000, 500_000};
            for (int size : testSizes) {
                System.out.println("\nTesting with size: " + size);
                try {
                    var sharedList = LinkedListTraversalValidator.populateList(size);
                    try {
                        testPopulateList(sharedList, size);
                        testSumEquality(sharedList);
                    } catch (AssertionError e) {
                        System.err.println("Test assertion failed: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error during test execution: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error populating list of size " + size + ": " + e.getMessage());
                }
            }
            System.out.println("\nAll method tests executed successfully.");
        } catch (Exception e) {
            System.err.println("Fatal error in test runner: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testPopulateList(LinkedList<Integer> list, int expectedSize) {
        try {
            assert list.size() == expectedSize : "List size mismatch!";
            assert list.getFirst() == 0 : "First element should be 0!";
            assert list.getLast() == expectedSize - 1 : "Last element mismatch!";
            System.out.println("testPopulateList passed for size " + expectedSize);
        } catch (AssertionError e) {
            System.err.println("testPopulateList failed: " + e.getMessage());
            throw e;
        }
    }

    private static void testSumEquality(LinkedList<Integer> list) {
        try {
            long[] iteratorResult = LinkedListTraversalValidator.iteratorTraversal(list);
            long[] getIndexResult = LinkedListTraversalValidator.getTraversal(list);
            assert iteratorResult[1] == getIndexResult[1] :
                "Sum mismatch! Iterator sum: " + iteratorResult[1] + ", get(index) sum: " + getIndexResult[1];
            System.out.println("testSumEquality passed for size " + list.size());
        } catch (AssertionError e) {
            System.err.println("testSumEquality failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error in testSumEquality: " + e.getMessage());
            throw e;
        }
    }
}