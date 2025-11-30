/**
 * Worker thread that processes tasks from the shared queue.
 * Retrieves tasks, processes them with simulated delay, and stores results.
 */
public class WorkerThread implements Runnable {
    private int workerId;
    private SharedTaskQueue taskQueue;
    private ResultsCollector resultsCollector;

    /**
     * Constructor for worker thread.
     * @param workerId Unique identifier for this worker
     * @param taskQueue Shared queue to retrieve tasks from
     * @param resultsCollector Shared collector to store results in
     */
    public WorkerThread(int workerId, SharedTaskQueue taskQueue, ResultsCollector resultsCollector) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
        this.resultsCollector = resultsCollector;
    }

    /**
     * Main run method that processes tasks.
     * Continuously retrieves tasks from queue, processes them, and stores results.
     */
    @Override
    public void run() {
        System.out.println("[Worker " + workerId + "] Thread started.");
        
        try {
            while (true) {
                // Retrieve task from queue
                Task task = taskQueue.getTask();
                
                // If no task available and queue is closed, exit
                if (task == null) {
                    if (taskQueue.isClosed() && taskQueue.isEmpty()) {
                        System.out.println("[Worker " + workerId + "] No more tasks available. Queue is closed and empty. Exiting.");
                        break;
                    }
                    // Queue is empty but not closed, wait a bit and retry
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("[Worker " + workerId + "] Interrupted while waiting for tasks: " + e.getMessage());
                        break;
                    }
                    continue;
                }

                // Process the task
                try {
                    System.out.println("[Worker " + workerId + "] Processing task: " + task.getTaskId());
                    String processedData = processTask(task);
                    
                    // Create result
                    long timestamp = System.currentTimeMillis();
                    Result result = new Result(task.getTaskId(), processedData, workerId, timestamp);
                    
                    // Store result
                    resultsCollector.addResult(result);
                    
                    System.out.println("[Worker " + workerId + "] Completed task: " + task.getTaskId());
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("[Worker " + workerId + "] Interrupted while processing task " + task.getTaskId() + ": " + e.getMessage());
                    // Put task back in queue if interrupted during processing
                    try {
                        taskQueue.addTask(task);
                    } catch (IllegalStateException ex) {
                        System.err.println("[Worker " + workerId + "] Could not return task to queue: " + ex.getMessage());
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("[Worker " + workerId + "] Error processing task " + task.getTaskId() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("[Worker " + workerId + "] Fatal error in worker thread: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("[Worker " + workerId + "] Thread completed.");
        }
    }

    /**
     * Process a task by simulating computational work.
     * @param task Task to process
     * @return Processed data string
     * @throws InterruptedException if thread is interrupted during processing
     */
    private String processTask(Task task) throws InterruptedException {
        // Simulate processing delay (random between 500ms and 2000ms)
        int processingTime = 500 + (int)(Math.random() * 1500);
        Thread.sleep(processingTime);
        
        // Simulate data processing (e.g., transform the data)
        String processedData = "[PROCESSED] " + task.getData().toUpperCase() + " (processed in " + processingTime + "ms)";
        
        return processedData;
    }

    /**
     * Get the worker ID.
     * @return Worker ID
     */
    public int getWorkerId() {
        return workerId;
    }
}

