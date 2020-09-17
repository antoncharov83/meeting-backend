package pro.profsoft.meetnetbackend.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileAsap;

@Repository
public interface ProfileAsapRepository extends JpaRepository<ProfileAsap, Long> {
    @Query("SELECT count(p) FROM ProfileAsap p WHERE p.dateMessage >= ?3 AND p.dateMessage < ?4 " +
            "AND p.senderProfile = ?1 AND p.targetProfile <> ?2")
    Integer countAsapMessageToday(Profile sender, Profile target, DateTime start, DateTime end);
}
