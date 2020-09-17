package pro.profsoft.meetnetbackend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AnswerMatch {
    @EmbeddedId
    private CompositeAnswerPK id = new CompositeAnswerPK();
    @MapsId("answerId")
    @ManyToOne
    private Answer answer;
    @MapsId("matchAnswerId")
    @ManyToOne
    private Answer matchAnswer;

    public AnswerMatch() {
    }

    public AnswerMatch(Answer answer, Answer matchAnswer) {
        this.answer = answer;
        this.matchAnswer = matchAnswer;
    }

    public CompositeAnswerPK getId() {
        return id;
    }

    public void setId(CompositeAnswerPK id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getMatchAnswer() {
        return matchAnswer;
    }

    public void setMatchAnswer(Answer matchAnswer) {
        this.matchAnswer = matchAnswer;
    }
}

@Embeddable
class CompositeAnswerPK implements Serializable {
    private Long answerId;
    private Long matchAnswerId;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getMatchAnswerId() {
        return matchAnswerId;
    }

    public void setMatchAnswerId(Long matchAnswerId) {
        this.matchAnswerId = matchAnswerId;
    }
}