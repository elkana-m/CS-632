#include "StandardRide.h"

const double StandardRide::STANDARD_RATE_PER_MILE = 1.50;

StandardRide::StandardRide(const std::string& id, const std::string& pickup, 
                           const std::string& dropoff, double dist)
    : Ride(id, pickup, dropoff, dist) {
    fare = calculateFare();
}

double StandardRide::calculateFare() {
    fare = distance * STANDARD_RATE_PER_MILE;
    return fare;
}

