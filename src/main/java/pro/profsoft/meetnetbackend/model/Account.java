package pro.profsoft.meetnetbackend.model;


public class Account extends BaseEntity {
    private String  accountId;
    private Long avatarId;
    private String nickName;
    private Long avatar;
    private String age;
    private String city;
    private Boolean coincidence;
    private Integer compability;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
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

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public Integer getCompability() {
        return compability;
    }

    public void setCompability(Integer compability) {
        this.compability = compability;
    }
}
