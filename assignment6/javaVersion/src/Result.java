/**
 * Result class representing the processed result of a task.
 * Contains task ID, processed data, worker ID, and timestamp.
 */
public class Result {
    private int taskId;
    private String processedData;
    private int workerId;
    private long timestamp;

    /**
     * Constructor for a result.
     * @param taskId ID of the original task
     * @param processedData The processed data result
     * @param workerId ID of the worker thread that processed the task
     * @param timestamp Timestamp when the result was created
     */
    public Result(int taskId, String processedData, int workerId, long timestamp) {
        this.taskId = taskId;
        this.processedData = processedData;
        this.workerId = workerId;
        this.timestamp = timestamp;
    }

    /**
     * Get the task ID.
     * @return Task ID
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Get the processed data.
     * @return Processed data
     */
    public String getProcessedData() {
        return processedData;
    }

    /**
     * Get the worker ID.
     * @return Worker ID
     */
    public int getWorkerId() {
        return workerId;
    }

    /**
     * Get the timestamp.
     * @return Timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * String representation of the result for logging.
     * @return String representation
     */
    @Override
    public String toString() {
        return "Result{taskId=" + taskId + ", processedData='" + processedData + 
               "', workerId=" + workerId + ", timestamp=" + timestamp + "}";
    }
}

