package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Answer;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileAnswer;
import pro.profsoft.meetnetbackend.model.Question;

import java.util.Optional;

@Repository
public interface ProfileAnswerRepository extends JpaRepository<ProfileAnswer, Long> {
    Optional<ProfileAnswer> findFirstByProfileAndAnswerAndQuestion(Profile p, Answer a, Question q);
    @Query("select pa from ProfileAnswer pa where pa.question.position = 3 and pa.question.typeQ = 0 and pa.profile.id = ?1")
    Optional<ProfileAnswer> getSex(Long id);
}
