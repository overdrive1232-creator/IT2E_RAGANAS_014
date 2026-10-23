package com.yourdomain.ui;

import com.yourdomain.dao.ScenarioDao;
import com.yourdomain.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao dao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            System.out.println("\n=================================================");
            System.out.println("          LAUNDRY SHOP JOB ORDER SYSTEM          ");
            System.out.println("=================================================");
            System.out.println("[1] Add New Job Order");
            System.out.println("[2] View Active Job Orders");
            System.out.println("[3] Exit System");
            System.out.println("=================================================");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter choice: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addRecordPrompt();
                case 2 -> viewRecordsTable();
                case 3 -> System.out.println("\nExiting system... Thank you!");
                default -> System.out.println("\n[ERROR] Invalid option. Please choose between 1 and 3.");
            }
        } while (choice != 3);
    }

    private void addRecordPrompt() {
        System.out.println("\n--- ADD NEW JOB ORDER ---");
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Enter Weight (KG): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid weight. Enter Weight (KG): ");
            scanner.next();
        }
        double weightKg = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Total Amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid amount. Enter Total Amount: ");
            scanner.next();
        }
        double totalAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Status (RECEIVED / WASHING / READY): ");
        String status = scanner.nextLine().trim().toUpperCase();

        ScenarioEntity newOrder = new ScenarioEntity(0, customerName, weightKg, totalAmount, status);
        dao.addRecord(newOrder);
    }

    private void viewRecordsTable() {
        List<ScenarioEntity> records = dao.getAllRecords();
        System.out.println("\n=======================================================================================");
        System.out.println("                              ACTIVE LAUNDRY ORDERS                                    ");
        System.out.println("=======================================================================================");
        if (records.isEmpty()) {
            System.out.println("No active laundry orders found in the database.");
        } else {
            System.out.println("+-----+----------------------+------------+--------------+-----------------+");
            System.out.printf("| %-3s | %-20s | %-10s | %-12s | %-15s |%n",
                    "ID", "CUSTOMER NAME", "WEIGHT_KG", "TOTAL AMOUNT", "STATUS");
            System.out.println("+-----+----------------------+------------+--------------+-----------------+");
            for (ScenarioEntity r : records) {
                System.out.printf("| %-3d | %-20s | %-10.1f | %-12.2f | %-15s |%n",
                        r.id(), r.customerName(), r.weightKg(), r.totalAmount(), r.status());
            }
            System.out.println("+-----+----------------------+------------+--------------+-----------------+");
        }
        System.out.println("Total Records Displayed: " + records.size());
        System.out.println("=======================================================================================");
    }
}