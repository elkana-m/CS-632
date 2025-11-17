#ifndef RIDER_H
#define RIDER_H

#include "Ride.h"
#include <vector>
#include <string>

class Rider {
private:
    std::string riderID;
    std::string name;
    std::vector<Ride*> requestedRides; // Private - encapsulation

public:
    // Constructor
    Rider(const std::string& id, const std::string& riderName);
    
    // Destructor
    ~Rider();
    
    // Method to request a ride
    void requestRide(Ride* ride);
    
    // Method to view all requested rides
    void viewRides() const;
    
    // Getters
    std::string getRiderID() const { return riderID; }
    std::string getName() const { return name; }
    int getRideCount() const { return requestedRides.size(); }
};

#endif // RIDER_H

