package com.example;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CavazosExample {

    public static void main(String[] args) {
        String fileName = "src\\main\\java\\com\\example\\commands.json";


        // Read commands from the JSON file
        JSONArray commandJSONArray = readArray(fileName);

        if (commandJSONArray.isEmpty()) {
            System.out.println("No commands found in the JSON file.");
            return;
        }

        String[] commandArray = getCommandArray(commandJSONArray);
        Scanner scanner = new Scanner(System.in);

 while (true) {
     System.out.println("\n----- General Cavazos Command Menu -----");
     System.out.println("L: List all commands");
     System.out.println("5: Issue 5 random commands");
     System.out.println("Q: Quit");
     System.out.print("Enter your choice: ");

     String choice = scanner.nextLine().toUpperCase();

     switch (choice) {
         case "L":
             System.out.println("\n----- List of all commands -----");
             print(commandArray);
             break;
         case "5":
             System.out.println("\n----- Issuing 5 random commands from General Cavazos -----");
             randomCommand(commandArray, 5);
             break;
         case "Q":
             System.out.println("Exiting...");
             scanner.close();
             return;
         default:
             System.out.println("Invalid choice. Please try again.");
     }
 }
        // Print list of all commands
        // System.out.println("----- List of all commands -----");
        // print(commandArray);

        // Issue 5 random commands
        // System.out.println("----- Issuing 5 random commands from General Cavazos
        // -----");
        // randomCommand(commandArray, 5);

    }

    // Method to read JSON array from file
    public static JSONArray readArray(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error: Could not read file: " + fileName);
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("Error: Invalid JSON format in file: " + fileName);
        }
        // Return an empty array on error
        return new JSONArray();
    }

    // Randomly issue commands from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        if (numCommand > commandArray.length) {
            // Adjust to the maximum available commands
            numCommand = commandArray.length;
        }
        System.out.printf("Number\tCommand\n");
        System.out.printf("------\t---------------\n");
        for (int i = 0; i < numCommand; i++) {
            int randIndex = rand.nextInt(commandArray.length);
            System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
        }
    }

    // Print command array
    public static void print(String[] commandArray) {
        System.out.printf("Number\tCommand\n");
        System.out.printf("------\t---------------\n");
        for (int i = 0; i < commandArray.length; i++) {
            System.out.printf("%04d\t%s\n", i, commandArray[i]);
        }
    }

    // Get array of commands
    public static String[] getCommandArray(JSONArray commandArray) {
        String[] arr = new String[commandArray.size()];

        // Get names from JSON object
        for (int i = 0; i < commandArray.size(); i++) {
            String command = commandArray.get(i).toString();
            arr[i] = command;
        }
        return arr;
    }
}
