import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Thread-safe results collector for storing processed task results.
 * Uses synchronized blocks to ensure thread-safe access to the results list.
 */
public class ResultsCollector {
    private List<Result> results;

    /**
     * Constructor initializes the synchronized results list.
     */
    public ResultsCollector() {
        this.results = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Add a result to the collector with synchronization.
     * @param result Result to be added
     * @throws NullPointerException if result is null
     */
    public synchronized void addResult(Result result) {
        if (result == null) {
            throw new NullPointerException("Cannot add null result to collector.");
        }
        results.add(result);
        System.out.println("[ResultsCollector] Added result for task " + result.getTaskId() + 
                          " by worker " + result.getWorkerId() + " (Total results: " + results.size() + ")");
    }

    /**
     * Get a copy of all results to avoid external modification.
     * @return Copy of the results list
     */
    public synchronized List<Result> getResults() {
        return new ArrayList<>(results);
    }

    /**
     * Get the count of results.
     * @return Number of results collected
     */
    public synchronized int getResultCount() {
        return results.size();
    }

    /**
     * Clear all results (useful for testing).
     */
    public synchronized void clear() {
        results.clear();
        System.out.println("[ResultsCollector] All results cleared.");
    }
}

