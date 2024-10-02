package kz.kalabay.locationsystem.services;

import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.repositories.LocationRepository;
import kz.kalabay.locationsystem.repositories.ShareLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final ShareLocationRepository shareLocationRepository;
    private final LocationRepository locationRepository;

    @CachePut(value = "locations", key = "#location.owner.id")
    public Location createLocation(Location location) {

        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        return locationRepository.save(location);
    }

    @Cacheable(value = "locations", key = "#ownerId")
    public Location getLocationsByOwnerId(Long ownerId) {

        return locationRepository.findLocationByOwner_Id(ownerId).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Cacheable(value = "userLocations", key = "#userId")
    public List<Location> getLocationsByUserId(Long userId) {

        List<Location> locations = new ArrayList<>();
        List<ShareLocation> shareLocations = shareLocationRepository.findShareLocationByShareFriend_Id(userId);
        for (ShareLocation shareLocation : shareLocations) {
            locations.add(shareLocation.getLocation());
        }
        List<Location> lo =
            locationRepository.findLocationsByOwner_Id(userId).orElseThrow(() -> new RuntimeException("Not found"));
        for (Location location : lo) {
            locations.add(location);
        }
        return locations;
    }
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }
    @CacheEvict(value = "locations", allEntries = true)
    public void clearCache() {

    }
}
