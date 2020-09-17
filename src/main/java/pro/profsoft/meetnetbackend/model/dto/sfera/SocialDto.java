package pro.profsoft.meetnetbackend.model.dto.sfera;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "likes",
    "forwards",
    "feedId"
})
public class SocialDto {

    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("forwards")
    private Integer forwards;
    @JsonProperty("feedId")
    private Integer feedId;

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("forwards")
    public Integer getForwards() {
        return forwards;
    }

    @JsonProperty("forwards")
    public void setForwards(Integer forwards) {
        this.forwards = forwards;
    }

    @JsonProperty("feedId")
    public Integer getFeedId() {
        return feedId;
    }

    @JsonProperty("feedId")
    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }
}
