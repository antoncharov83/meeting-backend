package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.AnswerHobby;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.ProfileHobbyAnswer;
import pro.profsoft.meetnetbackend.model.QuestionHobby;

import java.util.Optional;

@Repository
public interface ProfileHobbyAnswerRepository extends JpaRepository<ProfileHobbyAnswer, Long> {
    Optional<ProfileHobbyAnswer> findFirstByProfileAndQuestionAndAnswer(Profile p, QuestionHobby q, AnswerHobby a);
}
