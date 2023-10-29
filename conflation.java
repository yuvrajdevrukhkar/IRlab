package com.company;
import java.io.*;
import java.util.*;

public class conflation {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            List<String> text = new ArrayList<>();
            readTextFile("Input.txt", text);
            int choice;

            do {
                System.out.println("1. Display the file");
                System.out.println("2. Remove Punctuation and Stop Words");
                System.out.println("3. Suffix Stripping");
                System.out.println("4. Count Frequency");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        displayText(text);
                        break;
                    case 2:
                        removePunctuationAndStopWords(text);
                        break;
                    case 3:
                        suffixStripping(text);
                        break;
                    case 4:
                        countFrequency(text);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } while (choice != 5);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }
    }

    private static void readTextFile(String filename, List<String> text) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                text.add(scanner.next());
            }
        }
    }

    private static void displayText(List<String> text) {
        for (String word : text) {
            System.out.print(word + " ");
        }
        System.out.println();
    }

    private static void removePunctuationAndStopWords(List<String> text) {
        List<String> stopWords = Arrays.asList("the", "is", "and", "of", "are", "for", "in");
        List<String> result = new ArrayList<>();

        for (String word : text) {
            String cleanedWord = word.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
            if (!stopWords.contains(cleanedWord)) {
                result.add(cleanedWord);
            }
        }

        displayText(result);
    }

    private static void suffixStripping(List<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String word = text.get(i);
            if (word.endsWith("ing") || word.endsWith("ed") || word.endsWith("s")) {
                text.set(i, word.substring(0, word.length() - 3)); // Remove the last 3 characters
            }
        }
    }


    private static void countFrequency(List<String> text) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : text) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        wordCount.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
