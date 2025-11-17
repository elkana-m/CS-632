#include "Rider.h"
#include <iostream>

Rider::Rider(const std::string& id, const std::string& riderName)
    : riderID(id), name(riderName) {
}

Rider::~Rider() {
}

void Rider::requestRide(Ride* ride) {
    if (ride != nullptr) {
        requestedRides.push_back(ride);
    }
}

void Rider::viewRides() const {
    std::cout << "\n=== Ride History for " << name << " ===" << std::endl;
    std::cout << "Rider ID: " << riderID << std::endl;
    std::cout << "Total Rides Requested: " << requestedRides.size() << std::endl;
    
    if (!requestedRides.empty()) {
        std::cout << "\n--- Requested Rides ---" << std::endl;
        for (size_t i = 0; i < requestedRides.size(); ++i) {
            std::cout << "\nRide #" << (i + 1) << ":" << std::endl;
            requestedRides[i]->rideDetails();
        }
    } else {
        std::cout << "No rides requested yet." << std::endl;
    }
}

