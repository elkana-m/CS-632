# Ride Sharing System

A C++ demonstration of Object-Oriented Programming principles: Encapsulation, Inheritance, and Polymorphism.

## Files

- `Ride.h` / `Ride.cpp` - Base Ride class
- `StandardRide.h` / `StandardRide.cpp` - Standard ride subclass
- `PremiumRide.h` / `PremiumRide.cpp` - Premium ride subclass
- `Driver.h` / `Driver.cpp` - Driver class with encapsulation
- `Rider.h` / `Rider.cpp` - Rider class
- `main.cpp` - Main demonstration program

## Compilation

To compile the program, use a C++ compiler (g++ or clang++):

```bash
g++ -std=c++11 -o ride_sharing *.cpp
```

Or with clang++:

```bash
clang++ -std=c++11 -o ride_sharing *.cpp
```

## Running the Program

After compilation, run the executable:

```bash
./ride_sharing
```

On Windows (if using MinGW or similar):

```bash
ride_sharing.exe
```

## What the Program Demonstrates

1. **Encapsulation**: Private members (`assignedRides`, `requestedRides`) accessed only through public methods
2. **Inheritance**: `StandardRide` and `PremiumRide` inherit from base `Ride` class
3. **Polymorphism**: Different ride types stored in a vector and methods called polymorphically

## Requirements

- C++11 or later
- Standard C++ compiler (g++, clang++, or MSVC)

