public class Task {

    private String text;
    private TaskStatus status;


    public static Task createTask(String text) {
        Task t = new Task();
        t.text = text;
        t.status = TaskStatus.ACTIVE;
        return t;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return text + " (Статус: " + status.getTitle() + ")";
    }
}

enum TaskStatus {
    ACTIVE("Активно"),
    DONE("Завершено");

    private final String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
