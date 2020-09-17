package pro.profsoft.meetnetbackend.model.dto.hobby;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionHobbyDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("image")
    private String image;
    @JsonProperty("answers")
    private List<AnswerHobbyDto> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<AnswerHobbyDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerHobbyDto> answers) {
        this.answers = answers;
    }
}
