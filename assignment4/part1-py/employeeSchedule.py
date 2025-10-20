import random
from typing import List, Tuple

# ============================================================================
# EMPLOYEE CLASS
# ============================================================================

class Employee:
    """Represents an employee with their preferences and assigned schedule."""
    
    def __init__(self, name: str):
        self.name = name
        self.preferences = {}  # {day: [shift1, shift2, shift3]} - ranked preferences
        self.assignedShifts = {}  # {day: shift} - final schedule assignments
        self.daysWorked = 0
        self.maxDays = 5
    
    def addPreference(self, day: str, shiftPreferences: List[str]):
        """Add ranked shift preferences for a specific day."""
        self.preferences[day] = shiftPreferences
    
    def assignShift(self, day: str, shift: str) -> bool:
        """Assign employee to a shift on a specific day."""
        if self.daysWorked >= self.maxDays:
            return False
        if day not in self.assignedShifts:
            self.assignedShifts[day] = shift
            self.daysWorked += 1
            return True
        return False
    
    def isAvailable(self, day: str) -> bool:
        """Check if employee is available on a specific day."""
        return day not in self.assignedShifts and self.daysWorked < self.maxDays
    
    def getPreferredShifts(self, day: str) -> List[str]:
        """Get ranked shift preferences for a specific day."""
        return self.preferences.get(day, [])

# ============================================================================
# SCHEDULE CLASS
# ============================================================================

class Schedule:
    """Manages the weekly schedule with 7 days and 3 shifts per day."""
    
    DAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    SHIFTS = ['morning', 'afternoon', 'evening']
    
    def __init__(self):
        self.schedule = {day: {shift: [] for shift in self.SHIFTS} for day in self.DAYS}
        self.minEmployeesPerShift = 2
    
    def addEmployeeToShift(self, day: str, shift: str, employee: Employee) -> bool:
        """Add an employee to a specific shift."""
        if day in self.schedule and shift in self.schedule[day]:
            self.schedule[day][shift].append(employee)
            return True
        return False
    
    def getShiftCount(self, day: str, shift: str) -> int:
        """Get the number of employees assigned to a specific shift."""
        return len(self.schedule.get(day, {}).get(shift, []))
    
    def isShiftFull(self, day: str, shift: str, maxPerShift: int = 4) -> bool:
        """Check if a shift has reached maximum capacity."""
        return self.getShiftCount(day, shift) >= maxPerShift
    
    def getUnderstaffedShifts(self) -> List[Tuple[str, str]]:
        """Get list of shifts that need more employees."""
        understaffed = []
        for day in self.DAYS:
            for shift in self.SHIFTS:
                if self.getShiftCount(day, shift) < self.minEmployeesPerShift:
                    understaffed.append((day, shift))
        return understaffed
    
    def displaySchedule(self):
        """Display the weekly schedule in a readable format."""
        print("\n" + "="*80)
        print("WEEKLY EMPLOYEE SCHEDULE")
        print("="*80)
        
        for day in self.DAYS:
            print(f"\n{day.upper()}")
            print("-" * 40)
            for shift in self.SHIFTS:
                employees = [emp.name for emp in self.schedule[day][shift]]
                if employees:
                    print(f"  {shift.capitalize():>10}: {', '.join(employees)}")
                else:
                    print(f"  {shift.capitalize():>10}: No employees assigned")
        
        print("\n" + "="*80)

# ============================================================================
# MAIN SCHEDULER CLASS
# ============================================================================

class EmployeeScheduler:
    """Main scheduler class that handles the scheduling logic."""
    
    def __init__(self):
        self.employees = []
        self.schedule = Schedule()
    
    def collectEmployeeData(self):
        """Interactive CLI to collect employee information."""
        print("Employee Scheduling System")
        print("=" * 40)
        
        while True:
            name = input("\nEnter employee name (or 'done' to finish): ").strip()
            if name.lower() == 'done':
                break
            if not name:
                print("Please enter a valid name.")
                continue
            
            employee = Employee(name)
            self.employees.append(employee)
            
            print(f"\nSetting preferences for {name}")
            print("Available days: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday")
            print("Available shifts: morning, afternoon, evening")
            
            # Collect preferences for each day
            for day in Schedule.DAYS:
                work_today = input(f"\nDoes {name} want to work on {day}? (y/n): ").strip().lower()
                if work_today == 'y' or work_today == 'yes':
                    print(f"Enter shift preferences for {day} (ranked, separated by commas):")
                    print("Example: morning, evening, afternoon")
                    shift_input = input("Preferences: ").strip()
                    
                    if shift_input:
                        shifts = [s.strip().lower() for s in shift_input.split(',')]
                        # Validate shifts
                        validShifts = [s for s in shifts if s in Schedule.SHIFTS]
                        if validShifts:
                            employee.addPreference(day, validShifts)
                        else:
                            print("Invalid shift(s). Please use: morning, afternoon, evening")
    
    def assignEmployees(self):
        """Main scheduling algorithm with preference prioritization."""
        print("\nGenerating schedule...")
        
        # First pass: Assign based on first preferences
        for employee in self.employees:
            for day in Schedule.DAYS:
                if not employee.isAvailable(day):
                    continue
                
                preferredShifts = employee.getPreferredShifts(day)
                if not preferredShifts:
                    continue
                
                # Try to assign to first preference
                firstPreference = preferredShifts[0]
                if not self.schedule.isShiftFull(day, firstPreference):
                    if employee.assignShift(day, firstPreference):
                        self.schedule.addEmployeeToShift(day, firstPreference, employee)
        
        # Second pass: Handle conflicts and assign remaining preferences
        for employee in self.employees:
            for day in Schedule.DAYS:
                if not employee.isAvailable(day):
                    continue
                
                preferredShifts = employee.getPreferredShifts(day)
                if not preferredShifts:
                    continue
                
                # Try each preference in order
                for shift in preferredShifts:
                    if not self.schedule.isShiftFull(day, shift):
                        if employee.assignShift(day, shift):
                            self.schedule.addEmployeeToShift(day, shift, employee)
                            break
        
        # Third pass: Fill understaffed shifts
        self.fillUnderstaffedShifts()
    
    def fillUnderstaffedShifts(self):
        """Fill shifts that don't meet minimum staffing requirements."""
        understaffed = self.schedule.getUnderstaffedShifts()
        
        for day, shift in understaffed:
            needed = self.schedule.minEmployeesPerShift - self.schedule.getShiftCount(day, shift)
            
            # Find available employees
            availableEmployees = [emp for emp in self.employees 
                                 if emp.isAvailable(day)]
            
            # Randomly select employees to fill the shift
            selected = random.sample(availableEmployees, min(needed, len(availableEmployees)))
            
            for employee in selected:
                if employee.assignShift(day, shift):
                    self.schedule.addEmployeeToShift(day, shift, employee)
    
    def run(self):
        """Main program loop."""
        self.collectEmployeeData()
        
        if not self.employees:
            print("No employees added. Exiting.")
            return
        
        self.assignEmployees()
        self.schedule.displaySchedule()
        
        # Display summary
        print(f"\nSUMMARY:")
        print(f"Total employees: {len(self.employees)}")
        for employee in self.employees:
            print(f"{employee.name}: {employee.daysWorked} days worked")

# ============================================================================
# MAIN PROGRAM ENTRY POINT
# ============================================================================

def main():
    """Entry point of the program."""
    scheduler = EmployeeScheduler()
    scheduler.run()

if __name__ == "__main__":
    main()
