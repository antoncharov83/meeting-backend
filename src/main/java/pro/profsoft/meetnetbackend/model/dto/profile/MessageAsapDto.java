package pro.profsoft.meetnetbackend.model.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageAsapDto {
    private Boolean canSendMessage;

    public MessageAsapDto() {
    }

    public MessageAsapDto(Boolean canSendMessage) {
        this.canSendMessage = canSendMessage;
    }

    public Boolean getCanSendMessage() {
        return canSendMessage;
    }

    public void setCanSendMessage(Boolean canSendMessage) {
        this.canSendMessage = canSendMessage;
    }
}
