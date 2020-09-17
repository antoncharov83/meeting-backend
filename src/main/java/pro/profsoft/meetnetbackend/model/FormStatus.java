package pro.profsoft.meetnetbackend.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

//@Entity
public class FormStatus extends BaseEntity {
    private Integer informationFormStatus; //статус анкеты
    private Integer testFormStatus;        //статус теста
    @OneToOne(mappedBy = "formStatus")
    private Profile profile;

    public Integer getInformationFormStatus() {
        return informationFormStatus;
    }

    public void setInformationFormStatus(Integer informationFormStatus) {
        this.informationFormStatus = informationFormStatus;
    }

    public Integer getTestFormStatus() {
        return testFormStatus;
    }

    public void setTestFormStatus(Integer testFormStatus) {
        this.testFormStatus = testFormStatus;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
