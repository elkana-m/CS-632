Implementing Control Structures: Employee Schedule Management App
--------------------------------

This project contains two implementations of an Employee Schedule Management System; one in Python and one in Java. Both programs allow you to create employee schedules by collecting employee preferences and automatically assigning shifts.

## Project Structure
```
assignment4/
  ─ part1-py/           # Python implementation
    ─ employeeSchedule.py

  ─ part2-java/        # Java implementation
    ─ src/
        ─ Main.java
        ─ Employee.java
        ─ EmployeeScheduler.java
        ─ Schedule.java
```

---

## Part 1 - Python Implementation

### Requirements
- Python 3.7 or higher

### How to Run

1. **Navigate to the Python directory:**
   ```bash
   cd part1-py
   ```

2. **Create a virtual environment (recommended):**
   ```bash
   python -m venv venv
   ```

3. **Activate the virtual environment:**
   ```bash
   source venv/bin/activate
   ```

4. **Run the program:**
   ```bash
   python employeeSchedule.py
   ```

5. **Follow the prompts:**
   - Enter employee names (type 'done' when finished)
   - For each employee, specify which days they want to work
   - Rank their shift preferences (morning, afternoon, evening)

6. **View the generated schedule** - the program will display a weekly schedule with all employee assignments

### Deactivating Virtual Environment
When you're done, deactivate the virtual environment:
```bash
deactivate
```


---

## Part 2 - Java Implementation

### Requirements
- Java Development Kit (JDK) 8 or higher
- **Important:** You need JDK, not just JRE (Java Runtime Environment)

### Installing JDK (if needed)

**On macOS:**
```bash
# Using Homebrew (recommended)
brew install openjdk@8

# Or download from Amazon website
# https://downloads.corretto.aws/#/downloads?version=25

# I stayed away from Oracle website as they require to signup
# However, feel free to use it if you don't mind signing up.
# https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
```

**Verify installation:**
```bash
javac -version
java -version
```

### How to Run

1. **Navigate to the Java source directory:**
   ```bash
   cd part2-java/src
   ```

2. **Compile the Java files:**
   ```bash
   javac *.java
   ```

3. **Run the program:**
   ```bash
   java Main
   ```

4. **Follow the prompts:**
   - Enter employee names (type 'done' when finished)
   - For each employee, specify which days they want to work
   - Rank their shift preferences (morning, afternoon, evening)

5. **View the generated schedule** - the program will display a weekly schedule with all employee assignments

---

## How Both Programs Work

1. **Employee Input:** You add employees and their availability preferences
2. **Preference Collection:** For each day an employee wants to work, you rank their shift preferences
3. **Automatic Scheduling:** The program assigns shifts based on:
   - Employee preferences (first choice gets priority)
   - Maximum 5 days per employee
   - Minimum 2 employees per shift
   - Maximum 4 employees per shift
4. **Schedule Display:** Shows a weekly view with all assignments

## Instructions
**Program not responding:**
- Make sure to type 'done' (not 'Done' or 'DONE') when finished adding employees
- Make sure to type 'yes' or 'y' to select a shift day preference, any other word/letter
  will be considered as a no
- Follow the exact format for shift preferences: "morning, afternoon, evening"

## Troubleshooting
<!-- You might run into the following issues. Don't worry, I ran into them too.
 Below is a small approach you might consider to take to reslove-->

**Java "javac not found" error:**
- You have JRE but need JDK
- Install JDK using the instructions above
- Make sure your PATH includes the JDK bin directory

**Python import errors:**
- Make sure you activated the virtual environment (if you created one)
- The program only uses built-in Python modules, so no additional packages are needed
