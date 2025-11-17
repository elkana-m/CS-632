Ride subclass: #StandardRide
	instanceVariableNames: ''
	classVariableNames: 'StandardRatePerMile'
	poolDictionaries: ''
	category: 'RideSharing'

!StandardRide class methodsFor: 'initialization'!

initialize
	"Initialize class variables"
	StandardRatePerMile := 1.50
!

!StandardRide class methodsFor: 'instance creation'!

new: anID pickup: aPickup dropoff: aDropoff distance: aDistance
	"Create a new StandardRide instance"
	^super new: anID pickup: aPickup dropoff: aDropoff distance: aDistance
!

!StandardRide methodsFor: 'calculations'!

calculateFare
	"Calculate fare using standard rate per mile"
	fare := distance * StandardRatePerMile.
	^fare
!

