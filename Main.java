package app;
 
import exception.DuplicateRecordException;
import java.util.Scanner;
import model.ItemRecord;
import service.RecordManager;
 
public class Main {
 
    private static final Scanner input = new Scanner(System.in);
    private static final RecordManager manager = new RecordManager();
 
    public static void main(String[] args) {
        boolean running = true;
 
        System.out.println("=== Exception-Safe Record Entry ===");
 
        while (running) {
            showMenu();
            String choice = input.nextLine().trim();
 
            switch (choice) {
                case "1":
                    addNewRecord();
                    break;
                case "2":
                    manager.displayRecords();
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1, 2, or 3.");
            }
        }
 
        input.close();
    }
 
    private static void showMenu() {
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Add a record");
        System.out.println("2. View all records");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
 
    private static void addNewRecord() {
        boolean finished = false;
 
        while (!finished) {
            System.out.println();
            System.out.print("Record ID: ");
            String id = input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Quantity: ");
            String quantityText = input.nextLine();
 
            try {
                int quantity = Integer.parseInt(quantityText.trim());
                ItemRecord record = new ItemRecord(id, name, quantity);
                manager.addRecord(record);
 
                System.out.println("Record added successfully.");
                System.out.println("Added: " + record.getRecordId()
                        + " | " + record.getName()
                        + " | Quantity: " + record.getQuantity());
                finished = true;
 
            } catch (NumberFormatException e) {
                // NumberFormatException must come BEFORE
                // IllegalArgumentException because it is a more
                // specific (child) exception.
                System.out.println("Unable to add record: quantity must be "
                        + "a whole number such as 5. You entered \""
                        + quantityText + "\".");
 
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to add record: " + e.getMessage());
 
            } catch (DuplicateRecordException e) {
                System.out.println("Unable to add record: " + e.getMessage());
            }
 
            if (!finished) {
                System.out.println("The application remains ready "
                        + "for the next operation.");
                finished = !askToTryAgain();
            }
        }
    }
 
    private static boolean askToTryAgain() {
        while (true) {
            System.out.print("Try again? (Y/N): ");
            String answer = input.nextLine().trim();
 
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("N")) {
                System.out.println(
                        "Returning to the menu. No record was added.");
                return false;
            }
            System.out.println("Please type Y or N.");
        }
    }
}
