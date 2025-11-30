package main

import "fmt"

// Task represents a data processing task
type Task struct {
	TaskID   int
	Data     string
	Priority int
}

// String returns a string representation of the task for logging
func (t Task) String() string {
	return fmt.Sprintf("Task{id=%d, data='%s', priority=%d}", t.TaskID, t.Data, t.Priority)
}

// Result represents the processed result of a task
type Result struct {
	TaskID       int
	ProcessedData string
	WorkerID     int
	Timestamp    int64
}

// String returns a string representation of the result for logging
func (r Result) String() string {
	return fmt.Sprintf("Result{taskId=%d, processedData='%s', workerId=%d, timestamp=%d}",
		r.TaskID, r.ProcessedData, r.WorkerID, r.Timestamp)
}

