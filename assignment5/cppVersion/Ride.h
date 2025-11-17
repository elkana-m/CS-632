#ifndef RIDE_H
#define RIDE_H

#include <string>

class Ride {
protected:
    std::string rideID;
    std::string pickupLocation;
    std::string dropoffLocation;
    double distance;
    double fare;

public:
    // Constructor
    Ride(const std::string& id, const std::string& pickup, 
         const std::string& dropoff, double dist);
    
    // Virtual destructor for proper polymorphic cleanup
    virtual ~Ride();
    
    // Virtual method for calculating fare (to be overridden by subclasses)
    virtual double calculateFare() = 0;
    
    // Method to display ride details
    void rideDetails() const;
    
    // Getters
    std::string getRideID() const { return rideID; }
    std::string getPickupLocation() const { return pickupLocation; }
    std::string getDropoffLocation() const { return dropoffLocation; }
    double getDistance() const { return distance; }
    double getFare() const { return fare; }
    
    // Setter for fare (used after calculation)
    void setFare(double f) { fare = f; }
};

#endif // RIDE_H

