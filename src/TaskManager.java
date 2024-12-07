import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private static final List<Task> tasks = new ArrayList<>();
    private static final int MAX_ATTEMPTS = 3;

    public static void addTask() {
        JOptionPane.showMessageDialog(null, "--- Add Task ---");

        String title = JOptionPane.showInputDialog("Enter Task Title:");
        String description = JOptionPane.showInputDialog("Enter Task Description:");

        String[] categories = {"Work", "Personal", "Urgent"};
        String category = (String) JOptionPane.showInputDialog(null, "Choose Category:", "Category", JOptionPane.QUESTION_MESSAGE,
                null, categories, categories[0]);

        String[] priorities = {"Low", "Medium", "High"};
        String priority = (String) JOptionPane.showInputDialog(null, "Set Priority:", "Priority", JOptionPane.QUESTION_MESSAGE,
                null, priorities, priorities[0]);

        String passcode = JOptionPane.showInputDialog("Set a 4-digit passcode for this task:");

        Task task = new Task(title, description, category, priority, passcode);
        tasks.add(task);
        JOptionPane.showMessageDialog(null, "Task added successfully!");
    }

    public static void manageTasks() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        StringBuilder taskList = new StringBuilder("Choose a task to manage:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i + 1).append(". ").append(tasks.get(i).getBasicInfo()).append("\n");
        }

        String taskChoice = JOptionPane.showInputDialog(taskList.toString() + "Enter task number to manage (0 to cancel):");

        int choice = Integer.parseInt(taskChoice);
        if (choice > 0 && choice <= tasks.size()) {
            Task selectedTask = tasks.get(choice - 1);

            if (validatePasscode(selectedTask)) {
                String[] taskOptions = {"View Task Details", "Delete Task"};
                int action = JOptionPane.showOptionDialog(null, "Choose an action", "Manage Task", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, taskOptions, taskOptions[0]);

                switch (action) {
                    case 0 -> JOptionPane.showMessageDialog(null, selectedTask);
                    case 1 -> {
                        tasks.remove(selectedTask);
                        JOptionPane.showMessageDialog(null, "Task deleted successfully!");
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid option. Returning to menu.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Too many failed attempts. Returning to menu.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Action canceled.");
        }
    }

    private static boolean validatePasscode(Task task) {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            String inputPasscode = JOptionPane.showInputDialog("Enter the passcode for this task:");

            if (task.getPasscode().equals(inputPasscode)) {
                return true;
            } else {
                attempts++;
                JOptionPane.showMessageDialog(null, "Incorrect passcode. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }

        return false;
    }

    public static void viewStatistics() {
        StringBuilder stats = new StringBuilder("\n--- Task Statistics ---\n");
        Map<String, Long> categoryCount = tasks.stream()
                .collect(Collectors.groupingBy(Task::getCategory, Collectors.counting()));

        categoryCount.forEach((category, count) -> stats.append("Category: ").append(category).append(", Tasks: ").append(count).append("\n"));

        JOptionPane.showMessageDialog(null, stats.toString());
    }
}
