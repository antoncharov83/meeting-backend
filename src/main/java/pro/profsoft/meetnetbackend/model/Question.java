package pro.profsoft.meetnetbackend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "question-with-answers",
        attributeNodes = { @NamedAttributeNode("answers")})
public class Question extends BaseEntity {
     public enum TypeQ { Form, Test } // Анкета или Тест

     private Integer position;
     private Integer questionType;
     private String title;
     private String subtitle;
     private Boolean isImportant;
     private Integer maxAnswerCount;
     @Enumerated(EnumType.ORDINAL)
     private TypeQ typeQ;
     @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
     private Set<Answer> answers = new HashSet<>();
     @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
     private List<ProfileAnswer> profileAnswers = new ArrayList<>();

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

     public Boolean getImportant() {
          return isImportant;
     }

     public void setImportant(Boolean important) {
          isImportant = important;
     }

     public Integer getMaxAnswerCount() {
          return maxAnswerCount;
     }

     public void setMaxAnswerCount(Integer maxAnswerCount) {
          this.maxAnswerCount = maxAnswerCount;
     }

     public TypeQ getTypeQ() {
          return typeQ;
     }

     public void setTypeQ(TypeQ typeQ) {
          this.typeQ = typeQ;
     }

     public Set<Answer> getAnswers() {
          return answers;
     }

     public void setAnswers(Set<Answer> answers) {
          this.answers = answers;
     }

     public List<ProfileAnswer> getProfileAnswers() {
          return profileAnswers;
     }

     public void setProfileAnswers(List<ProfileAnswer> profileAnswers) {
          this.profileAnswers = profileAnswers;
     }
}
