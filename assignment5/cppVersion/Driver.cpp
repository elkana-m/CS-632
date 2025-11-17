#include "Driver.h"
#include <iostream>
#include <iomanip>

Driver::Driver(const std::string& id, const std::string& driverName, double driverRating)
    : driverID(id), name(driverName), rating(driverRating) {
}

Driver::~Driver() {
    // the rides are not delted here as they may be owned by other objects.
    // So ownership semantics are not clear at the moment.
}

void Driver::addRide(Ride* ride) {
    if (ride != nullptr) {
        assignedRides.push_back(ride);
    }
}

void Driver::getDriverInfo() const {
    std::cout << "\n=== Driver Information ===" << std::endl;
    std::cout << "Driver ID: " << driverID << std::endl;
    std::cout << "Name: " << name << std::endl;
    std::cout << "Rating: " << std::fixed << std::setprecision(1) 
              << rating << "/5.0" << std::endl;
    std::cout << "Total Rides Completed: " << assignedRides.size() << std::endl;
    
    if (!assignedRides.empty()) {
        std::cout << "\n--- Assigned Rides ---" << std::endl;
        for (size_t i = 0; i < assignedRides.size(); ++i) {
            std::cout << "\nRide #" << (i + 1) << ":" << std::endl;
            assignedRides[i]->rideDetails();
        }
    }
}

