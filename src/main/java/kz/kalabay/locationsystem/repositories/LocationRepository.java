package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<List<Location>> findLocationsByOwner_Id(Long ownerId);

    Optional<Location> findLocationByOwner_Id(Long id);
}
