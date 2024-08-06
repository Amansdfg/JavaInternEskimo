package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
