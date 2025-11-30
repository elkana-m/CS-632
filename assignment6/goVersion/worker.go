package main

import (
	"fmt"
	"math/rand"
	"strings"
	"sync"
	"time"
)

// processTask simulates processing a task by performing computational work.
// Returns the processed data and any error that occurred.
func processTask(task Task) (string, error) {
	// Simulate processing delay (random between 500ms and 2000ms)
	processingTime := time.Duration(500+rand.Intn(1500)) * time.Millisecond
	time.Sleep(processingTime)

	// Simulate data processing (e.g., transform the data)
	processedData := fmt.Sprintf("[PROCESSED] %s (processed in %v)", 
		strings.ToUpper(task.Data), processingTime)

	return processedData, nil
}

// worker processes tasks from the task channel and sends results to the result channel.
// It runs as a goroutine and signals completion via the done channel.
func worker(workerID int, taskChan <-chan Task, resultChan chan<- Result, done chan<- bool, wg *sync.WaitGroup) {
	defer func() {
		fmt.Printf("[Worker %d] Thread completed.\n", workerID)
		if wg != nil {
			wg.Done()
		}
	}()

	fmt.Printf("[Worker %d] Thread started.\n", workerID)

	for task := range taskChan {
		// Process the task
		fmt.Printf("[Worker %d] Processing task: %d\n", workerID, task.TaskID)

		processedData, err := processTask(task)
		if err != nil {
			fmt.Printf("[Worker %d] Error processing task %d: %v\n", workerID, task.TaskID, err)
			continue
		}

		// Create result
		timestamp := time.Now().UnixMilli()
		result := Result{
			TaskID:        task.TaskID,
			ProcessedData: processedData,
			WorkerID:      workerID,
			Timestamp:     timestamp,
		}

		// Send result to result channel
		select {
		case resultChan <- result:
			fmt.Printf("[Worker %d] Completed task: %d\n", workerID, task.TaskID)
		default:
			fmt.Printf("[Worker %d] Error: result channel is full or closed for task %d\n", workerID, task.TaskID)
		}
	}

	// Signal completion
	select {
	case done <- true:
		// Successfully signaled completion
	default:
		// Done channel might be closed or full, but that's okay
	}
}

