package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
