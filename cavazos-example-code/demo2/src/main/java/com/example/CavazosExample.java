package com.example;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class CavazosExample {

    public static void main(String[] args) {
        String fileName = "src\\main\\java\\com\\example\\commands.json";

        System.out.println("Reading from: " + fileName); // Print the file path

        // Read commands from the JSON file
        JSONArray commandJSONArray = readArray(fileName);

        if (commandJSONArray.isEmpty()) {
            System.out.println("No commands found in the JSON file.");
            return;
        }

        String[] commandArray = getCommandArray(commandJSONArray);

        // Print list of all commands
        System.out.println("----- List of all commands -----");
        print(commandArray);

        // Issue 5 random commands
        System.out.println("----- Issuing 5 random commands from General Cavazos -----");
        randomCommand(commandArray, 5);
                        //e
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
        return new JSONArray(); // Return an empty array on error
    }

    // Randomly issue commands from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        if (numCommand > commandArray.length) {
            numCommand = commandArray.length; // Adjust to the maximum available commands
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
