import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread-safe shared queue for tasks.
 * Uses BlockingQueue for thread-safe operations with proper synchronization.
 */
public class SharedTaskQueue {
    private BlockingQueue<Task> queue;
    private boolean isClosed;

    /**
     * Constructor initializes the queue.
     */
    public SharedTaskQueue() {
        this.queue = new LinkedBlockingQueue<>();
        this.isClosed = false;
    }

    /**
     * Add a task to the queue with synchronization.
     * @param task Task to be added
     * @throws IllegalStateException if queue is closed
     */
    public synchronized void addTask(Task task) {
        if (isClosed) {
            throw new IllegalStateException("Queue is closed. Cannot add new tasks.");
        }
        if (task == null) {
            throw new NullPointerException("Cannot add null task to queue.");
        }
        try {
            queue.put(task);
            System.out.println("[Queue] Added task: " + task.getTaskId() + " (Queue size: " + queue.size() + ")");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[Queue] Interrupted while adding task: " + e.getMessage());
        }
    }

    /**
     * Retrieve a task from the queue with synchronization.
     * Returns null if queue is empty and closed.
     * @return Task from queue, or null if empty
     */
    public synchronized Task getTask() {
        if (queue.isEmpty() && isClosed) {
            return null;
        }
        try {
            Task task = queue.poll();
            if (task != null) {
                System.out.println("[Queue] Retrieved task: " + task.getTaskId() + " (Queue size: " + queue.size() + ")");
            }
            return task;
        } catch (Exception e) {
            System.err.println("[Queue] Error retrieving task: " + e.getMessage());
            return null;
        }
    }

    /**
     * Check if the queue is empty.
     * @return true if queue is empty, false otherwise
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Get the current size of the queue.
     * @return Queue size
     */
    public synchronized int size() {
        return queue.size();
    }

    /**
     * Close the queue to signal no more tasks will be added.
     * This helps worker threads know when to stop waiting.
     */
    public synchronized void close() {
        this.isClosed = true;
        System.out.println("[Queue] Queue closed. No more tasks will be added.");
    }

    /**
     * Check if the queue is closed.
     * @return true if closed, false otherwise
     */
    public synchronized boolean isClosed() {
        return isClosed;
    }
}

