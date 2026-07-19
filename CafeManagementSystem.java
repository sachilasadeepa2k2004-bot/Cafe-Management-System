import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Table {
    int tableNumber;
    boolean isOccupied;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
    }
}

class Order {
    String orderId;
    String customerName;
    String items;
    int tableNumber; // අලුතින් එකතු කළා

    public Order(String orderId, String customerName, String items, int tableNumber) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "[" + orderId + "] " + customerName + " (Table " + tableNumber + ") ordered: " + items;
    }
}

public class CafeManagementSystem {

    private static ArrayList<Table> tables = new ArrayList<>();
    private static Queue<Order> orderQueue = new LinkedList<>();
    private static Stack<Order> recentOrdersStack = new Stack<>();
    private static int orderCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 1; i <= 5; i++) {
            tables.add(new Table(i));
        }

        boolean running = true;
        System.out.println("=============================================");
        System.out.println("     Smart Cafe Order & Table Management     ");
        System.out.println("=============================================");

        while (running) {
            System.out.println("\n[1] Check Table Availability");
            System.out.println("[2] Take New Order");
            System.out.println("[3] Prepare/Serve Next Order");
            System.out.println("[4] Undo Last Order (Cashier Mistake)");
            System.out.println("[5] Exit");
            System.out.print("Select an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    checkTables();
                    break;
                case "2":
                    takeOrder(scanner);
                    break;
                case "3":
                    serveNextOrder();
                    break;
                case "4":
                    undoLastOrder();
                    break;
                case "5":
                    running = false;
                    System.out.println("System shutting down...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void checkTables() {
        System.out.println("\n--- Table Status ---");
        for (Table t : tables) {
            String status = t.isOccupied ? "[Occupied]" : "[Available]";
            System.out.println("Table " + t.tableNumber + " : " + status);
        }
    }

    private static void takeOrder(Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Items (e.g., 2 Lattes): ");
        String items = scanner.nextLine();
        
        // Table Number එක අහනවා
        System.out.print("Enter Table Number (1-5): ");
        int tableNo = Integer.parseInt(scanner.nextLine());

        // Table එක Occupied කරනවා
        for (Table t : tables) {
            if (t.tableNumber == tableNo) {
                t.isOccupied = true;
                break;
            }
        }

        Order newOrder = new Order("ORD-" + orderCounter++, name, items, tableNo);
        orderQueue.add(newOrder);
        recentOrdersStack.push(newOrder);

        System.out.println("\nSuccess: Order placed -> " + newOrder);
    }

    private static void serveNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("\nKitchen is clear! No pending orders.");
            return;
        }

        Order orderToServe = orderQueue.poll();
        System.out.println("\n[KITCHEN] Preparing and Serving: " + orderToServe);
        
        // Order එක සර්ව් කළාම Table එක ආපහු Available කරනවා
        for (Table t : tables) {
            if (t.tableNumber == orderToServe.tableNumber) {
                t.isOccupied = false;
                System.out.println("Table " + t.tableNumber + " is now free.");
                break;
            }
        }
    }

    private static void undoLastOrder() {
        if (recentOrdersStack.isEmpty()) {
            System.out.println("\nNo recent orders to cancel.");
            return;
        }

        Order mistake = recentOrdersStack.pop();
        orderQueue.remove(mistake);
        
        // Undo කළාම Table එකත් ආපහු Available කරනවා
        for (Table t : tables) {
            if (t.tableNumber == mistake.tableNumber) {
                t.isOccupied = false;
                break;
            }
        }
        
        System.out.println("\nCancelled Mistaken Order: " + mistake.orderId + " for " + mistake.customerName);
    }
}