package pro.profsoft.meetnetbackend.repository;

import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Profile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.profileAnswers WHERE p.id = ?1")
    Optional<Profile> getAllWithProfileAnswers(Long id);
    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.content")
    List<Profile> getAllWithContent();
    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.content WHERE p.id =?1")
    Optional<Profile> getByIdWithContent(Long id);
    @Query("SELECT distinct p FROM Profile p LEFT JOIN FETCH p.relationshipsDislike r LEFT JOIN FETCH p.content WHERE (r.ownerProfile.id <> ?1 OR " +
            "r.dislikeProfile.id <> p.id OR r.dateDislike < ?2 or r.ownerProfile IS NULL) AND p.id <> ?1")
    List<Profile> getNextPaginated(Long ownerId, DateTime dateEndBlock, Pageable p);
}
