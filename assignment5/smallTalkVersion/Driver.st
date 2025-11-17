Object subclass: #Driver
	instanceVariableNames: 'driverID name rating assignedRides'
	classVariableNames: ''
	poolDictionaries: ''
	category: 'RideSharing'

!Driver class methodsFor: 'instance creation'!

new: anID name: aName rating: aRating
	"Create a new Driver instance"
	^self new
		driverID: anID;
		name: aName;
		rating: aRating;
		assignedRides: OrderedCollection new;
		yourself
!

!Driver methodsFor: 'accessing'!

driverID
	^driverID
!

driverID: anID
	driverID := anID
!

name
	^name
!

name: aName
	name := aName
!

rating
	^rating
!

rating: aRating
	rating := aRating
!

assignedRides
	^assignedRides
!

assignedRides: aCollection
	assignedRides := aCollection
!

rideCount
	^assignedRides size
!

!Driver methodsFor: 'ride management'!

addRide: aRide
	"Add a ride to the driver's assigned rides list (encapsulation)"
	aRide ifNotNil: [assignedRides add: aRide]
!

!Driver methodsFor: 'displaying'!

getDriverInfo
	"Display driver information and assigned rides"
	Transcript cr; show: '=== Driver Information ==='.
	Transcript cr; show: 'Driver ID: ', driverID.
	Transcript cr; show: 'Name: ', name.
	Transcript cr; show: 'Rating: ', rating printString, '/5.0'.
	Transcript cr; show: 'Total Rides Completed: ', assignedRides size printString.
	
	assignedRides isEmpty ifFalse: [
		Transcript cr; show: ''; show: '--- Assigned Rides ---'.
		1 to: assignedRides size do: [:index |
			Transcript cr; show: 'Ride #', index printString, ':'.
			(assignedRides at: index) rideDetails
		]
	]
!

