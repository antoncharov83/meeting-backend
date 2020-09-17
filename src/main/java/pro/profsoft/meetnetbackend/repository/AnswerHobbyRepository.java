package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.AnswerHobby;

@Repository
public interface AnswerHobbyRepository extends JpaRepository<AnswerHobby, Long> {
}
