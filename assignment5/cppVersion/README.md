# Ride Sharing System

A C++ demonstration of Object-Oriented Programming principles: Encapsulation, Inheritance, and Polymorphism.

## Compiling the code

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

