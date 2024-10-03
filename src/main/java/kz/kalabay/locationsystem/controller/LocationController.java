package kz.kalabay.locationsystem.controller;

import kz.kalabay.locationsystem.models.AccessType;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.dtos.ShareLocationDto;
import kz.kalabay.locationsystem.services.LocationService;
import kz.kalabay.locationsystem.services.ShareLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {
    private final ShareLocationService shareLocationService;
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<?> registerLocation(@RequestBody Location location) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(locationService.createLocation(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/share")
    public ResponseEntity<ShareLocation> shareLocationWithOtherUser(@RequestBody ShareLocationDto shareLocationSto) {

        return ResponseEntity.ok(shareLocationService.shareLocationWithUser(shareLocationSto));
    }

    @GetMapping("{id}")
    public List<Location> getAllLocations(@PathVariable("id") Long id) {

        return locationService.getLocationsByUserId(id);
    }
    @GetMapping("/all")
    public List<Location> getAllLocations() {
        return locationService.getLocations();
    }
}
