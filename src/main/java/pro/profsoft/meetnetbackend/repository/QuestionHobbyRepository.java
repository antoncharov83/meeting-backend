package pro.profsoft.meetnetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.profsoft.meetnetbackend.model.QuestionHobby;

import java.util.List;

@Repository
public interface QuestionHobbyRepository extends JpaRepository<QuestionHobby, Long> {
    @Query("select qh from QuestionHobby qh left join fetch qh.answers")
    List<QuestionHobby> getAllWithAnswers();
    @Query("SELECT distinct qh FROM QuestionHobby qh LEFT JOIN FETCH qh.profileHobbyAnswers pa" +
            " WHERE pa.profile.id <> ?1 OR pa.question.id <> qh.id OR pa.question IS NULL ORDER BY qh.position ASC")
    List<QuestionHobby> getAllByProfile(Long id);
}
