package pro.profsoft.meetnetbackend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Profile {
    @Id
    private Long id;
    private String token;
    private String nickName;
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private Integer likes;
    private Integer forwards;
    private Integer feedId;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Set<Content> content = new HashSet<>();
    @OneToMany(mappedBy = "ownerProfile",cascade = CascadeType.ALL)
    private List<Relationship> relationshipsOwner = new ArrayList<>();
    @OneToMany(mappedBy = "dislikeProfile",cascade = CascadeType.ALL)
    private List<Relationship> relationshipsDislike = new ArrayList<>();
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileAnswer> profileAnswers = new ArrayList<>();
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    List<ProfileHobbyAnswer> profileHobbyAnswers = new ArrayList<>();
    @OneToMany(mappedBy = "profile")
    private List<ProfileReport> profileReports = new ArrayList<>();

    public void copy(Profile profile) {
        this.nickName = profile.nickName;
        this.firstName = profile.firstName;
        this.lastName = profile.lastName;
        this.likes = profile.likes;
        this.forwards = profile.forwards;
        this.feedId = profile.feedId;
        this.content = profile.content;
        this.token = profile.token;
        this.age = profile.age;
        this.city = profile.city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getForwards() {
        return forwards;
    }

    public void setForwards(Integer forwards) {
        this.forwards = forwards;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Set<Content> getContent() {
        return content;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }

    public List<Relationship> getRelationshipsOwner() {
        return relationshipsOwner;
    }

    public void setRelationshipsOwner(List<Relationship> relationshipsOwner) {
        this.relationshipsOwner = relationshipsOwner;
    }

    public List<Relationship> getRelationshipsDislike() {
        return relationshipsDislike;
    }

    public void setRelationshipsDislike(List<Relationship> relationshipsDislike) {
        this.relationshipsDislike = relationshipsDislike;
    }

    public List<ProfileAnswer> getProfileAnswers() {
        return profileAnswers;
    }

    public void setProfileAnswers(List<ProfileAnswer> profileAnswers) {
        this.profileAnswers = profileAnswers;
    }

    public List<ProfileHobbyAnswer> getProfileHobbyAnswers() {
        return profileHobbyAnswers;
    }

    public void setProfileHobbyAnswers(List<ProfileHobbyAnswer> profileHobbyAnswers) {
        this.profileHobbyAnswers = profileHobbyAnswers;
    }

    public List<ProfileReport> getProfileReports() {
        return profileReports;
    }

    public void setProfileReports(List<ProfileReport> profileReports) {
        this.profileReports = profileReports;
    }
}
