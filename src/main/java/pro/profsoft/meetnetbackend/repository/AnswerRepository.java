package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Answer;
import pro.profsoft.meetnetbackend.model.Question;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionPositionAndQuestion_TypeQOrderByIdAsc(Integer p, Question.TypeQ typeQ);
}
