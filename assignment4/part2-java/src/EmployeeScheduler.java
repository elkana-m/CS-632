import java.util.*;

// ============================================================================
// MAIN SCHEDULER CLASS
// ============================================================================

public class EmployeeScheduler {
    private List<Employee> employees;
    private Schedule schedule;
    private Scanner scanner;
    
    public EmployeeScheduler() {
        this.employees = new ArrayList<>();
        this.schedule = new Schedule();
        this.scanner = new Scanner(System.in);
    }
    
    public void collectEmployeeData() {
        // Interactive CLI to collect employee information
        System.out.println("Employee Scheduling System");
        System.out.println("=".repeat(40));
        
        while (true) {
            System.out.print("\nEnter employee name (or 'done' to finish): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            if (name.isEmpty()) {
                System.out.println("Please enter a valid name.");
                continue;
            }
            
            Employee employee = new Employee(name);
            this.employees.add(employee);
            
            System.out.println("\nSetting preferences for " + name);
            System.out.println("Available days: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday");
            System.out.println("Available shifts: morning, afternoon, evening");
            
            // Collect preferences for each day
            for (String day : Schedule.DAYS) {
                System.out.print("\nDoes " + name + " want to work on " + day + "? (y/n): ");
                String workToday = scanner.nextLine().trim().toLowerCase();
                if (workToday.equals("y") || workToday.equals("yes")) {
                    System.out.println("Enter shift preferences for " + day + " (ranked, separated by commas):");
                    System.out.println("Example: morning, evening, afternoon");
                    System.out.print("Preferences: ");
                    String shiftInput = scanner.nextLine().trim();
                    
                    if (!shiftInput.isEmpty()) {
                        String[] shifts = shiftInput.split(",");
                        List<String> validShifts = new ArrayList<>();
                        for (String shift : shifts) {
                            String trimmedShift = shift.trim().toLowerCase();
                            if (Arrays.asList(Schedule.SHIFTS).contains(trimmedShift)) {
                                validShifts.add(trimmedShift);
                            }
                        }
                        if (!validShifts.isEmpty()) {
                            employee.addPreference(day, validShifts);
                        } else {
                            System.out.println("Invalid shift(s). Please use: morning, afternoon, evening");
                        }
                    }
                }
            }
        }
    }
    
    public void assignEmployees() {
        // Main scheduling algorithm with preference prioritization
        System.out.println("\nGenerating schedule...");
        
        // First pass: Assign based on first preferences
        for (Employee employee : this.employees) {
            for (String day : Schedule.DAYS) {
                if (!employee.isAvailable(day)) {
                    continue;
                }
                
                List<String> preferredShifts = employee.getPreferredShifts(day);
                if (preferredShifts.isEmpty()) {
                    continue;
                }
                
                // Try to assign to first preference
                String firstPreference = preferredShifts.get(0);
                if (!this.schedule.isShiftFull(day, firstPreference)) {
                    if (employee.assignShift(day, firstPreference)) {
                        this.schedule.addEmployeeToShift(day, firstPreference, employee);
                    }
                }
            }
        }
        
        // Second pass: Handle conflicts and assign remaining preferences
        for (Employee employee : this.employees) {
            for (String day : Schedule.DAYS) {
                if (!employee.isAvailable(day)) {
                    continue;
                }
                
                List<String> preferredShifts = employee.getPreferredShifts(day);
                if (preferredShifts.isEmpty()) {
                    continue;
                }
                
                // Try each preference in order
                for (String shift : preferredShifts) {
                    if (!this.schedule.isShiftFull(day, shift)) {
                        if (employee.assignShift(day, shift)) {
                            this.schedule.addEmployeeToShift(day, shift, employee);
                            break;
                        }
                    }
                }
            }
        }
        
        // Third pass: Fill understaffed shifts
        this.fillUnderstaffedShifts();
    }
    
    public void fillUnderstaffedShifts() {
        // Fill shifts that don't meet minimum staffing requirements
        List<String[]> understaffed = this.schedule.getUnderstaffedShifts();
        
        for (String[] shiftInfo : understaffed) {
            String day = shiftInfo[0];
            String shift = shiftInfo[1];
            int needed = this.schedule.getMinEmployeesPerShift() - this.schedule.getShiftCount(day, shift);
            
            // Find available employees
            List<Employee> availableEmployees = new ArrayList<>();
            for (Employee emp : this.employees) {
                if (emp.isAvailable(day)) {
                    availableEmployees.add(emp);
                }
            }
            
            // Randomly select employees to fill the shift
            int toSelect = Math.min(needed, availableEmployees.size());
            if (toSelect > 0) {
                Collections.shuffle(availableEmployees);
                List<Employee> selected = availableEmployees.subList(0, toSelect);
                
                for (Employee employee : selected) {
                    if (employee.assignShift(day, shift)) {
                        this.schedule.addEmployeeToShift(day, shift, employee);
                    }
                }
            }
        }
    }
    
    public void run() {
        // Main program loop
        this.collectEmployeeData();
        
        if (this.employees.isEmpty()) {
            System.out.println("No employees added. Exiting.");
            return;
        }
        
        this.assignEmployees();
        this.schedule.displaySchedule();
        
        // Display summary
        System.out.println("\nSUMMARY:");
        System.out.println("Total employees: " + this.employees.size());
        for (Employee employee : this.employees) {
            System.out.println(employee.getName() + ": " + employee.getDaysWorked() + " days worked");
        }
        
        this.scanner.close();
    }
}


