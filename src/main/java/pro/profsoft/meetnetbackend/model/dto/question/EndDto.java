package pro.profsoft.meetnetbackend.model.dto.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EndDto {
    private Boolean isForm;
    private Boolean isTest;

    public Boolean getForm() {
        return isForm;
    }

    public void setForm(Boolean form) {
        isForm = form;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }
}
