public class Task {
    private final String title;
    private final String description;
    private final String category;
    private final String priority;
    private final String passcode;

    public Task(String title, String description, String category, String priority, String passcode) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }

    public String getCategory() {
        return category;
    }

    public String getBasicInfo() {
        return "Title: " + title + ", Category: " + category + ", Priority: " + priority;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description +
               "\nCategory: " + category + "\nPriority: " + priority;
    }
}
