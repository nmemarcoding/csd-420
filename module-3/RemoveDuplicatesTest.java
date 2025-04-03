// Name: Nima Memarzadeh
// Date: 04/03/2025
// Assignment: M3: Programming Assignment

// This program generates an ArrayList with 50 random values from 1 to 20,
// removes duplicates from the list, and prints the original and modified 
// lists along with the number of duplicates removed.
// The program uses a generic method to remove duplicates from the ArrayList.

import java.util.ArrayList;
import java.util.Random;


public class RemoveDuplicatesTest {


    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();
        
        // Add each element to the result list only if it's not already there
        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Create an ArrayList with 50 random values from 1 to 20
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();
        
        System.out.println("Generating an ArrayList with 50 random values from 1 to 20...");
        for (int i = 0; i < 50; i++) {
            int randomValue = random.nextInt(20) + 1; // Random value between 1 and 20
            originalList.add(randomValue);
        }
        
        // Print the original list
        System.out.println("\nOriginal ArrayList:");
        System.out.println(originalList);
        System.out.println("Size: " + originalList.size());
        
        // Remove duplicates and get the result
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);
        
        // Print the result
        System.out.println("\nArrayList after removing duplicates:");
        System.out.println(noDuplicatesList);
        System.out.println("Size: " + noDuplicatesList.size());
        
        // Count how many duplicates were removed
        int duplicatesRemoved = originalList.size() - noDuplicatesList.size();
        System.out.println("\nNumber of duplicates removed: " + duplicatesRemoved);
    }
}