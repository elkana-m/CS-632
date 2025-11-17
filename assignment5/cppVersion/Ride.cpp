#include "Ride.h"
#include <iostream>
#include <iomanip>

Ride::Ride(const std::string& id, const std::string& pickup, 
           const std::string& dropoff, double dist)
    : rideID(id), pickupLocation(pickup), dropoffLocation(dropoff), 
      distance(dist), fare(0.0) {
}

Ride::~Ride() {
    // Virtual destructor
}

void Ride::rideDetails() const {
    std::cout << "\n=== Ride Details ===" << std::endl;
    std::cout << "Ride ID: " << rideID << std::endl;
    std::cout << "Pickup Location: " << pickupLocation << std::endl;
    std::cout << "Dropoff Location: " << dropoffLocation << std::endl;
    std::cout << "Distance: " << std::fixed << std::setprecision(2) 
              << distance << " miles" << std::endl;
    std::cout << "Fare: $" << std::fixed << std::setprecision(2) 
              << fare << std::endl;
}

