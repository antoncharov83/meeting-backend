package pro.profsoft.meetnetbackend.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnswerRange extends BaseEntity{
    private Integer minRange;
    private Integer maxRange;
    private Integer minCurrentRange;
    private Integer maxCurrentRange;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public Integer getMinCurrentRange() {
        return minCurrentRange;
    }

    public void setMinCurrentRange(Integer minCurrentRange) {
        this.minCurrentRange = minCurrentRange;
    }

    public Integer getMaxCurrentRange() {
        return maxCurrentRange;
    }

    public void setMaxCurrentRange(Integer maxCurrentRange) {
        this.maxCurrentRange = maxCurrentRange;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
