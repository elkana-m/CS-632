import java.util.*;

// ============================================================================
// SCHEDULE CLASS
// ============================================================================

public class Schedule {
    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static final String[] SHIFTS = {"morning", "afternoon", "evening"};
    
    private Map<String, Map<String, List<Employee>>> schedule;
    private int minEmployeesPerShift;
    
    public Schedule() {
        this.schedule = new HashMap<>();
        this.minEmployeesPerShift = 2;
        
        // Initialize schedule structure
        for (String day : DAYS) {
            Map<String, List<Employee>> daySchedule = new HashMap<>();
            for (String shift : SHIFTS) {
                daySchedule.put(shift, new ArrayList<>());
            }
            this.schedule.put(day, daySchedule);
        }
    }
    
    public boolean addEmployeeToShift(String day, String shift, Employee employee) {
        // Add an employee to a specific shift
        if (this.schedule.containsKey(day) && this.schedule.get(day).containsKey(shift)) {
            this.schedule.get(day).get(shift).add(employee);
            return true;
        }
        return false;
    }
    
    public int getShiftCount(String day, String shift) {
        // Get the number of employees assigned to a specific shift
        return this.schedule.getOrDefault(day, new HashMap<>()).getOrDefault(shift, new ArrayList<>()).size();
    }
    
    public boolean isShiftFull(String day, String shift, int maxPerShift) {
        // Check if a shift has reached maximum capacity
        return this.getShiftCount(day, shift) >= maxPerShift;
    }
    
    public boolean isShiftFull(String day, String shift) {
        // Check if a shift has reached maximum capacity (default max = 4)
        return this.isShiftFull(day, shift, 4);
    }
    
    public int getMinEmployeesPerShift() {
        // Get the minimum number of employees required per shift
        return this.minEmployeesPerShift;
    }
    
    public List<String[]> getUnderstaffedShifts() {
        // Get list of shifts that need more employees
        List<String[]> understaffed = new ArrayList<>();
        for (String day : DAYS) {
            for (String shift : SHIFTS) {
                if (this.getShiftCount(day, shift) < this.minEmployeesPerShift) {
                    understaffed.add(new String[]{day, shift});
                }
            }
        }
        return understaffed;
    }
    
    public void displaySchedule() {
        // Display the weekly schedule in a readable format
        System.out.println("\n" + "=".repeat(80));
        System.out.println("WEEKLY EMPLOYEE SCHEDULE");
        System.out.println("=".repeat(80));
        
        for (String day : DAYS) {
            System.out.println("\n" + day.toUpperCase());
            System.out.println("-".repeat(40));
            for (String shift : SHIFTS) {
                List<Employee> employees = this.schedule.get(day).get(shift);
                if (!employees.isEmpty()) {
                    List<String> employeeNames = new ArrayList<>();
                    for (Employee emp : employees) {
                        employeeNames.add(emp.getName());
                    }
                    System.out.printf("  %10s: %s%n", 
                        shift.substring(0, 1).toUpperCase() + shift.substring(1),
                        String.join(", ", employeeNames));
                } else {
                    System.out.printf("  %10s: No employees assigned%n",
                        shift.substring(0, 1).toUpperCase() + shift.substring(1));
                }
            }
        }
        
        System.out.println("\n" + "=".repeat(80));
    }
}


