Object subclass: #Ride
	instanceVariableNames: 'rideID pickupLocation dropoffLocation distance fare'
	classVariableNames: ''
	poolDictionaries: ''
	category: 'RideSharing'

!Ride class methodsFor: 'instance creation'!

new: anID pickup: aPickup dropoff: aDropoff distance: aDistance
	"Create a new Ride instance"
	^self new
		rideID: anID;
		pickupLocation: aPickup;
		dropoffLocation: aDropoff;
		distance: aDistance;
		fare: 0.0;
		yourself
!

!Ride methodsFor: 'accessing'!

rideID
	^rideID
!

rideID: anID
	rideID := anID
!

pickupLocation
	^pickupLocation
!

pickupLocation: aLocation
	pickupLocation := aLocation
!

dropoffLocation
	^dropoffLocation
!

dropoffLocation: aLocation
	dropoffLocation := aLocation
!

distance
	^distance
!

distance: aDistance
	distance := aDistance
!

fare
	^fare
!

fare: aFare
	fare := aFare
!

!Ride methodsFor: 'calculations'!

calculateFare
	"Abstract method - must be overridden by subclasses"
	self subclassResponsibility
!

!Ride methodsFor: 'displaying'!

rideDetails
	"Display all ride information"
	Transcript cr; show: '=== Ride Details ==='.
	Transcript cr; show: 'Ride ID: ', rideID.
	Transcript cr; show: 'Pickup Location: ', pickupLocation.
	Transcript cr; show: 'Dropoff Location: ', dropoffLocation.
	Transcript cr; show: 'Distance: ', distance printString, ' miles'.
	Transcript cr; show: 'Fare: $', fare printString
!

