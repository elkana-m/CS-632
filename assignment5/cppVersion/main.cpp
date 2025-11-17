#include <iostream>
#include <iomanip>
#include <vector>
#include "Ride.h"
#include "StandardRide.h"
#include "PremiumRide.h"
#include "Driver.h"
#include "Rider.h"

int main() {
    std::cout << "========================================" << std::endl;
    std::cout << "   RIDE SHARING SYSTEM - CS-632 Assignment 5" << std::endl;
    std::cout << "========================================" << std::endl;
    
    // Create different types of rides
    std::cout << "\n--- Creating Rides ---" << std::endl;
    StandardRide ride1("R001", "123 Main St", "456 Oak Ave", 5.5);
    StandardRide ride2("R002", "789 Pinecrest Dr", "321 Panamera St", 3.2);
    PremiumRide ride3("R003", "555 Broadway", "999 48th Ave", 8.0);
    PremiumRide ride4("R004", "11 6th St", "116 Roman St", 2.5);
    
    // Demonstrate Polymorphism: Store different ride types in a vector of base class pointers
    std::cout << "\n--- Polymorphism Deom ---" << std::endl;
    std::vector<Ride*> rides;
    rides.push_back(&ride1);
    rides.push_back(&ride2);
    rides.push_back(&ride3);
    rides.push_back(&ride4);
    
    std::cout << "\n Polymorphism demo - Calling calculateFare() and rideDetails():" << std::endl;
    std::cout << "========================================" << std::endl;
    
    for (size_t i = 0; i < rides.size(); ++i) {
        std::cout << "\nProcessing Ride " << (i + 1) << ":" << std::endl;
        // Polymorphic call to calculateFare() - each subclass implements it differently
        double fare = rides[i]->calculateFare();
        std::cout << "Calculated Fare: $" << std::fixed << std::setprecision(2) 
                  << fare << std::endl;
        
        // Polymorphic call to rideDetails() - inherited method
        rides[i]->rideDetails();
    }
    
    // Create Driver and demonstrate encapsulation
    std::cout << "\n\n--- Demonstrating Encapsulation with Driver ---" << std::endl;
    Driver driver1("D001", "John Smith", 4.8);
    Driver driver2("D002", "Sarah Johnson", 4.9);
    
    // Using public method to access private assignedRides (encapsulation)
    driver1.addRide(&ride1);
    driver1.addRide(&ride3);
    driver2.addRide(&ride2);
    driver2.addRide(&ride4);
    
    driver1.getDriverInfo();
    driver2.getDriverInfo();
    
    // Create Rider and demonstrate encapsulation
    std::cout << "\n\n--- Demonstrating Encapsulation with Rider ---" << std::endl;
    Rider rider1("P001", "Alice Brown");
    Rider rider2("P002", "Bob Wilson");
    
    // Using public method to access private requestedRides (encapsulation)
    rider1.requestRide(&ride1);
    rider1.requestRide(&ride3);
    rider2.requestRide(&ride2);
    rider2.requestRide(&ride4);
    
    rider1.viewRides();
    rider2.viewRides();
    
    // Summary
    std::cout << "\n\n========================================" << std::endl;
    std::cout << "           SUMMARY" << std::endl;
    std::cout << "========================================" << std::endl;
    std::cout << "OOP Principles Demonstrated:" << std::endl;
    std::cout << "1. Encapsulation: Private members (assignedRides, requestedRides)" << std::endl;
    std::cout << "   accessed only through public methods (addRide, requestRide)" << std::endl;
    std::cout << "2. Inheritance: StandardRide and PremiumRide inherit from Ride" << std::endl;
    std::cout << "3. Polymorphism: Different ride types stored in vector<Ride*> and" << std::endl;
    std::cout << "   calculateFare() called polymorphically" << std::endl;
    std::cout << "========================================" << std::endl;
    
    return 0;
}

