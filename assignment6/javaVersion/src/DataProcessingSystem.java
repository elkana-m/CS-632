import java.util.List;

/**
 * Main Data Processing System that orchestrates worker threads processing tasks.
 * Creates tasks, manages worker threads, and displays results.
 */
public class DataProcessingSystem {
    private static final int NUM_WORKER_THREADS = 4;
    private static final int NUM_TASKS = 15;

    /**
     * Main method to run the data processing system.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Data Processing System Starting");
        System.out.println("========================================");
        System.out.println("Configuration:");
        System.out.println("  Worker Threads: " + NUM_WORKER_THREADS);
        System.out.println("  Tasks to Process: " + NUM_TASKS);
        System.out.println("========================================\n");

        SharedTaskQueue taskQueue = null;
        ResultsCollector resultsCollector = null;
        Thread[] workerThreads = null;

        try {
            // Initialize shared resources
            taskQueue = new SharedTaskQueue();
            resultsCollector = new ResultsCollector();

            // Generate and add tasks to the queue
            System.out.println("[Main] Generating " + NUM_TASKS + " tasks...");
            generateTasks(taskQueue, NUM_TASKS);
            System.out.println("[Main] All tasks added to queue.\n");

            // Close the queue to signal no more tasks will be added
            taskQueue.close();

            // Create and start worker threads
            System.out.println("[Main] Creating " + NUM_WORKER_THREADS + " worker threads...");
            workerThreads = new Thread[NUM_WORKER_THREADS];
            
            for (int i = 0; i < NUM_WORKER_THREADS; i++) {
                WorkerThread worker = new WorkerThread(i + 1, taskQueue, resultsCollector);
                workerThreads[i] = new Thread(worker);
                workerThreads[i].start();
                System.out.println("[Main] Started worker thread " + (i + 1));
            }
            System.out.println();

            // Wait for all worker threads to complete
            System.out.println("[Main] Waiting for all worker threads to complete...");
            for (int i = 0; i < workerThreads.length; i++) {
                try {
                    workerThreads[i].join();
                    System.out.println("[Main] Worker thread " + (i + 1) + " has completed.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("[Main] Interrupted while waiting for worker thread " + (i + 1) + ": " + e.getMessage());
                }
            }
            System.out.println();

            // Display results and statistics
            displayResults(resultsCollector, NUM_TASKS);

        } catch (Exception e) {
            System.err.println("[Main] Fatal error in data processing system: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n========================================");
            System.out.println("Data Processing System Completed");
            System.out.println("========================================");
        }
    }

    /**
     * Generate tasks and add them to the queue.
     * @param taskQueue Queue to add tasks to
     * @param numTasks Number of tasks to generate
     */
    private static void generateTasks(SharedTaskQueue taskQueue, int numTasks) {
        try {
            for (int i = 1; i <= numTasks; i++) {
                String taskData = "Data-" + i + "-" + generateRandomData();
                Task task = new Task(i, taskData, i % 3); // Priority cycles 0, 1, 2
                
                try {
                    taskQueue.addTask(task);
                } catch (IllegalStateException e) {
                    System.err.println("[Main] Error adding task " + i + ": " + e.getMessage());
                } catch (NullPointerException e) {
                    System.err.println("[Main] Null task error for task " + i + ": " + e.getMessage());
                }
                
                // Small delay between task additions for better logging visibility
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("[Main] Interrupted while generating tasks: " + e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("[Main] Error generating tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generate random data string for task.
     * @return Random data string
     */
    private static String generateRandomData() {
        String[] dataTypes = {"Text", "Number", "Image", "Video", "Audio", "Document"};
        int randomIndex = (int)(Math.random() * dataTypes.length);
        return dataTypes[randomIndex];
    }

    /**
     * Display results and statistics.
     * @param resultsCollector Collector containing all results
     * @param expectedTasks Expected number of tasks
     */
    private static void displayResults(ResultsCollector resultsCollector, int expectedTasks) {
        System.out.println("========================================");
        System.out.println("Processing Results");
        System.out.println("========================================");
        
        try {
            List<Result> results = resultsCollector.getResults();
            int actualResults = results.size();
            
            System.out.println("Expected Tasks: " + expectedTasks);
            System.out.println("Processed Results: " + actualResults);
            System.out.println();
            
            if (actualResults == 0) {
                System.out.println("WARNING: No results were collected!");
                return;
            }
            
            // Display results by worker
            System.out.println("Results by Worker Thread:");
            System.out.println("-------------------------");
            for (int workerId = 1; workerId <= NUM_WORKER_THREADS; workerId++) {
                int count = 0;
                for (Result result : results) {
                    if (result.getWorkerId() == workerId) {
                        count++;
                    }
                }
                System.out.println("  Worker " + workerId + ": " + count + " tasks processed");
            }
            System.out.println();
            
            // Display all results
            System.out.println("All Results:");
            System.out.println("------------");
            for (Result result : results) {
                System.out.println("  " + result.toString());
            }
            System.out.println();
            
            // Check for duplicates or missing tasks
            if (actualResults != expectedTasks) {
                System.out.println("WARNING: Result count mismatch!");
                System.out.println("  Expected: " + expectedTasks + ", Actual: " + actualResults);
            } else {
                System.out.println("SUCCESS: All tasks processed successfully!");
            }
            
        } catch (Exception e) {
            System.err.println("[Main] Error displaying results: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

