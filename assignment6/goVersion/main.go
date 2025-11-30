package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

const (
	numWorkers = 4
	numTasks   = 15
)

var dataTypes = []string{"Text", "Number", "Image", "Video", "Audio", "Document"}

func main() {
	fmt.Println("========================================")
	fmt.Println("Data Processing System Starting")
	fmt.Println("========================================")
	fmt.Printf("Configuration:\n")
	fmt.Printf("  Worker Threads: %d\n", numWorkers)
	fmt.Printf("  Tasks to Process: %d\n", numTasks)
	fmt.Println("========================================\n")

	// Create channels for tasks and results
	taskChan := make(chan Task, numTasks)
	resultChan := make(chan Result, numTasks)
	doneChan := make(chan bool, numWorkers)

	// Use WaitGroup to wait for all workers to complete
	var wg sync.WaitGroup

	// Defer closing channels to ensure cleanup
	defer func() {
		close(taskChan)
		close(resultChan)
		close(doneChan)
		fmt.Println("\n========================================")
		fmt.Println("Data Processing System Completed")
		fmt.Println("========================================")
	}()

	// Generate and send tasks to the task channel
	fmt.Printf("[Main] Generating %d tasks...\n", numTasks)
	err := generateTasks(taskChan, numTasks)
	if err != nil {
		fmt.Printf("[Main] Error generating tasks: %v\n", err)
		return
	}
	fmt.Printf("[Main] All tasks added to queue.\n\n")

	// Close task channel to signal no more tasks will be sent
	close(taskChan)

	// Start worker goroutines
	fmt.Printf("[Main] Creating %d worker threads...\n", numWorkers)
	for i := 1; i <= numWorkers; i++ {
		wg.Add(1)
		go worker(i, taskChan, resultChan, doneChan, &wg)
		fmt.Printf("[Main] Started worker thread %d\n", i)
	}
	fmt.Println()

	// Start a goroutine to collect results
	results := make([]Result, 0, numTasks)
	var resultsMutex sync.Mutex
	var resultsWg sync.WaitGroup
	resultsWg.Add(1)

	go func() {
		defer resultsWg.Done()
		for result := range resultChan {
			resultsMutex.Lock()
			results = append(results, result)
			resultsMutex.Unlock()
			fmt.Printf("[ResultsCollector] Added result for task %d by worker %d (Total results: %d)\n",
				result.TaskID, result.WorkerID, len(results))
		}
	}()

	// Wait for all worker threads to complete
	fmt.Println("[Main] Waiting for all worker threads to complete...")
	wg.Wait()
	fmt.Println()

	// Close result channel after all workers are done
	close(resultChan)

	// Wait for result collection to complete
	resultsWg.Wait()

	// Wait for all done signals (optional, for verification)
	doneCount := 0
	for {
		select {
		case <-doneChan:
			doneCount++
			if doneCount >= numWorkers {
				goto allDone
			}
		case <-time.After(1 * time.Second):
			// Timeout to prevent infinite wait
			goto allDone
		}
	}
allDone:

	// Display results and statistics
	resultsMutex.Lock()
	displayResults(results, numTasks)
	resultsMutex.Unlock()
}

// generateTasks generates tasks and sends them to the task channel.
// Returns an error if task generation fails.
func generateTasks(taskChan chan<- Task, numTasks int) error {
	rand.Seed(time.Now().UnixNano())

	for i := 1; i <= numTasks; i++ {
		taskData := fmt.Sprintf("Data-%d-%s", i, generateRandomData())
		task := Task{
			TaskID:   i,
			Data:     taskData,
			Priority: i % 3, // Priority cycles 0, 1, 2
		}

		// Send task to channel
		select {
		case taskChan <- task:
			fmt.Printf("[Queue] Added task: %d (Queue size: %d)\n", task.TaskID, len(taskChan))
		case <-time.After(5 * time.Second):
			return fmt.Errorf("timeout sending task %d to channel", i)
		}

		// Small delay between task additions for better logging visibility
		time.Sleep(50 * time.Millisecond)
	}

	return nil
}

// generateRandomData returns a random data type string.
func generateRandomData() string {
	randomIndex := rand.Intn(len(dataTypes))
	return dataTypes[randomIndex]
}

// displayResults displays the processing results and statistics.
func displayResults(results []Result, expectedTasks int) {
	fmt.Println("========================================")
	fmt.Println("Processing Results")
	fmt.Println("========================================")

	actualResults := len(results)
	fmt.Printf("Expected Tasks: %d\n", expectedTasks)
	fmt.Printf("Processed Results: %d\n", actualResults)
	fmt.Println()

	if actualResults == 0 {
		fmt.Println("WARNING: No results were collected!")
		return
	}

	// Display results by worker
	fmt.Println("Results by Worker Thread:")
	fmt.Println("-------------------------")
	for workerID := 1; workerID <= numWorkers; workerID++ {
		count := 0
		for _, result := range results {
			if result.WorkerID == workerID {
				count++
			}
		}
		fmt.Printf("  Worker %d: %d tasks processed\n", workerID, count)
	}
	fmt.Println()

	// Display all results
	fmt.Println("All Results:")
	fmt.Println("------------")
	for _, result := range results {
		fmt.Printf("  %s\n", result.String())
	}
	fmt.Println()

	// Check for duplicates or missing tasks
	if actualResults != expectedTasks {
		fmt.Println("WARNING: Result count mismatch!")
		fmt.Printf("  Expected: %d, Actual: %d\n", expectedTasks, actualResults)
	} else {
		fmt.Println("SUCCESS: All tasks processed successfully!")
	}
}

