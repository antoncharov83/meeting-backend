package pro.profsoft.meetnetbackend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProfileAnswer {
    @EmbeddedId
    private ProfileAnswerPK id = new ProfileAnswerPK();
    @ManyToOne
    @MapsId("questionId")
    private Question question;
    @MapsId("profileId")
    @ManyToOne
    private Profile profile;
    @MapsId("answerId")
    @ManyToOne
    private Answer answer;
    // для вопросов с rangeBar
    private Integer minRange;
    private Integer maxRange;
    private Integer minCurrentRange;
    private Integer maxCurrentRange;
    // для вопросов с ручным вводом
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String textEdit;

    public ProfileAnswer() {
    }

    public ProfileAnswer(Question question, Profile profile, Answer answer) {
        this.question = question;
        this.profile = profile;
        this.answer = answer;
    }

    public ProfileAnswerPK getId() {
        return id;
    }

    public void setId(ProfileAnswerPK id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

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

    public String getTextEdit() {
        return textEdit;
    }

    public void setTextEdit(String textEdit) {
        this.textEdit = textEdit;
    }
}

@Embeddable
class ProfileAnswerPK implements Serializable {
    private Long questionId;
    private Long profileId;
    private Long answerId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
