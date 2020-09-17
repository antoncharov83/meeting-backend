package pro.profsoft.meetnetbackend.model.dto.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import pro.profsoft.meetnetbackend.model.Question;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "position",
        "questionType",
        "title",
        "subtitle",
        "isImportant",
        "maxAnswerCount",
        "typeQ",
        "answers"
})
public class QuestionDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("questionType")
    private Integer questionType;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("isImportant")
    private Boolean isImportant;
    @JsonProperty("maxAnswerCount")
    private Integer maxAnswerCount;
    @JsonProperty("typeQ")
    private Question.TypeQ typeQ;
    @JsonProperty("answers")
    private Set<AnswerDto> answers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("isImportant")
    public Boolean getImportant() {
        return isImportant;
    }

    @JsonProperty("isImportant")
    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public Integer getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(Integer maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }

    public Question.TypeQ getTypeQ() {
        return typeQ;
    }

    public void setTypeQ(Question.TypeQ typeQ) {
        this.typeQ = typeQ;
    }

    public Set<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDto> answers) {
        this.answers = answers;
    }
}
