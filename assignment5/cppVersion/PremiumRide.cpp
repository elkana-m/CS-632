#include "PremiumRide.h"

const double PremiumRide::PREMIUM_RATE_PER_MILE = 2.50;

PremiumRide::PremiumRide(const std::string& id, const std::string& pickup, 
                         const std::string& dropoff, double dist)
    : Ride(id, pickup, dropoff, dist) {
    fare = calculateFare();
}

double PremiumRide::calculateFare() {
    fare = distance * PREMIUM_RATE_PER_MILE;
    return fare;
}

