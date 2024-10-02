package kz.kalabay.locationsystem.repositories;

import kz.kalabay.locationsystem.models.ShareLocation;
import kz.kalabay.locationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareLocationRepository extends JpaRepository<ShareLocation, Long> {

    List<ShareLocation> findShareLocationByShareFriend_Id(Long id);

    List<ShareLocation> findShareLocationByLocationOwner_Id(Long id);

    Optional<ShareLocation> findShareLocationByLocationOwner_IdAndShareFriend_Id(Long ownerId, Long shareFriendId);
}
