Object subclass: #Rider
	instanceVariableNames: 'riderID name requestedRides'
	classVariableNames: ''
	poolDictionaries: ''
	category: 'RideSharing'

!Rider class methodsFor: 'instance creation'!

new: anID name: aName
	"Create a new Rider instance"
	^self new
		riderID: anID;
		name: aName;
		requestedRides: OrderedCollection new;
		yourself
!

!Rider methodsFor: 'accessing'!

riderID
	^riderID
!

riderID: anID
	riderID := anID
!

name
	^name
!

name: aName
	name := aName
!

requestedRides
	^requestedRides
!

requestedRides: aCollection
	requestedRides := aCollection
!

rideCount
	^requestedRides size
!

!Rider methodsFor: 'ride management'!

requestRide: aRide
	"Add a ride to the rider's requested rides list (encapsulation)"
	aRide ifNotNil: [requestedRides add: aRide]
!

!Rider methodsFor: 'displaying'!

viewRides
	"Display all requested rides"
	Transcript cr; show: '=== Ride History for ', name, ' ==='.
	Transcript cr; show: 'Rider ID: ', riderID.
	Transcript cr; show: 'Total Rides Requested: ', requestedRides size printString.
	
	requestedRides isEmpty ifFalse: [
		Transcript cr; show: ''; show: '--- Requested Rides ---'.
		1 to: requestedRides size do: [:index |
			Transcript cr; show: 'Ride #', index printString, ':'.
			(requestedRides at: index) rideDetails
		]
	] ifTrue: [
		Transcript cr; show: 'No rides requested yet.'
	]
!

