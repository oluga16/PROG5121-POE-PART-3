import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Task Manager 3.0!");

        boolean running = true;

        while (running) {
            String menu = "Main Menu:\n1. Register\n2. Login\n3. Exit";
            String choice = JOptionPane.showInputDialog(menu);

            if (choice == null) break; // Handles case when the user cancels the input.

            switch (choice) {
                case "1" -> UserManager.register();
                case "2" -> {
                    if (UserManager.login()) { 
                        // If login is successful, take the user to the activities section.
                        loggedInMenu();
                    }
                }
                case "3" -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye! Thanks for using Task Manager 3.0.");
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    private static void loggedInMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            String menu = "Logged-In Menu:\n1. Add Task\n2. Manage Tasks\n3. View Task Statistics\n4. Logout";
            String choice = JOptionPane.showInputDialog(menu);

            if (choice == null) break;

            switch (choice) {
                case "1" -> TaskManager.addTask();
                case "2" -> TaskManager.manageTasks();
                case "3" -> TaskManager.viewStatistics();
                case "4" -> {
                    loggedIn = false;
                    JOptionPane.showMessageDialog(null, "Logged out successfully.");
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }
}
