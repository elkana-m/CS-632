/**
 * Task class representing a data processing task.
 * Contains task ID, data to process, and optional priority.
 */
public class Task {
    private int taskId;
    private String data;
    private int priority;

    /**
     * Constructor with task ID and data.
     * @param taskId Unique identifier for the task
     * @param data Data to be processed
     */
    public Task(int taskId, String data) {
        this.taskId = taskId;
        this.data = data;
        this.priority = 0; // Default priority
    }

    /**
     * Constructor with task ID, data, and priority.
     * @param taskId Unique identifier for the task
     * @param data Data to be processed
     * @param priority Priority level of the task
     */
    public Task(int taskId, String data, int priority) {
        this.taskId = taskId;
        this.data = data;
        this.priority = priority;
    }

    /**
     * Get the task ID.
     * @return Task ID
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Get the task data.
     * @return Task data
     */
    public String getData() {
        return data;
    }

    /**
     * Get the task priority.
     * @return Task priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * String representation of the task for logging.
     * @return String representation
     */
    @Override
    public String toString() {
        return "Task{id=" + taskId + ", data='" + data + "', priority=" + priority + "}";
    }
}

