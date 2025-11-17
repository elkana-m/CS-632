Ride subclass: #PremiumRide
	instanceVariableNames: ''
	classVariableNames: 'PremiumRatePerMile'
	poolDictionaries: ''
	category: 'RideSharing'

!PremiumRide class methodsFor: 'initialization'!

initialize
	"Initialize class variables"
	PremiumRatePerMile := 2.50
!

!PremiumRide class methodsFor: 'instance creation'!

new: anID pickup: aPickup dropoff: aDropoff distance: aDistance
	"Create a new PremiumRide instance"
	^super new: anID pickup: aPickup dropoff: aDropoff distance: aDistance
!

!PremiumRide methodsFor: 'calculations'!

calculateFare
	"Calculate fare using premium rate per mile"
	fare := distance * PremiumRatePerMile.
	^fare
!

