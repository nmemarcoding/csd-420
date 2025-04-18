// Name: Nima Memarzadeh
// Date: 04/18/2025
// Assignment: M6: Programming Assignment

import java.util.Arrays;
import java.util.Comparator;

/**
 * BubbleSort
 * 
 * Implements generic bubble sort using both Comparable and Comparator interfaces.
 * Includes test cases to verify correct functionality with various data types and orderings.
 * Step-by-step outputs are included to visualize sorting progression.
 */
public class BubbleSort {

    /**
     * Generic bubble sort using Comparable interface.
     * Prints array after each pass to illustrate sorting progress.
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        System.out.println("Starting bubble sort using Comparable:");
        for (int i = 0; i < list.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(list));
            if (!swapped) break;
        }
        System.out.println("Final sorted array: " + Arrays.toString(list));
    }

    /**
     * Generic bubble sort using Comparator interface.
     * Prints array after each pass to illustrate sorting progress.
     */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        System.out.println("Starting bubble sort using Comparator:");
        for (int i = 0; i < list.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(list));
            if (!swapped) break;
        }
        System.out.println("Final sorted array: " + Arrays.toString(list));
    }

    /**
     * Main method to run test cases demonstrating both bubble sort methods.
     */
    public static void main(String[] args) {
        // Test 1: Integer array with Comparable (ascending)
        System.out.println("Test 1: Integer sorting using Comparable (ascending):");
        Integer[] numbers1 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("Original array: " + Arrays.toString(numbers1));
        bubbleSort(numbers1);
        System.out.println("\n-------------------------------------\n");

        // Test 2: Integer array with Comparator (ascending)
        System.out.println("Test 2: Integer sorting using Comparator (ascending):");
        Integer[] numbers2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("Original array: " + Arrays.toString(numbers2));
        bubbleSort(numbers2, Comparator.naturalOrder());
        System.out.println("\n-------------------------------------\n");

        // Test 3: Integer array with Comparator (descending)
        System.out.println("Test 3: Integer sorting using Comparator (descending):");
        Integer[] numbers3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Original array: " + Arrays.toString(numbers3));
        bubbleSort(numbers3, Comparator.reverseOrder());
        System.out.println("\n-------------------------------------\n");

        // Test 4: String array with Comparable
        System.out.println("Test 4: String sorting using Comparable (alphabetical):");
        String[] fruits = {"Orange", "Apple", "Banana", "Pear", "Grape"};
        System.out.println("Original array: " + Arrays.toString(fruits));
        bubbleSort(fruits);
        System.out.println("\n-------------------------------------\n");

        // Test 5: String array with Comparator (by length)
        System.out.println("Test 5: String sorting using Comparator (by length):");
        String[] words = {"Java", "Programming", "Assignment", "Bubble", "Sort"};
        System.out.println("Original array: " + Arrays.toString(words));
        bubbleSort(words, Comparator.comparing(String::length));
        System.out.println("\n-------------------------------------\n");

        // Test 6: Empty array with Comparable
        System.out.println("Test 6: Empty array using Comparable:");
        Integer[] emptyArray = {};
        System.out.println("Original array: " + Arrays.toString(emptyArray));
        bubbleSort(emptyArray);
        System.out.println("Sorted array: " + Arrays.toString(emptyArray));
        System.out.println("\n-------------------------------------\n");

        // Test 7: Single element array with Comparable
        System.out.println("Test 7: Single element array using Comparable:");
        Integer[] singleElementArray = {42};
        System.out.println("Original array: " + Arrays.toString(singleElementArray));
        bubbleSort(singleElementArray);
        System.out.println("Sorted array: " + Arrays.toString(singleElementArray));
        System.out.println("\n-------------------------------------\n");

        // Test 8: Array with duplicate elements using Comparable
        System.out.println("Test 8: Array with duplicate elements using Comparable:");
        Integer[] duplicatesArray = {5, 3, 5, 3, 5, 3};
        System.out.println("Original array: " + Arrays.toString(duplicatesArray));
        bubbleSort(duplicatesArray);
        System.out.println("Sorted array: " + Arrays.toString(duplicatesArray));
        System.out.println("\n-------------------------------------\n");

        // Test 9: Array with negative numbers using Comparable
        System.out.println("Test 9: Array with negative numbers using Comparable:");
        Integer[] negativeNumbersArray = {-3, -1, -4, -2, 0, 2, 1};
        System.out.println("Original array: " + Arrays.toString(negativeNumbersArray));
        bubbleSort(negativeNumbersArray);
        System.out.println("Sorted array: " + Arrays.toString(negativeNumbersArray));
        System.out.println("\n-------------------------------------\n");

        // Test 10: Array with mixed case strings using Comparator
        System.out.println("Test 10: Array with mixed case strings using Comparator (case insensitive):");
        String[] mixedCaseStrings = {"apple", "Banana", "APPLE", "banana", "Orange"};
        System.out.println("Original array: " + Arrays.toString(mixedCaseStrings));
        bubbleSort(mixedCaseStrings, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Sorted array (case insensitive): " + Arrays.toString(mixedCaseStrings));
        System.out.println("\n-------------------------------------\n");

        // Test 11: Array with null values using Comparator
        System.out.println("Test 11: Array with null values using Comparator (nulls first):");
        String[] arrayWithNulls = {"apple", null, "banana", null, "cherry"};
        System.out.println("Original array: " + Arrays.toString(arrayWithNulls));
        bubbleSort(arrayWithNulls, Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("Sorted array (nulls first): " + Arrays.toString(arrayWithNulls));
        System.out.println("\n-------------------------------------\n");

        // Test 12: Array already sorted using Comparable
        System.out.println("Test 12: Array already sorted using Comparable:");
        Integer[] alreadySortedArray = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(alreadySortedArray));
        bubbleSort(alreadySortedArray);
        System.out.println("Sorted array: " + Arrays.toString(alreadySortedArray));
        System.out.println("\n-------------------------------------\n");

        // Test 13: Array sorted in reverse order using Comparable
        System.out.println("Test 13: Array sorted in reverse order using Comparable:");
        Integer[] reverseSortedArray = {5, 4, 3, 2, 1};
        System.out.println("Original array: " + Arrays.toString(reverseSortedArray));
        bubbleSort(reverseSortedArray);
        System.out.println("Sorted array: " + Arrays.toString(reverseSortedArray));
        System.out.println("\n-------------------------------------\n");
    }
}
