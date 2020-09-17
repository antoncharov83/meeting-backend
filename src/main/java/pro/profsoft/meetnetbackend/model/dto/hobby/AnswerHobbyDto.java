package pro.profsoft.meetnetbackend.model.dto.hobby;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerHobbyDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("isTitle")
    private Boolean isTitle;
    @JsonProperty("isCheckedWant")
    private Boolean isCheckedWant;
    @JsonProperty("isCheckedCan")
    private Boolean isCheckedCan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("isTitle")
    public Boolean getTitle() {
        return isTitle;
    }

    @JsonProperty("isTitle")
    public void setTitle(Boolean title) {
        isTitle = title;
    }

    @JsonProperty("isCheckedWant")
    public Boolean getCheckedWant() {
        return isCheckedWant;
    }

    @JsonProperty("isCheckedWant")
    public void setCheckedWant(Boolean checkedWant) {
        isCheckedWant = checkedWant;
    }

    @JsonProperty("isCheckedCan")
    public Boolean getCheckedCan() {
        return isCheckedCan;
    }

    @JsonProperty("isCheckedCan")
    public void setCheckedCan(Boolean checkedCan) {
        isCheckedCan = checkedCan;
    }
}
