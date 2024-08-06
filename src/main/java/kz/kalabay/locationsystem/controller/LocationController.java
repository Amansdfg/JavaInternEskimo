package kz.kalabay.locationsystem.controller;

import kz.kalabay.locationsystem.models.AccessType;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.dtos.ShareLocationDto;
import kz.kalabay.locationsystem.services.LocationService;
import kz.kalabay.locationsystem.services.ShareLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private  ShareLocationService shareLocationService;
    @Autowired
    private LocationService locationService;
    @PostMapping
    public ResponseEntity<Location> registerLocation(@RequestBody Location location) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.createLocation(location));
    }
    @PostMapping("/share")
    public ResponseEntity<ShareLocation> shareLocationWithOtherUser(@RequestBody ShareLocationDto shareLocationSto) {
        return ResponseEntity.ok( shareLocationService.shareLocationWithUser(shareLocationSto));
    }
    @GetMapping("{id}")
    public List<Location> getAllLocations(@PathVariable("id") Long id) {
        return locationService.getLocationsByUserId(id);
    }
}
