# Ride Sharing System (Smalltalk)

A Smalltalk demonstration of Object-Oriented Programming principles: Encapsulation, Inheritance, and Polymorphism.

## Files

- `Ride.st` - Base Ride class
- `StandardRide.st` - Standard ride subclass
- `PremiumRide.st` - Premium ride subclass
- `Driver.st` - Driver class with encapsulation
- `Rider.st` - Rider class
- `RideSharingDemo.st` - Demonstration script

## Requirements

- A Smalltalk environment (Pharo, Squeak, GNU Smalltalk, or similar)
- The files should be loaded in the order listed above

## Loading the Program

### Option 1: Using File Browser (Pharo/Squeak)

1. Open your Smalltalk environment (Pharo or Squeak)
2. Use the File Browser or Monticello Browser
3. Load the files in this order:
   - `Ride.st`
   - `StandardRide.st`
   - `PremiumRide.st`
   - `Driver.st`
   - `Rider.st`
4. Open a Workspace and execute the contents of `RideSharingDemo.st`

### Option 2: Using FileIn (Pharo/Squeak)

1. Open a Workspace
2. Execute:
   ```smalltalk
   FileStream fileIn: 'Ride.st'.
   FileStream fileIn: 'StandardRide.st'.
   FileStream fileIn: 'PremiumRide.st'.
   FileStream fileIn: 'Driver.st'.
   FileStream fileIn: 'Rider.st'.
   ```
3. Then execute the contents of `RideSharingDemo.st`

### Option 3: Using GNU Smalltalk

1. Start GNU Smalltalk:
   ```bash
   gst
   ```
2. Load all files:
   ```smalltalk
   FileStream fileIn: 'Ride.st'.
   FileStream fileIn: 'StandardRide.st'.
   FileStream fileIn: 'PremiumRide.st'.
   FileStream fileIn: 'Driver.st'.
   FileStream fileIn: 'Rider.st'.
   ```
3. Execute the demo script:
   ```smalltalk
   FileStream fileIn: 'RideSharingDemo.st'.
   ```

## Running the Program

After loading all the class files, execute the demonstration script:

1. Open a Workspace (or use the command line in GNU Smalltalk)
2. Copy and paste the contents of `RideSharingDemo.st`
3. Execute it (Do It / Print It in Pharo/Squeak, or just execute in GNU Smalltalk)

The output will appear in the Transcript window (Pharo/Squeak) or standard output (GNU Smalltalk).

## What the Program Demonstrates

1. **Encapsulation**: Private instance variables (`assignedRides`, `requestedRides`) accessed only through public methods (`addRide:`, `requestRide:`)
2. **Inheritance**: `StandardRide` and `PremiumRide` inherit from base `Ride` class
3. **Polymorphism**: Different ride types stored in an `OrderedCollection` and methods called polymorphically using message passing

## Smalltalk-Specific Features

- **Message Passing**: All method calls use message syntax (e.g., `ride calculateFare`)
- **Collections**: Uses `OrderedCollection` (equivalent to vector in C++)
- **Transcript**: Output goes to Transcript window (standard Smalltalk output)
- **Instance Variables**: Private by default (encapsulation)
- **Abstract Methods**: Uses `self subclassResponsibility` pattern

## Notes

- Make sure to initialize class variables by calling `StandardRide initialize` and `PremiumRide initialize` before creating instances
- The demo script includes all necessary initialization
- In some Smalltalk environments, you may need to accept the class definitions after loading

