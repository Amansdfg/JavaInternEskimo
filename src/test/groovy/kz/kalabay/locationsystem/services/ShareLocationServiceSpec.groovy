package kz.kalabay.locationsystem.services

import kz.kalabay.locationsystem.models.AccessType
import kz.kalabay.locationsystem.models.Location
import kz.kalabay.locationsystem.models.ShareLocation
import kz.kalabay.locationsystem.models.User
import kz.kalabay.locationsystem.models.dtos.ShareLocationDto
import kz.kalabay.locationsystem.models.dtos.ShareLocationFriend
import kz.kalabay.locationsystem.repositories.LocationRepository
import kz.kalabay.locationsystem.repositories.ShareLocationRepository
import kz.kalabay.locationsystem.repositories.UserRepository
import spock.lang.Specification
class ShareLocationServiceSpec extends Specification{
    ShareLocationRepository shareLocationRepository=Mock()
    LocationRepository locationRepository =Mock()
    UserRepository userRepository=Mock()
    ShareLocationService shareLocationService =new ShareLocationService(
            shareLocationRepository:shareLocationRepository,
            locationRepository:locationRepository,
            userRepository:userRepository
    )
    def "should share location with user"(){
        given:
        Long ownerId=1L
        Long friendId=2L
        Location location=new Location(id: 1L, address: "Estemesove 97",owner:new User(id:ownerId))
        User friend=new User(id: friendId)
        ShareLocationDto dto=new ShareLocationDto(ownerId: ownerId,friendId:friendId,accessType:AccessType.READ_ONLY)
        ShareLocation shareLocation=ShareLocation.builder()
                .location(location)
                .shareFriend(friend)
                .accessType(AccessType.READ_ONLY)
                .build()
        locationRepository.findLocationByOwner_Id(ownerId)>>Optional.of(location)
        userRepository.findById(friendId)>>Optional.of(friend)
        shareLocationRepository.save(shareLocation)>>shareLocation
        when:
        ShareLocation result=shareLocationService.shareLocationWithUser(dto)
        then:
        result.location==location
        result.shareFriend==friend
        result.accessType==AccessType.READ_ONLY
    }
    def "should return users who have shared locations"(){
        given:
        Long locationId=1L
        User user =new User(id: 2L)
        Location location =new Location(id:locationId)
        ShareLocation shareLocation =ShareLocation.builder()
                .location(location)
                .shareFriend(user)
                .accessType(AccessType.READ_ONLY)
                .build()
        List<ShareLocation> shareLocations=[shareLocation]
        shareLocationRepository.findShareLocationByLocationOwner_Id(locationId)>>shareLocations
        when:
        List<User> result=shareLocationService.getSharedUsers(locationId)
        then:
        result.size()==1
        result[0]==user
    }
    def "should return all shared locations"(){
        given:
        Location location1 = new Location(id: 1L, address: "Estemesova 97")
        Location location2 = new Location(id: 2L, address: "Estemesova 98")
        ShareLocation shareLocation1 =ShareLocation.builder().location(location1).build()
        ShareLocation shareLocation2 =ShareLocation.builder().location(location2).build()
        List<ShareLocation> shareLocations =[shareLocation1,shareLocation2]
        shareLocationRepository.findAll() >>shareLocations
        when:
        List<ShareLocation> result=shareLocationService.getSharedLocations()
        then:
        result.size()==2
        result.contains(shareLocation1)
        result.contains(shareLocation2)
    }

    def "should return shared locations by user id"(){
        given:
        Long userId=1L
        Location location1 =new Location(id:1L,address:"123 Main St")
        Location location2 =new Location(id:2L,address:"456 Elm St")
        ShareLocation shareLocation1 =ShareLocation.builder().location(location1).build()
        ShareLocation shareLocation2 =ShareLocation.builder().location(location2).build()
        List<ShareLocation> shareLocations=[shareLocation1,shareLocation2]

        shareLocationRepository.findShareLocationByLocationOwner_Id(userId)>>shareLocations
        when:
        List<ShareLocation> result=shareLocationService.getSharedLocationsByUserId(userId)
        then:
        result.size()==2
        result.contains(shareLocation1)
        result.contains(shareLocation2)
    }
    def "should get share location by id and update access if admin"(){
        given:
        ShareLocationFriend shareLocationFriend=new ShareLocationFriend(ownerId:1L,friendId:2L,toId:3L)
        User owner =new User(id:1L)
        User friend=new User(id:2L)
        User newFriend =new User(id:3L)
        Location location=new Location(id:1L,address:"Estemesova 97",owner: owner)
        ShareLocation shareLocation =ShareLocation.builder()
                .location(location)
                .shareFriend(friend)
                .accessType(AccessType.ADMIN)
                .build()
        ShareLocationDto dto =new ShareLocationDto(ownerId: 1L, friendId: 2L, accessType: AccessType.READ_ONLY)
        locationRepository.findLocationByOwner_Id(1L) >> Optional.of(location)
        userRepository.findById(2L) >> Optional.of(friend)
        userRepository.findById(3L) >> Optional.of(newFriend)
        shareLocationRepository.findShareLocationByLocationOwner_IdAndShareFriend_Id(1L, 2L)>>Optional.of(shareLocation)
        shareLocationRepository.save(_)>>{ ShareLocation updatedShareLocation->
            assert updatedShareLocation.accessType==AccessType.READ_ONLY
            return updatedShareLocation
        }
        when:
        ShareLocation result=shareLocationService.getShareLocationById(shareLocationFriend)
        then:
        result.accessType == AccessType.READ_ONLY
    }

}
