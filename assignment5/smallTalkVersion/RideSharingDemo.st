"Ride Sharing System Demonstration Script"
"Demonstrates Encapsulation, Inheritance, and Polymorphism"

| ride1 ride2 ride3 ride4 rides driver1 driver2 rider1 rider2 |

Transcript clear.
Transcript cr; show: '========================================'.
Transcript cr; show: '   RIDE SHARING SYSTEM DEMONSTRATION'.
Transcript cr; show: '========================================'.

"Initialize class variables"
StandardRide initialize.
PremiumRide initialize.

"Create different types of rides"
Transcript cr; show: ''; show: '--- Creating Rides ---'.
ride1 := StandardRide new: 'R001' pickup: '123 Main St' dropoff: '456 Oak Ave' distance: 5.5.
ride2 := StandardRide new: 'R002' pickup: '789 Pine Rd' dropoff: '321 Elm St' distance: 3.2.
ride3 := PremiumRide new: 'R003' pickup: '555 Broadway' dropoff: '999 Park Ave' distance: 8.0.
ride4 := PremiumRide new: 'R004' pickup: '111 First St' dropoff: '222 Second St' distance: 2.5.

"Demonstrate Polymorphism: Store different ride types in an OrderedCollection"
Transcript cr; show: ''; show: '--- Demonstrating Polymorphism ---'.
rides := OrderedCollection new.
rides add: ride1.
rides add: ride2.
rides add: ride3.
rides add: ride4.

Transcript cr; show: ''; show: 'Calling calculateFare() and rideDetails() polymorphically:'.
Transcript cr; show: '========================================'.

"Polymorphic iteration - each ride type implements calculateFare differently"
1 to: rides size do: [:index |
	Transcript cr; show: ''; show: 'Processing Ride ', index printString, ':'.
	| fare |
	fare := (rides at: index) calculateFare.
	Transcript cr; show: 'Calculated Fare: $', fare printString.
	(rides at: index) rideDetails
].

"Create Driver and demonstrate encapsulation"
Transcript cr; show: ''; show: ''; show: '--- Demonstrating Encapsulation with Driver ---'.
driver1 := Driver new: 'D001' name: 'John Smith' rating: 4.8.
driver2 := Driver new: 'D002' name: 'Sarah Johnson' rating: 4.9.

"Using public method to access private assignedRides (encapsulation)"
driver1 addRide: ride1.
driver1 addRide: ride3.
driver2 addRide: ride2.
driver2 addRide: ride4.

driver1 getDriverInfo.
driver2 getDriverInfo.

"Create Rider and demonstrate encapsulation"
Transcript cr; show: ''; show: ''; show: '--- Demonstrating Encapsulation with Rider ---'.
rider1 := Rider new: 'P001' name: 'Alice Brown'.
rider2 := Rider new: 'P002' name: 'Bob Wilson'.

"Using public method to access private requestedRides (encapsulation)"
rider1 requestRide: ride1.
rider1 requestRide: ride3.
rider2 requestRide: ride2.
rider2 requestRide: ride4.

rider1 viewRides.
rider2 viewRides.

"Summary"
Transcript cr; show: ''; show: ''; show: '========================================'.
Transcript cr; show: '           SUMMARY'.
Transcript cr; show: '========================================'.
Transcript cr; show: 'OOP Principles Demonstrated:'.
Transcript cr; show: '1. Encapsulation: Private members (assignedRides, requestedRides)'.
Transcript cr; show: '   accessed only through public methods (addRide:, requestRide:)'.
Transcript cr; show: '2. Inheritance: StandardRide and PremiumRide inherit from Ride'.
Transcript cr; show: '3. Polymorphism: Different ride types stored in OrderedCollection and'.
Transcript cr; show: '   calculateFare called polymorphically'.
Transcript cr; show: '========================================'.

