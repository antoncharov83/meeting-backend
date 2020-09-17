package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @EntityGraph(value = "question-with-answers", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT q FROM Question  q")
    List<Question> getAll();

//    @Query("SELECT distinct q FROM Question q LEFT JOIN FETCH q.profileAnswers pa" +
//            " WHERE (pa.profile.id <> ?1 OR pa.question.id <> q.id OR pa.question IS NULL) AND q.typeQ = ?2 ORDER BY q.position ASC")
//    List<Question> getAllByProfile(Long id, Question.TypeQ typeQ);

    @Query("SELECT distinct q FROM Question q " +
            " WHERE q.id NOT IN (SELECT distinct pa.question.id FROM ProfileAnswer pa WHERE pa.profile.id = ?1)" +
            " AND q.typeQ = ?2 ORDER BY q.position ASC")
    List<Question> getAllByProfile(Long id, Question.TypeQ typeQ);

    @Query("SELECT distinct q FROM Question q " +
            " WHERE q.id NOT IN (SELECT distinct pa.question.id FROM ProfileAnswer pa WHERE pa.profile.id = ?1)" +
            " AND q.typeQ = ?2 AND q.position > ?3 ORDER BY q.position ASC")
    Question getNextQuestion(Long id, Question.TypeQ typeQ, Integer position);

    @Query("SELECT COUNT(q) FROM Question q " +
            " WHERE q.id NOT IN (SELECT distinct pa.question.id FROM ProfileAnswer pa WHERE pa.profile.id = ?1) AND q.typeQ = ?2")
    Integer getUnansweredCount(Long id, Question.TypeQ typeQ);

    @Query("SELECT COUNT(q) FROM Question q WHERE q.typeQ = ?1")
    Integer getCount(Question.TypeQ typeQ);
}
