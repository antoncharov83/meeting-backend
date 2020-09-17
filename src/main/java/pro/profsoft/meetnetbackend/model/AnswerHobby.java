package pro.profsoft.meetnetbackend.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnswerHobby extends BaseEntity {
    private String text;
    private Boolean isTitle = false;
    private Boolean isCheckedWant = false;
    private Boolean isCheckedCan = false;
    @ManyToOne
    @JoinColumn(name = "question_hobby_id")
    private QuestionHobby questionHobby;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getTitle() {
        return isTitle;
    }

    public void setTitle(Boolean title) {
        isTitle = title;
    }

    public Boolean getCheckedWant() {
        return isCheckedWant;
    }

    public void setCheckedWant(Boolean checkedWant) {
        isCheckedWant = checkedWant;
    }

    public Boolean getCheckedCan() {
        return isCheckedCan;
    }

    public void setCheckedCan(Boolean checkedCan) {
        isCheckedCan = checkedCan;
    }

    public QuestionHobby getQuestionHobby() {
        return questionHobby;
    }

    public void setQuestionHobby(QuestionHobby questionHobby) {
        this.questionHobby = questionHobby;
    }
}
