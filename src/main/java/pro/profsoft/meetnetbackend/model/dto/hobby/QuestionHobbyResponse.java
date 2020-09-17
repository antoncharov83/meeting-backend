package pro.profsoft.meetnetbackend.model.dto.hobby;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionHobbyResponse {
    @JsonProperty("questions")
    private List<QuestionHobbyDto> questions;
    @JsonProperty("maxAnswersCount")
    private Integer maxAnswersCount;

    public List<QuestionHobbyDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionHobbyDto> questions) {
        this.questions = questions;
    }

    public Integer getMaxAnswersCount() {
        return maxAnswersCount;
    }

    public void setMaxAnswersCount(Integer maxAnswersCount) {
        this.maxAnswersCount = maxAnswersCount;
    }
}
