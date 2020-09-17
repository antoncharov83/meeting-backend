package pro.profsoft.meetnetbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuestionHobby extends BaseEntity {
    private String title;
    @Column(unique = true)
    private Integer position;
    private String image;
    @OneToMany(mappedBy = "questionHobby", cascade = CascadeType.ALL)
    private List<AnswerHobby> answers = new ArrayList<>();
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    List<ProfileHobbyAnswer> profileHobbyAnswers = new ArrayList<>();

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

    public List<AnswerHobby> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerHobby> answers) {
        this.answers = answers;
    }

    public List<ProfileHobbyAnswer> getProfileHobbyAnswers() {
        return profileHobbyAnswers;
    }

    public void setProfileHobbyAnswers(List<ProfileHobbyAnswer> profileHobbyAnswers) {
        this.profileHobbyAnswers = profileHobbyAnswers;
    }
}
