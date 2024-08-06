package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.Location;
import kz.kalabay.locationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
