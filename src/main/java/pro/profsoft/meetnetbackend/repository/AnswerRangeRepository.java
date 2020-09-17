package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.AnswerRange;

@Repository
public interface AnswerRangeRepository extends JpaRepository<AnswerRange, Long> {
}
