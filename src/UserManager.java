import javax.swing.*;
import java.util.HashMap;

public class UserManager {
    private static final HashMap<String, String> users = new HashMap<>(); // Stores username and password

    public static void register() {
        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");
        String username = JOptionPane.showInputDialog("Create a Username:");
        String password = JOptionPane.showInputDialog("Create a Password:");

        // Ask user if they are sure about the registration details
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to register with this username: " + username + "?");
        if (confirm == JOptionPane.YES_OPTION) {
            // Register the user
            users.put(username, password);
            JOptionPane.showMessageDialog(null, "Registration successful for " + firstName + " " + lastName + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Registration cancelled.");
        }
    }

    public static boolean login() {
        String username = JOptionPane.showInputDialog("Enter your Username:");
        String password = JOptionPane.showInputDialog("Enter your Password:");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful. Welcome " + username + "!");
            return true; // Login successful
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            return false; // Login failed
        }
    }
}
