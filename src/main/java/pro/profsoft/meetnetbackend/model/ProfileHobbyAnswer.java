package pro.profsoft.meetnetbackend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProfileHobbyAnswer {
    @EmbeddedId
    private ProfileAnswerHobbyPK id = new ProfileAnswerHobbyPK();
    @ManyToOne
    @MapsId("questionHobbyId")
    private QuestionHobby question;
    @MapsId("profileId")
    @ManyToOne
    private Profile profile;
    @MapsId("answerHobbyId")
    @ManyToOne
    private AnswerHobby answer;
    private Boolean isCheckedWant;
    private Boolean isCheckedCan;

    public ProfileHobbyAnswer() {
    }

    public ProfileHobbyAnswer(Profile profile, QuestionHobby question, AnswerHobby answer) {
        this.question = question;
        this.profile = profile;
        this.answer = answer;
    }

    public ProfileAnswerHobbyPK getId() {
        return id;
    }

    public void setId(ProfileAnswerHobbyPK id) {
        this.id = id;
    }

    public QuestionHobby getQuestion() {
        return question;
    }

    public void setQuestion(QuestionHobby question) {
        this.question = question;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public AnswerHobby getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerHobby answer) {
        this.answer = answer;
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
}

@Embeddable
class ProfileAnswerHobbyPK implements Serializable {
    private Long questionHobbyId;
    private Long profileId;
    private Long answerHobbyId;

    public Long getQuestionHobbyId() {
        return questionHobbyId;
    }

    public void setQuestionHobbyId(Long questionHobbyId) {
        this.questionHobbyId = questionHobbyId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getAnswerHobbyId() {
        return answerHobbyId;
    }

    public void setAnswerHobbyId(Long answerHobbyId) {
        this.answerHobbyId = answerHobbyId;
    }
}
