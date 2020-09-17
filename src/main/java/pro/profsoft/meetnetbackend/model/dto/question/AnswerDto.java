package pro.profsoft.meetnetbackend.model.dto.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("answerIcon")
    private String answerIcon;
    @JsonProperty("isChecked")
    private Boolean isChecked;
    @JsonProperty("isRadioButton")
    private Boolean isRadioButton;
    @JsonProperty("textEdit")
    private String textEdit;
    @JsonProperty("isAgreementVisible")
    private Boolean isAgreementVisible;
    @JsonProperty("answerRanges")
    private AnswerRangeDto answerRanges;

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

    public String getAnswerIcon() {
        return answerIcon;
    }

    public void setAnswerIcon(String answerIcon) {
        this.answerIcon = answerIcon;
    }

    @JsonProperty("isChecked")
    public Boolean getChecked() {
        return isChecked;
    }

    @JsonProperty("isChecked")
    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @JsonProperty("isRadioButton")
    public Boolean getRadioButton() {
        return isRadioButton;
    }

    @JsonProperty("isRadioButton")
    public void setRadioButton(Boolean radioButton) {
        isRadioButton = radioButton;
    }

    public String getTextEdit() {
        return textEdit;
    }

    public void setTextEdit(String textEdit) {
        this.textEdit = textEdit;
    }

    @JsonProperty("isAgreementVisible")
    public Boolean getAgreementVisible() {
        return isAgreementVisible;
    }

    @JsonProperty("isAgreementVisible")
    public void setAgreementVisible(Boolean agreementVisible) {
        isAgreementVisible = agreementVisible;
    }

    public AnswerRangeDto getAnswerRanges() {
        return answerRanges;
    }

    public void setAnswerRanges(AnswerRangeDto answerRanges) {
        this.answerRanges = answerRanges;
    }
}
