import java.util.*;

// ============================================================================
// EMPLOYEE CLASS
// ============================================================================

public class Employee {
    private String name;
    private Map<String, List<String>> preferences; // {day: [shift1, shift2, shift3]} - ranked preferences
    private Map<String, String> assignedShifts; // {day: shift} - final schedule assignments
    private int daysWorked;
    private int maxDays;
    
    public Employee(String name) {
        this.name = name;
        this.preferences = new HashMap<>();
        this.assignedShifts = new HashMap<>();
        this.daysWorked = 0;
        this.maxDays = 5;
    }
    
    public void addPreference(String day, List<String> shiftPreferences) {
        // Add ranked shift preferences for a specific day
        this.preferences.put(day, new ArrayList<>(shiftPreferences));
    }
    
    public boolean assignShift(String day, String shift) {
        // Assign employee to a shift on a specific day
        if (this.daysWorked >= this.maxDays) {
            return false;
        }
        if (!this.assignedShifts.containsKey(day)) {
            this.assignedShifts.put(day, shift);
            this.daysWorked++;
            return true;
        }
        return false;
    }
    
    public boolean isAvailable(String day) {
        // Check if employee is available on a specific day
        return !this.assignedShifts.containsKey(day) && this.daysWorked < this.maxDays;
    }
    
    public List<String> getPreferredShifts(String day) {
        // Get ranked shift preferences for a specific day
        return this.preferences.getOrDefault(day, new ArrayList<>());
    }
    
    // Getters
    public String getName() {
        return this.name;
    }
    
    public int getDaysWorked() {
        return this.daysWorked;
    }
    
    public Map<String, String> getAssignedShifts() {
        return new HashMap<>(this.assignedShifts);
    }
}


