// Name: Nima Memarzadeh
// Date: 04/01/2025
// Assignment: M2: Programming Assignment

// This program tests the performance of traversing a LinkedList using two methods:
// 1. Using an Iterator
// 2. Using the get(index) method
// It compares the time taken for each method and prints the results. The program also includes error handling
// and assertions to ensure the correctness of the operations performed on the LinkedList.
// The expected results are explained in the comments at the end of the code.

import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTraversalValidator {

    // Populate LinkedList with sequential integers
    public static LinkedList<Integer> populateList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        
        LinkedList<Integer> list = new LinkedList<>();
        try {
            for (int i = 0; i < size; i++) {
                list.add(i);
            }
            return list;
        } catch (OutOfMemoryError e) {
            System.err.println("Out of memory error when creating list of size " + size);
            throw e;
        }
    }

    // Measure traversal time using Iterator
    public static long[] iteratorTraversal(LinkedList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        
        try {
            Iterator<Integer> iterator = list.iterator();
            long sum = 0;
            long startTime = System.nanoTime();
            while (iterator.hasNext()) {
                sum += iterator.next();
            }
            long endTime = System.nanoTime();
            System.out.println("Iterator sum (to avoid optimization): " + sum);
            return new long[]{endTime - startTime, sum};
        } catch (Exception e) {
            System.err.println("Error during iterator traversal: " + e.getMessage());
            throw e;
        }
    }

    // Measure traversal time using get(index)
    public static long[] getTraversal(LinkedList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        
        try {
            long sum = 0;
            long startTime = System.nanoTime();
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i);
            }
            long endTime = System.nanoTime();
            System.out.println("Get(index) sum (to avoid optimization): " + sum);
            return new long[]{endTime - startTime, sum};
        } catch (Exception e) {
            System.err.println("Error during get(index) traversal: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            int[] testSizes = {50_000, 500_000};

            for (int size : testSizes) {
                try {
                    System.out.println("\nTesting traversal performance with size: " + size);
                    LinkedList<Integer> list = populateList(size);

                    try {
                        long[] iteratorResult = iteratorTraversal(list);
                        System.out.printf("Iterator traversal time: %.3f ms\n", iteratorResult[0] / 1_000_000.0);
                    } catch (Exception e) {
                        System.err.println("Error in iterator traversal test: " + e.getMessage());
                    }

                    try {
                        long[] getIndexResult = getTraversal(list);
                        System.out.printf("get(index) traversal time: %.3f ms\n", getIndexResult[0] / 1_000_000.0);
                    } catch (Exception e) {
                        System.err.println("Error in get(index) traversal test: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error processing test with size " + size + ": " + e.getMessage());
                }
            }

            System.out.println("\nAll tests completed. Closing the application.");
        } catch (Exception e) {
            System.err.println("Fatal error in main method: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}

/*
Expected Results Explanation:
- Iterator traversal will be significantly faster for both 50,000 and 500,000 elements.
- get(index) traversal will be considerably slower, especially at larger sizes (e.g., 500,000 elements) due to the linked list's sequential access nature.
- As the size increases (500,000 vs. 50,000), get(index) traversal time increases dramatically, showing a nonlinear performance degradation (O(n^2)).
- The iterator method maintains near-linear performance (O(n)) due to direct sequential access.

Actual Test Results:

Testing traversal performance with size: 50000
Iterator sum (to avoid optimization): 1249975000
Iterator traversal time: 2.777 ms
Get(index) sum (to avoid optimization): 1249975000
get(index) traversal time: 1125.077 ms

Testing traversal performance with size: 500000
Iterator sum (to avoid optimization): 124999750000
Iterator traversal time: 8.665 ms
Get(index) sum (to avoid optimization): 124999750000
get(index) traversal time: 145264.863 ms

Explanation of Results:
- The `Iterator` traversal maintained very fast performance even as the list size increased from 50,000 to 500,000 elements, growing only from ~2.8 ms to ~8.7 ms.
- In contrast, the `get(index)` traversal showed a drastic increase in time: ~1.1 seconds for 50,000 elements and over 145 seconds for 500,000 elements.
- This confirms the O(n^2) behavior of `get(index)` traversal in a LinkedList. Each call to `get(i)` is O(i), leading to a cumulative quadratic cost.
- These results reinforce the importance of using an iterator or enhanced for-loop when traversing a LinkedList to avoid extreme performance penalties.
*/
