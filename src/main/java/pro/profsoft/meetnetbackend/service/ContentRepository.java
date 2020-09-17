package pro.profsoft.meetnetbackend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Content;
import pro.profsoft.meetnetbackend.model.Profile;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
//    @Query("DELETE FROM Content WHERE profile.id = ?1")
    void deleteAllByProfile(Profile p);
}
