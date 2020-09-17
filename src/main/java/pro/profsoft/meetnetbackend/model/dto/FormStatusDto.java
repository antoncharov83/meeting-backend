package pro.profsoft.meetnetbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormStatusDto {
    @JsonProperty("informationFormStatus")
    private Integer informationFormStatus; //статус анкеты
    @JsonProperty("testFormStatus")
    private Integer testFormStatus;        //статус теста

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
}
