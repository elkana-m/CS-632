# Data Processing System

A Java demonstration of multi-threaded programming with synchronized shared resources, worker threads, and concurrent task processing.

## Compiling the code

To compile the program, use the Java compiler:

```bash
cd src
javac *.java
```

## Running the Program

After compilation, run the program:

```bash
java DataProcessingSystem
```

## What the Program Demonstrates

1. **Shared Resource Queue**: Thread-safe queue using `BlockingQueue` with synchronized operations to prevent race conditions
2. **Worker Threads**: Multiple threads processing tasks in parallel from a shared queue
3. **Concurrency Management**: Synchronized blocks and thread-safe collections for safe concurrent access
4. **Exception Handling**: Comprehensive handling of `InterruptedException`, `NullPointerException`, and `IllegalStateException`
5. **Thread Lifecycle**: Proper thread creation, execution, and termination with logging
