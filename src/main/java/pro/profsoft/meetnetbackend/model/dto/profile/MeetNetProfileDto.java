package pro.profsoft.meetnetbackend.model.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import pro.profsoft.meetnetbackend.model.dto.sfera.AvatarDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountId",
        "avatarId",
        "nickName",
        "avatar",
        "age",
        "city",
        "coincidence",
        "compability"
})
public class MeetNetProfileDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("avatarId")
    private Long avatarId;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("avatar")
    private AvatarDto avatarDto;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("city")
    private String city;
    @JsonProperty("coincidence")
    private Boolean coincidence;
    @JsonProperty("compatibility")
    private Integer compability; // от 0 до 100


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public AvatarDto getAvatarDto() {
        return avatarDto;
    }

    public void setAvatarDto(AvatarDto avatarDto) {
        this.avatarDto = avatarDto;
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

    public Boolean getCoincidence() {
        return coincidence;
    }

    public void setCoincidence(Boolean coincidence) {
        this.coincidence = coincidence;
    }

    @JsonProperty("compatibility")
    public Integer getCompability() {
        return compability;
    }

    @JsonProperty("compatibility")
    public void setCompability(Integer compability) {
        this.compability = compability;
    }
}
