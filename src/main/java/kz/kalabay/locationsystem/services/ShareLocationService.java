package kz.kalabay.locationsystem.services;

import kz.kalabay.locationsystem.models.AccessType;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.models.dtos.ShareLocationDto;
import kz.kalabay.locationsystem.models.dtos.ShareLocationFriend;
import kz.kalabay.locationsystem.repositories.LocationRepository;
import kz.kalabay.locationsystem.repositories.ShareLocationRepository;
import kz.kalabay.locationsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShareLocationService {

    private final ShareLocationRepository shareLocationRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public ShareLocation shareLocationWithUser(ShareLocationDto shareLocationDto) {

        Location ownerLocation =
            locationRepository.findLocationByOwner_Id(shareLocationDto.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found"));
        User share = userRepository.findById(shareLocationDto.getFriendId()).orElseThrow(() -> new RuntimeException(
            "Location not found"));
        ShareLocation shareLocation = ShareLocation.builder()
            .shareFriend(share)
            .location(ownerLocation)
            .accessType(shareLocationDto.getAccessType())
            .build();
        return shareLocationRepository.save(shareLocation);
    }

    public List<User> getSharedUsers(Long id) {

        List<ShareLocation> sh = shareLocationRepository.findShareLocationByLocationOwner_Id(id);
        List<User> users = new ArrayList<>();
        for (ShareLocation shareLocation : sh) {
            users.add(shareLocation.getShareFriend());
        }
        return users;
    }

    public List<ShareLocation> getSharedLocations() {

        return shareLocationRepository.findAll();
    }

    public List<ShareLocation> getSharedLocationsByUserId(Long id) {

        return shareLocationRepository.findShareLocationByLocationOwner_Id(id);
    }

    public ShareLocation getShareLocationById(ShareLocationFriend shareLocationFriend) {

        ShareLocation shareLocation =
            shareLocationRepository.findShareLocationByLocationOwner_IdAndShareFriend_Id(shareLocationFriend.getOwnerId(), shareLocationFriend.getFriendId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (shareLocation.getAccessType().equals(AccessType.ADMIN)) {
            ShareLocationDto shareLocationDto = new ShareLocationDto();
            shareLocationDto.setAccessType(AccessType.READ_ONLY);
            shareLocationDto.setFriendId(shareLocationFriend.getToId());
            shareLocationDto.setOwnerId(shareLocationFriend.getOwnerId());
//            shareLocationWithUser(shareLocationDto);
//            return shareLocation;
            Location ownerLocation =
                locationRepository.findLocationByOwner_Id(shareLocationDto.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found"));
            User share =
                userRepository.findById(shareLocationDto.getFriendId()).orElseThrow(() -> new RuntimeException(
                    "Location not found"));
            ShareLocation shareLocationa = ShareLocation.builder()
                .shareFriend(share)
                .location(ownerLocation)
                .accessType(shareLocationDto.getAccessType())
                .build();
            return shareLocationRepository.save(shareLocationa);
        } else {
            throw new RuntimeException("Don't have access to this friend");
        }
    }
}
