package kz.kalabay.locationsystem.services
import kz.kalabay.locationsystem.models.AccessType

import kz.kalabay.locationsystem.models.Location
import kz.kalabay.locationsystem.models.ShareLocation
import kz.kalabay.locationsystem.models.User
import kz.kalabay.locationsystem.repositories.LocationRepository
import kz.kalabay.locationsystem.repositories.ShareLocationRepository
import spock.lang.Specification

class LocationServiceSpec extends Specification {
    LocationRepository locationRepository=Mock()
    ShareLocationRepository shareLocationRepository=Mock()
    LocationService locationService =new LocationService(locationRepository:locationRepository,shareLocationRepository:shareLocationRepository)
    def "should create a location"(){
        given:
        Location location=new Location(id:1L,address:"Estemesova 97",owner: new User(id:1L))
        locationRepository.save(location)>>location
        when:
        Location result=locationService.createLocation(location)
        then:
        result.id==1L
        result.address=="Estemesova 97"
    }
    def "should return locations by owner id"(){
        given:
        Long ownerId=1L
        User owner =new User(id:ownerId)
        Location location= new Location(id:1L,address: "Estemesova 97", owner: owner)
        List<Location> locations=[location]
        locationRepository.findLocationsByOwner_Id(ownerId)>> Optional.of(locations)
        when:
        List<Location> result= locationService.getLocationsByOwnerId(ownerId)
        then:
        result.size()==1
        result[0].address=="Estemesova 97"
    }

    def "should return locations by user id, including shared and owned"(){
        given:
        Long userId=1L
        User user=new User(id: userId)
        Location ownedLocation=new Location(id: 1L, address: "Estemesova 97",owner:user)
        Location sharedLocation=new Location(id: 2L, address: "Estemesova 98",owner:new User(id:2L))
        ShareLocation shareLocation=new ShareLocation(id:2L,location:sharedLocation,shareFriend:user,accessType:AccessType.READ_ONLY)
        List<ShareLocation> shareLocations=[shareLocation]
        List<Location> ownedLocations=[ownedLocation]
        shareLocationRepository.findShareLocationByShareFriend_Id(userId)>>shareLocations
        locationRepository.findLocationsByOwner_Id(userId)>>Optional.of(ownedLocations)
        when:
        List<Location> result=locationService.getLocationsByUserId(userId)
        then:
        result.size()==2
        result.find {it.address=="Estemesova 97"}
        result.find {it.address =="Estemesova 98"}
    }
}