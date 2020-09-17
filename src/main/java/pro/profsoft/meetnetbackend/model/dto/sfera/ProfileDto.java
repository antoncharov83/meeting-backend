package pro.profsoft.meetnetbackend.model.dto.sfera;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "nickName",
    "firstName",
    "lastName",
    "avatar"
})
public class ProfileDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("avatar")
    private AvatarDto avatarDto;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nickName")
    public String getNickName() {
        return nickName;
    }

    @JsonProperty("nickName")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("avatar")
    public AvatarDto getAvatarDto() {
        return avatarDto;
    }

    @JsonProperty("avatar")
    public void setAvatarDto(AvatarDto avatarDto) {
        this.avatarDto = avatarDto;
    }
}
