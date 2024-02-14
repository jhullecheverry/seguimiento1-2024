package cue.edu.co;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

class Toy {
    String name;
    char type;
    double price;
    int quantity;

    public Toy(String name, char type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
}

public class ToyStore {
    private static List<Toy> inventory = new ArrayList<>();

    public static void addToy() {
        String name = JOptionPane.showInputDialog("Enter toy name:");
        char type = JOptionPane.showInputDialog("Enter toy type (O->Female, 1->Male, 2->Unisex):").charAt(0);
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter toy price:"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity in stock:"));
        inventory.add(new Toy(name, type, price, quantity));
    }

    public static void informQuantityByType() {
        Map<Character, Integer> quantityByType = new HashMap<>();
        for (Toy toy : inventory) {
            quantityByType.put(toy.type, quantityByType.getOrDefault(toy.type, 0) + toy.quantity);
        }
        StringBuilder message = new StringBuilder("Quantity by toy type:\n");
        for (Map.Entry<Character, Integer> entry : quantityByType.entrySet()) {
            message.append("Type ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public static void informTotalQuantity() {
        int totalQuantity = 0;
        for (Toy toy : inventory) {
            totalQuantity += toy.quantity;
        }
        JOptionPane.showMessageDialog(null, "Total quantity of toys: " + totalQuantity);
    }

    public static void informTotalValue() {
        double totalValue = 0;
        for (Toy toy : inventory) {
            totalValue += toy.price * toy.quantity;
        }
        JOptionPane.showMessageDialog(null, "Total value of all toys in the store: $" + totalValue);
    }

    public static void decreaseStock() {
        String nameToDecrease = JOptionPane.showInputDialog("Enter toy name to decrease stock:");
        int quantityToDecrease = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to decrease:"));
        for (Toy toy : inventory) {
            if (toy.name.equalsIgnoreCase(nameToDecrease)) {
                toy.quantity -= quantityToDecrease;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Toy not found.");
    }

    public static void increaseStock() {
        String nameToIncrease = JOptionPane.showInputDialog("Enter toy name to increase stock:");
        int quantityToIncrease = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to increase:"));
        for (Toy toy : inventory) {
            if (toy.name.equalsIgnoreCase(nameToIncrease)) {
                toy.quantity += quantityToIncrease;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Toy not found.");
    }

    public static void informTypeWithMostToys() {
        Map<Character, Integer> quantityByType = new HashMap<>();
        for (Toy toy : inventory) {
            quantityByType.put(toy.type, quantityByType.getOrDefault(toy.type, 0) + toy.quantity);
        }
        char typeMostToys = Collections.max(quantityByType.entrySet(), Map.Entry.comparingByValue()).getKey();
        JOptionPane.showMessageDialog(null, "Type with most toys: " + typeMostToys);
    }

    public static void informTypeWithLeastToys() {
        Map<Character, Integer> quantityByType = new HashMap<>();
        for (Toy toy : inventory) {
            quantityByType.put(toy.type, quantityByType.getOrDefault(toy.type, 0) + toy.quantity);
        }
        char typeLeastToys = Collections.min(quantityByType.entrySet(), Map.Entry.comparingByValue()).getKey();
        JOptionPane.showMessageDialog(null, "Type with least toys: " + typeLeastToys);
    }

    public static void getToysWithValueGreaterThan() {
        double value = Double.parseDouble(JOptionPane.showInputDialog("Enter value:"));
        StringBuilder message = new StringBuilder("Toys with value greater than $" + value + ":\n");
        for (Toy toy : inventory) {
            if (toy.price * toy.quantity > value) {
                message.append(toy.name).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public static void sortStockByType() {
        Collections.sort(inventory, Comparator.comparingInt(toy -> toy.type));
        StringBuilder message = new StringBuilder("Inventory sorted from least to most by quantity of stock by type:\n");
        for (Toy toy : inventory) {
            message.append(toy.name).append(" - Type: ").append(toy.type).append(" - Quantity: ").append(toy.quantity).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Add a new toy", "Inform quantity of toys by type", "Inform total quantity of toys", "Inform total value of toys",
                    "Decrease stock of a toy", "Increase stock of a toy", "Inform type with most toys", "Inform type with least toys",
                    "Get toys with value greater than", "Sort stock by type", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Menu", "Toy Store Management", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addToy();
                    break;
                case 1:
                    informQuantityByType();
                    break;
                case 2:
                    informTotalQuantity();
                    break;
                case 3:
                    informTotalValue();
                    break;
                case 4:
                    decreaseStock();
                    break;
                case 5:
                    increaseStock();
                    break;
                case 6:
                    informTypeWithMostToys();
                    break;
                case 7:
                    informTypeWithLeastToys();
                    break;
                case 8:
                    getToysWithValueGreaterThan();
                    break;
                case 9:
                    sortStockByType();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid option.");
            }
        }
    }
}
