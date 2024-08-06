package kz.kalabay.locationsystem.services;
import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.User;
import kz.kalabay.locationsystem.repositories.LocationRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    public Location registerUser(Location location) {
        return locationRepository.save(location);
    }
}
