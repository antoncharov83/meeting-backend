package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.AnswerMatch;

@Repository
public interface AnswerMatchRepository extends JpaRepository<AnswerMatch, Long> {
}
