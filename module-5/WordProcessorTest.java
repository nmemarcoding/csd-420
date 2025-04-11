// Name: Nima Memarzadeh
// Date: 04/010/2025
// Assignment: M5: Programming Assignment


// This code is a test suite for the WordProcessor class, which processes
//  a text file to count word frequencies and identify unique words.

import java.util.*;
import java.util.Map.Entry;

public class WordProcessorTest {

    public static void main(String[] args) {
        String testFilePath = "collection_of_words.txt";

        System.out.println("===== Starting WordProcessor Manual Tests =====\n");

        try {
            // Test: Read and process file
            logTest("readFileAndProcess");
            Map<String, Integer> wordCounts = WordProcessor.readFileAndProcess(testFilePath);

            if (wordCounts.isEmpty()) {
                warn("No words were found in the file.");
            } else {
                pass("Words successfully processed from file.");
                printKeySummary(wordCounts.keySet());
            }

            // Test: Print all word frequencies
            logTest("Word Frequencies");
            printWordFrequencies(wordCounts);

            // Test: Get words that appear only once
            logTest("getTrulyUniqueWords");
            Set<String> trulyUnique = WordProcessor.getTrulyUniqueWords(wordCounts);

            if (trulyUnique.isEmpty()) {
                warn("No truly unique words (frequency == 1) found.");
            } else {
                pass("Truly unique words successfully extracted.");
                printKeySummary(trulyUnique);
            }

            // Test: Display sorted output
            logTest("displayResults");
            WordProcessor.displayResults(wordCounts.keySet(), trulyUnique);

            // Test: Display word counts again using provided method
            logTest("displayWordCounts");
            WordProcessor.displayWordCounts(wordCounts);

            System.out.println("\n===== All tests completed successfully. =====");

        } catch (Exception e) {
            System.err.println("\nERROR: An unexpected exception occurred.");
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Prints test section header
    private static void logTest(String testName) {
        System.out.println("\n--- TEST: " + testName + " ---");
    }

    // Marks test as passed
    private static void pass(String message) {
        System.out.println("PASS: " + message);
    }

    // Prints a warning message
    private static void warn(String message) {
        System.out.println("WARNING: " + message);
    }

    // Prints size and sample of a key set
    private static void printKeySummary(Set<String> keys) {
        System.out.println("Total entries: " + keys.size());
        System.out.println("Sample: " + getSample(keys, 5));
    }

    // Returns first N items from a set
    private static List<String> getSample(Set<String> input, int count) {
        List<String> list = new ArrayList<>(input);
        return list.subList(0, Math.min(count, list.size()));
    }

    // Prints each word and its frequency
    private static void printWordFrequencies(Map<String, Integer> wordCounts) {
        if (wordCounts.isEmpty()) {
            warn("No word frequencies to display.");
            return;
        }
        for (Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue());
        }
    }
}
