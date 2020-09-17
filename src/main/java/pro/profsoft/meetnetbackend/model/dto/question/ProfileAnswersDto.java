package pro.profsoft.meetnetbackend.model.dto.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileAnswersDto {
    @JsonProperty("profileId")
    private Long profileId;
    @JsonProperty("questionId")
    private Long questionId;
    @JsonProperty("ownTextAnswer")
    private String ownTextAnswer;
    @JsonProperty("answerIds")
    private List<Long> answerIds;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOwnTextAnswer() {
        return ownTextAnswer;
    }

    public void setOwnTextAnswer(String ownTextAnswer) {
        this.ownTextAnswer = ownTextAnswer;
    }

    public List<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
    }
}
