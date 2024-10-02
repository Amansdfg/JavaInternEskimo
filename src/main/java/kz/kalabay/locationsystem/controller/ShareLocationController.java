package kz.kalabay.locationsystem.controller;

import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.models.dtos.ShareLocationFriend;
import kz.kalabay.locationsystem.services.ShareLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sharedLocation")
@RequiredArgsConstructor
public class ShareLocationController {

    private final ShareLocationService shareLocationService;

    @GetMapping("/{id}/friends")
    public List<User> shareLocation(@PathVariable("id") Long userId) {

        return shareLocationService.getSharedUsers(userId);
    }

    @GetMapping("/all")
    public List<ShareLocation> getAllShareLocations() {

        return shareLocationService.getSharedLocations();
    }

    @GetMapping("/{id}/sharedFriends")
    public List<ShareLocation> getSharedFriends(@PathVariable("id") Long userId) {

        return shareLocationService.getSharedLocationsByUserId(userId);
    }

    @PostMapping("/shareFriendsLocation")
    public ResponseEntity<?> getShareFriendsLocation(@RequestBody ShareLocationFriend shareLocationFriend) {

        try {
            return ResponseEntity.ok(shareLocationService.getShareLocationById(shareLocationFriend));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
