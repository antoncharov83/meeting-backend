package pro.profsoft.meetnetbackend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Answer extends BaseEntity {
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;
    private String answerIcon;
    private Boolean isChecked;
    private Boolean isRadioButton;
    private String textEdit;
    private Boolean isAgreementVisible;
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private Set<AnswerRange> answerRanges = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

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

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getRadioButton() {
        return isRadioButton;
    }

    public void setRadioButton(Boolean radioButton) {
        isRadioButton = radioButton;
    }

    public String getTextEdit() {
        return textEdit;
    }

    public void setTextEdit(String textEdit) {
        this.textEdit = textEdit;
    }

    public Boolean getAgreementVisible() {
        return isAgreementVisible;
    }

    public void setAgreementVisible(Boolean agreementVisible) {
        isAgreementVisible = agreementVisible;
    }

    public Set<AnswerRange> getAnswerRanges() {
        return answerRanges;
    }

    public void setAnswerRanges(Set<AnswerRange> answerRanges) {
        this.answerRanges = answerRanges;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
