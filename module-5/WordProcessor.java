// Name: Nima Memarzadeh
// Date: 04/010/2025
// Assignment: M5: Programming Assignment


// This code is a simple Java program that reads a text file containing words,
// counts the frequency of each word, identifies truly unique words (words that
// appear only once), and displays the results in both ascending and descending
// order. It also provides a summary of word frequencies.

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class WordProcessor {

    public static void main(String[] args) {
        try {
            String filePath = "collection_of_words.txt";

            // Read the file to get word frequencies
            Map<String, Integer> wordCounts = readFileAndProcess(filePath);

            if (wordCounts.isEmpty()) {
                System.out.println("No words found in the file.");
                return;
            }

            // Get all unique words (keys of the map)
            Set<String> allWords = wordCounts.keySet();

            // Get truly unique words (appear only once)
            Set<String> trulyUniqueWords = getTrulyUniqueWords(wordCounts);

            // Showing both interpretations of "non-duplicate":
            // 1. allWords - distinct words (each shown once regardless of frequency)
            // 2. trulyUniqueWords - words that appear exactly once in the file
            displayResults(allWords, trulyUniqueWords);

            // Display word frequencies
            displayWordCounts(wordCounts);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Reads the file and returns a map of word frequencies
    public static Map<String, Integer> readFileAndProcess(String filePath) {
        Map<String, Integer> wordCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    String word = matcher.group();
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return wordCounts;
    }

    // Returns words that appear exactly once
    public static Set<String> getTrulyUniqueWords(Map<String, Integer> wordCounts) {
        Set<String> uniqueWords = new HashSet<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueWords.add(entry.getKey());
            }
        }
        return uniqueWords;
    }

    // Displays all words and truly unique words in both ascending and descending order
    public static void displayResults(Set<String> allWords, Set<String> uniqueWords) {
        System.out.println("All non-duplicate words in ascending order:");
        displaySorted(allWords, true);

        System.out.println("\nAll non-duplicate words in descending order:");
        displaySorted(allWords, false);

        System.out.println("\nTruly unique words (appear only once) in ascending order:");
        displaySorted(uniqueWords, true);

        System.out.println("\nTruly unique words (appear only once) in descending order:");
        displaySorted(uniqueWords, false);
    }

    // Sorts and prints words in ascending or descending order
    public static void displaySorted(Set<String> words, boolean ascending) {
        List<String> sorted = new ArrayList<>(words);
        sorted.sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());
        for (String word : sorted) {
            System.out.println(word);
        }
    }

    // Displays how many times each word appears
    public static void displayWordCounts(Map<String, Integer> wordCounts) {
        System.out.println("\nWord Counts:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }
}
