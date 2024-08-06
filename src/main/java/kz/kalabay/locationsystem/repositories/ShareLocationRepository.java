package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.ShareLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareLocationRepository extends JpaRepository<ShareLocation, Long> {
}
