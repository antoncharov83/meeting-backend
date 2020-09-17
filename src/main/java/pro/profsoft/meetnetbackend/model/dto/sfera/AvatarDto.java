package pro.profsoft.meetnetbackend.model.dto.sfera;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "social",
    "content"
})
public class AvatarDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("social")
    private SocialDto socialDto;
    @JsonProperty("content")
    private Set<ContentDto> contentDto = null;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("social")
    public SocialDto getSocialDto() {
        return socialDto;
    }

    @JsonProperty("social")
    public void setSocialDto(SocialDto socialDto) {
        this.socialDto = socialDto;
    }

    @JsonProperty("content")
    public Set<ContentDto> getContentDto() {
        return contentDto;
    }

    @JsonProperty("content")
    public void setContentDto(Set<ContentDto> contentDto) {
        this.contentDto = contentDto;
    }
}
