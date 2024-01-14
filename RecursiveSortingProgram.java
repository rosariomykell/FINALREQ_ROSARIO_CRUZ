/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recursivesortingprogram;

import java.util.Arrays;
import java.util.Scanner;

public class RecursiveSortingProgram {

    public static void main(String[] args) {
        runProgram();
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter at least 5 string inputs:");

        
        String[] inputs = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Input " + (i + 1) + ": ");
            inputs[i] = getUserInput(scanner);
        }

        
        System.out.println("Invalid inputs: " + Arrays.toString(findInvalidInputs(inputs)));

        
        sortStrings(inputs, 0, inputs.length - 1);

        
        System.out.println("Sorted Strings (Ascending Order): " + Arrays.toString(inputs));

        
        System.out.print("Do you want to run again? (yes/no): ");
        String runAgain = getUserInput(scanner);

        if ("yes".equalsIgnoreCase(runAgain)) {
            runProgram(); // Recursive call to run the program again
        } else {
            System.out.println("Exiting the program.");
        }
    }

    private static String getUserInput(Scanner scanner) {
        String userInput = scanner.nextLine().trim();

        
        if (userInput.matches(".*\\d.*") || !userInput.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter a valid string.");
            return getUserInput(scanner); // Recursive call for invalid input
        }

        return userInput;
    }

    private static String[] findInvalidInputs(String[] inputs) {
        return Arrays.stream(inputs)
                .filter(s -> s.matches(".*\\d.*") || !s.matches("[a-zA-Z]+"))
                .toArray(String[]::new);
    }

    private static void sortStrings(String[] array, int start, int end) {
        if (start < end) {
            // Recursive call for the substring
            sortStrings(array, start, end - 1);

            // Perform Insertion Sort on the last character of the string
            String key = array[end];
            int i = end - 1;

            while (i >= 0 && compareStrings(key, array[i]) < 0) {
                array[i + 1] = array[i];
                i--;
            }

            array[i + 1] = key;
        }
    }

    private static int compareStrings(String s1, String s2) {
        // Compare characters from right to left
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 1; i <= minLength; i++) {
            char c1 = s1.charAt(s1.length() - i);
            char c2 = s2.charAt(s2.length() - i);

            if (c1 < c2) {
                return -1;
            } else if (c1 > c2) {
                return 1;
            }
        }

        
        return Integer.compare(s1.length(), s2.length());
    }
}

