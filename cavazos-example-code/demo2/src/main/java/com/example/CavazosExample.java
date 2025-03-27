package com.example;

import org.json.simple.JSONArray;
import java.util.Stack;
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
        CommandStack commandStack = new CavazosExample().new CommandStack();

 while (true) {
     line(args);
     System.out.println("----- General Cavazos Command Menu -----");
     line(args);
     System.out.println("L: List all commands");
     System.out.println("I: Issue a random command");
     System.out.println("R: Reissue Previous command");
     System.out.println("U: Undo Previous command");
     System.out.println("Q: Quit");
     line(args);
     System.out.print("Enter your choice: ");
     String choice = scanner.nextLine().toLowerCase();

     switch (choice) {
         case "l":
             System.out.println("\n----- List of all commands -----");
             // Print each command with a number
             for (int i = 0; i < commandArray.length; i++) {
                 String listFormat = String.format("%02d", i + 1);
                 System.out.println(listFormat + " " + commandArray[i]);
             }
             break;
         case "i":
             line(args);
             String randomCommand = commandArray[new Random().nextInt(commandArray.length)];
             // Add command to the stack
             commandStack.executeCommand(randomCommand);
             break;
         case "u":
             line(args);
             commandStack.undo();
             break;
         case "r":
             line(args);
             commandStack.redo();
             break;
         case "q":
             System.out.println("Exiting...");
             scanner.close();
             return;

         default:
             System.out.println("Invalid choice. Please try again.");
             break;

     }
 }

    }

    // Formatting line func.
    public static void line(String[] args) {
        System.out.println("\n-----------------------------------------\n");

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

    public class CommandStack {

        private Stack<String> commands = new Stack<>();
        String uiFlair = "[Command issued]  ";

        public void executeCommand(String command) {
            commands.push(command);
            System.out.println(uiFlair + command);
        }

        public void redo() {
            if (!commands.isEmpty()) {
                String command = commands.peek();
                executeCommand(command);
            } else {
                System.out.println("No commands to reissue.");
            }
        }

        public void undo() {
            if (!commands.isEmpty()) {
                String command = commands.pop();
                System.out.println(uiFlair + command + " *Order Cancelled*");
            } else {
                System.out.println("No commands to revoke.");
            }
        }

    }
}
